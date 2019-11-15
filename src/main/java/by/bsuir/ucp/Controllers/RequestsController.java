package by.bsuir.ucp.Controllers;

import by.bsuir.ucp.Entities.*;
import by.bsuir.ucp.Repositories.PointRepository;
import by.bsuir.ucp.Repositories.TransportRepository;
import by.bsuir.ucp.Repositories.UserRepository;
import by.bsuir.ucp.Repositories.WayRepository;
import by.bsuir.ucp.Services.RequestService;
import by.bsuir.ucp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping
    public String setForm2(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("requestList", requestService.getRequestList(userService.getCurrentUser()));
        return "requests";
    }

    @GetMapping("/addRequest")
    public String addTransport(Model model) {
        model.addAttribute("isLogin", userService.isLogin());

        return "addRequest";
    }

    @PostMapping("/addRequest")
    public String addTransportData(@RequestParam String transportName,
                                   @RequestParam String max_capacity,
                                   @RequestParam String unit_cost,
                                   @RequestParam String speed,
                                   @RequestParam String dangerous,
                                   @RequestParam String fragile,
                                   @RequestParam String perishable,
                                   Model model) {
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("isLogin", userService.isLogin());

//        requestService.addRequest(transportName, max_capacity, unit_cost, speed, dangerous, fragile, perishable);
        model.addAttribute("requestList", requestService.getRequestList(userService.getCurrentUser()));
        return "requests";

    }
}
