package app.Controller;

import app.Model.User;
import app.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Registration")
public class RegistrationController {
    @Autowired
    UserRepo userRepo;

    @GetMapping
    public String show(Model model){
        return "Registration";
    }

    @PostMapping()
    public String addUser( @RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String password,
                            Model model){
        if (userRepo.findByUserName(name)==null){
            User u = new User(name, email, password);
            userRepo.save(u);
            model.addAttribute("message", "User create");
        } else model.addAttribute("message", "User allready exist");

        return "Registration";
    }
}
