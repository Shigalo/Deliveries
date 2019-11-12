package by.bsuir.ucp.Controllers;

import by.bsuir.ucp.Entities.Role;
import by.bsuir.ucp.Entities.User;
import by.bsuir.ucp.Repositories.UserRepository;
import by.bsuir.ucp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping
    public String setForm(Model  model) {
        model.addAttribute("isLogin", userService.isLogin());

        return "registration";
    }

    @PostMapping
    public String getForm(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String passwordConfirm,
                          Model model) {
        model.addAttribute("isLogin", userService.isLogin());

        if(!password.equals(passwordConfirm)) {
            model.addAttribute("massage", "Пароли не совпадают!");
            return "registration";
        }
        if(userRepository.findByName(username) != null) {
            model.addAttribute("massage", "Имя пользователя уже используется!");
            return "registration";
        }
        User user = new User(username, password, true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/";
    }
}
