package com.spring_boot.sBoot.controller;

import com.spring_boot.sBoot.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String pageForUser (Model model, Principal principal) {
        model.addAttribute("user",userService.findByUsername(principal.getName()));
        return "user";
    }

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}