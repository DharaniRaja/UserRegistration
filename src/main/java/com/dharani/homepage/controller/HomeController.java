package com.dharani.homepage.controller;

import com.dharani.homepage.model.LoginModel;
import com.dharani.homepage.model.User;
import com.dharani.homepage.service.HomeServiceImpl;
import com.dharani.homepage.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller

public class HomeController {
@Autowired
    Validate validate;

    @Autowired
    HomeServiceImpl homeService;

    @GetMapping("/home")
    public String homePage() {
        return "Home.html";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login.html";
    }
    @PostMapping("/login")
    public String getLoginData(@RequestBody LoginModel loginModel) {

         homeService.checkLogin(loginModel);
         return "Home.html";

    }
    @GetMapping("/newUser")
    public String userPage() {
        return "newUser.html";
    }

    @PostMapping("/newUser")
    public String createUser(User user, Model model) {

        if ((!validate.validateEmail(user.getEmail()))||(!validate.validateName(user.getName()))||(!validate.isValidPassword(user.getPassword()))
                ||(!validate.validatePhoneNumber(user))){
            if(!validate.validateEmail(user.getEmail()))
            {
                model.addAttribute("emailError","Enter Valid Email");
            }
            if (!validate.validateName(user.getName()))
            {
                model.addAttribute("nameError","Enter Valid Name");
            }
            if (!validate.isValidPassword(user.getPassword()))
            {
                model.addAttribute("passwordError","Enter Valid Password");
            }
            if (!validate.validatePhoneNumber(user))
            {
                model.addAttribute("phoneError","Enter Valid Phone Number");
            }
            return "newUser.html";
        }

        homeService.addUser(user);
        return "Home.html";

    }

    /*@PostMapping("/createUser")
    public User createUser(@RequestBody User user)
    {
        return homeService.addUser(user);

    }*/
}
