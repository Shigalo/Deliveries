package by.bsuir.ucp.Controllers;

import by.bsuir.ucp.Entities.Point;
import by.bsuir.ucp.Entities.Transport;
import by.bsuir.ucp.Entities.User;
import by.bsuir.ucp.Entities.Way;
import by.bsuir.ucp.Repositories.PointRepository;
import by.bsuir.ucp.Repositories.TransportRepository;
import by.bsuir.ucp.Repositories.UserRepository;
import by.bsuir.ucp.Repositories.WayRepository;
import by.bsuir.ucp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WayRepository wayRepository;
    @Autowired
    TransportRepository transportRepository;
    @Autowired
    PointRepository pointRepository;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        List<User> userList = userRepository.findAll();
        List<Way> wayList = wayRepository.findByTransportNull();
        List<Transport> transportList = transportRepository.findAll();
        List<Point> pointList = pointRepository.findByWayNull();

        model.addAttribute("userList", userList.size());
        model.addAttribute("wayList", wayList.size());
        model.addAttribute("transportList", transportList.size());
        model.addAttribute("pointList", pointList.size());
        model.addAttribute("isLogin", userService.isLogin());

        return "homepage";
    }
}
