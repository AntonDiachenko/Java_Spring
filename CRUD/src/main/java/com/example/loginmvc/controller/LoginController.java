package com.example.loginmvc.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
public class LoginController {

    @RequestMapping("/")

    public ModelAndView index(){
        return new ModelAndView("index");
    }
    @PostMapping("/hello")

    public ModelAndView sayHello(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return new ModelAndView("hello");
    }
}
