/*
package by.bsuir.ucp.Controllers;

import by.bsuir.ucp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserRepository userRepository;


    @GetMapping
    public String setForm(Model  model) {

        return "login";
    }

    @PostMapping
    public String getForm(@RequestParam String username,
                          @RequestParam String password,
                          Model model) {

 if(userRepository.findByNameAndPassword(username, password) != null) {
            return "redirect:/";
        }

//        model.addAttribute("error", "Неверное имя пользователя или пароль!");
        return "login";

    }
}
*/
