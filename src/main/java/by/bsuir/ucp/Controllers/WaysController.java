package by.bsuir.ucp.Controllers;

import by.bsuir.ucp.Entities.Point;
import by.bsuir.ucp.Entities.Transport;
import by.bsuir.ucp.Entities.Way;
import by.bsuir.ucp.Repositories.WayRepository;
import by.bsuir.ucp.Services.PointService;
import by.bsuir.ucp.Services.TransportService;
import by.bsuir.ucp.Services.UserService;
import by.bsuir.ucp.Services.WayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ways")
public class WaysController {

    @Autowired
    UserService userService;

    @Autowired
    WayService wayService;

    @Autowired
    PointService pointService;

    @Autowired
    TransportService transportService;

    @GetMapping
    public String setForm3(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("wayList", wayService.getWayList());
        return "ways";
    }

    @PostMapping
    public String getForm(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("wayList", wayService.getWayList());

        return "ways";
    }

    @GetMapping("/addWay")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addWay(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("pointList", pointService.getPointList());
        return "addWay";
    }

    @PostMapping("/addWay")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addWayData(@RequestParam String startPoint,
                             @RequestParam String endPoint,
                             @RequestParam String nSubPoints,
                             Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("startPoint", startPoint);
        model.addAttribute("endPoint", endPoint);
        model.addAttribute("nSubPoints", nSubPoints);
        model.addAttribute("wayList", wayService.getWayList());
        return "redirect:/ways/subPoints";
    }

    @GetMapping("/subPoints")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String setSubPoints(@RequestParam String startPoint,
                               @RequestParam String endPoint,
                               @RequestParam String nSubPoints,
                               Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        return "subPoints";
    }

    @PostMapping("/subPoints")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String setSubPointsData(@RequestParam String startPoint,
                                   @RequestParam String endPoint,
                                   @RequestParam String subPoints,
                                   Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        subPoints = startPoint+","+subPoints+","+endPoint;
        model.addAttribute("subPoints", subPoints);


        return "redirect:/ways/subWays";
    }

    @GetMapping("/subWays")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String setSubWays(@RequestParam String subPoints,
                             Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("transportList", transportService.getTransportList());
        model.addAttribute("subPoints", subPoints);

        return "subWays";
    }

    @PostMapping("/subWays")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String setSubWaysData(@RequestParam String subPoints,
                                 @RequestParam String transport,
                                 @RequestParam String length[],
                                   Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());

        /*String[] sub = subPoints.split(",");
        for(int i = 0; i < length.length; i++) {
            System.out.println(sub[i] + " " + length[i] + " " + sub[i+1]);
        }*/

        wayService.addWay(subPoints, length, transport);

        return "redirect:/ways";
    }

    @GetMapping("/wayView/{id}")
    public String editTransport(Model model, @PathVariable String id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("transportList", transportService.getTransportList());

        Way way = wayService.getById(id);
        model.addAttribute("way", way);
        List<Point> pointList = pointService.getSubPoints(way.getId());

        List<Way> wayList = new ArrayList<>();
        wayList.add(wayService.getSubWay(way.getStartPoint(), pointList.get(0)));
        for(int i = 0; i < pointList.size()-1; i++) { wayList.add(wayService.getSubWay(pointList.get(i), pointList.get(i+1))); }
        wayList.add(wayService.getSubWay(pointList.get(pointList.size()-1), way.getEndPoint()));

        Double sumLength = 0.0;
        Double sumTime = 0.0;
        for(Way buf : wayList) {
            sumLength += buf.getLength();
            sumTime += buf.getLength() / buf.getTransport().getSpeed();
        }

        model.addAttribute("pointList", pointList);
        model.addAttribute("wayList", wayList);
        model.addAttribute("sumLength", sumLength);
        model.addAttribute("sumTime", sumTime);

        return "viewWay";
    }

    @RequestMapping("/wayRemove/{id}")
    public String pointRemove(@PathVariable("id") int id){
        wayService.wayRemoveById(id);
        return "redirect:/ways";
    }
}
