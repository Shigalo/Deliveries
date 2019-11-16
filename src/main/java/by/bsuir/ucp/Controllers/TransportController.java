package by.bsuir.ucp.Controllers;

import by.bsuir.ucp.Entities.Transport;
import by.bsuir.ucp.Services.TransportService;
import by.bsuir.ucp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transport")
//@PreAuthorize("hasAuthority('ADMIN')")
public class TransportController {

    @Autowired
    UserService userService;

    @Autowired
    TransportService transportService;

    @GetMapping
    public String setForm2(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("transportList", transportService.getTransportList());
        return "transport";
    }

    @GetMapping("/addTransport")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addTransport(Model model) {
        model.addAttribute("isLogin", userService.isLogin());
        return "addTransport";
    }

    @PostMapping("/addTransport")
    @PreAuthorize("hasAuthority('ADMIN')")
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

        if(transportService.addTransport(transportName, max_capacity, unit_cost, speed, dangerous, fragile, perishable)) {
            model.addAttribute("transportList", transportService.getTransportList());
            return "transport";
        }
        model.addAttribute("error", "Транспорт с таким именем уже существует!");
        return "addTransport";
    }

    @GetMapping("/transportEdit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editTransport(Model model, @PathVariable String id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("transportList", transportService.getTransportList());

        Transport transport = transportService.getById(id);
        model.addAttribute("id", transport.getId());
        model.addAttribute("name", transport.getName());
        model.addAttribute("max_capacity", transport.getMax_capacity());
        model.addAttribute("unit_cost", transport.getUnit_cost());
        model.addAttribute("speed", transport.getSpeed());
        model.addAttribute("dangerous", transport.getDangerous());
        model.addAttribute("fragile", transport.getFragile());
        model.addAttribute("perishable", transport.getPerishable());

        return "editTransport";
    }

    @PostMapping("/transportEdit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editTransportSave(@RequestParam String transportName,
                                    @RequestParam String max_capacity,
                                    @RequestParam String unit_cost,
                                    @RequestParam String speed,
                                    @RequestParam String dangerous,
                                    @RequestParam String fragile,
                                    @RequestParam String perishable,
                                    Model model,
                                    @PathVariable String id) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("isAdmin", userService.isAdmin());
        if(transportService.editTransport(id, transportName, max_capacity, unit_cost, speed, dangerous, fragile, perishable)) {
            model.addAttribute("transportList", transportService.getTransportList());
            return "transport";
        }
        model.addAttribute("error", "Транспорт с таким именем уже существует!");
        return "editTransport";

    }

    @RequestMapping("/transportRemove/{id}")
    public String transportRemove(@PathVariable("id") int id){
        transportService.transportRemoveById(id);
        return "redirect:/transport";
    }
}
