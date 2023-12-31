package com.Strong.Tshirt_Web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.Strong.Tshirt_Web.Entity.Users;
import com.Strong.Tshirt_Web.Service.UerService;;

@Controller
public class UserController {

    @Autowired
    private UerService userService;

    @GetMapping("/UserLogin")
    public String UserLogin() {
        return "UserLogin";
    }

    @GetMapping("/Signup")
    public String Signup() {
        return "Signup";
    }

    @PostMapping("/AddUser")
    public String AddUsers(@ModelAttribute Users user) {
        userService.AddUser(user);
        return "redirect:/AllUsers";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }
}
