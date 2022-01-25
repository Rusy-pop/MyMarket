package app.Controller;

import app.Model.Role;
import app.Model.User;
import app.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    UserRepo userRepo;

    @GetMapping("registration")
    public String show(Model model){
        return "registration";
    }

    @PostMapping("registration")
    public String addUser( User user,
                           Model model){
        if (userRepo.findByUsername(user.getUsername())==null){
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepo.save(user);
            model.addAttribute("message", "User create");

        } else model.addAttribute("message", "User allready exist");

        return "registration";
    }
}
