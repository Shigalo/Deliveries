package by.bsuir.ucp.Controllers;

import by.bsuir.ucp.Entities.*;
import by.bsuir.ucp.Repositories.PointRepository;
import by.bsuir.ucp.Repositories.TransportRepository;
import by.bsuir.ucp.Repositories.UserRepository;
import by.bsuir.ucp.Repositories.WayRepository;
import by.bsuir.ucp.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/requests")
public class RequestsController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    @Autowired
    RequestService requestService;
    @Autowired
    TransportService transportService;
    @Autowired
    PointService pointService;
    @Autowired
    WayService wayService;

    @GetMapping
    public String setForm2(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        if(userService.isAdmin()) { model.addAttribute("requestList", requestService.getRequestList()); }
        else { model.addAttribute("requestList", requestService.getRequestList(userService.getCurrentUser())); }
        return "requests";
    }

    @GetMapping("/addRequest")
    public String addTransport(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("transportList", transportService.getTransportList());


        List<Way> wayList = wayService.getWayList();
        model.addAttribute("wayList", wayList);
        model.addAttribute("wayService", wayService);

        return "addRequest";
    }

    @PostMapping("/addRequest")
    public String addTransportData(@RequestParam String weight,
                                   @RequestParam String id,
                                   @RequestParam String type,
                                   Model model) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isLogin", userService.isLogin());

        Way way = wayService.getById(id);
        User user = userService.getCurrentUser();
        Double cost = Double.valueOf(weight);
        switch (type) {
            case "dangerous": cost *= wayService.getDangerous(way); break;
            case "fragile": cost *= wayService.getFragile(way); break;
            case "perishable": cost *= wayService.getPerishable(way); break;
            case "normal": cost *= wayService.getCost(way); break;
        }
        Request request = new Request(way, user, Double.valueOf(weight), cost);
        request.setComplete(false);
        requestService.addRequest(request);

        model.addAttribute("requestList", requestService.getRequestList(userService.getCurrentUser()));
        return "redirect:/requests";
    }

    @GetMapping("/push")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String pushChoice(Model model) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("wayList", wayService.getWayList());
        model.addAttribute("requests", requestService.getWaitingRequestList());
        model.addAttribute("wayService", wayService);

        return "push";
    }

    @GetMapping("/pushing")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String pushComplete(Model model,
                               @RequestParam String get) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isLogin", userService.isLogin());

        for(String id : get.split(",")) {
            requestService.complete(id);
        }
        return "redirect:/requests";
    }
}
