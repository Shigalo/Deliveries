package by.bsuir.ucp.Controllers;

import by.bsuir.ucp.Entities.Point;
import by.bsuir.ucp.Services.PointService;
import by.bsuir.ucp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/points")
//@PreAuthorize("hasAuthority('ADMIN')")
public class PointsController {

    @Autowired
    UserService userService;

    @Autowired
    PointService pointService;

    @GetMapping
    public String setForm2(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("pointList", pointService.getPointList());
        return "points";
    }

/*    @PostMapping("/points")
    public String getForm(Model model) {
        model.addAttribute("isLogin", userService.isLogin());

        return "points";
    }*/

    @GetMapping("/addPoint")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addPoint(Model model) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isLogin", userService.isLogin());
        return "addPoint";
    }

    @PostMapping("/addPoint")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addPointData(@RequestParam String pointName,
                               Model model) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isLogin", userService.isLogin());

        if(pointService.addPoint(pointName)) {
            model.addAttribute("pointList", pointService.getPointList());
            return "points";
        }
        model.addAttribute("error", "Точка с таким именем уже существует!");
        return "addPoint";
    }

    @GetMapping("/pointEdit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editPointSave(Model model, @PathVariable String id) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("pointList", pointService.getPointList());

        Point point = pointService.getById(id);
        model.addAttribute("id", point.getId());
        model.addAttribute("name", point.getName());

        return "editPoint";
    }

    @PostMapping("/pointEdit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editPoint(@RequestParam String pointName, Model model, @PathVariable String id) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isLogin", userService.isLogin());
        if(pointService.editPoint(id, pointName)) {
            model.addAttribute("pointList", pointService.getPointList());
            return "points";
        }
        model.addAttribute("error", "Точка с таким именем уже существует!");
        return "editPoint";

    }

    @RequestMapping("/pointRemove/{id}")
    public String pointRemove(@PathVariable("id") int id){
        pointService.pointRemoveById(id);
        return "redirect:/points";
    }
}
