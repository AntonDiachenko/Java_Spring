package com.data.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.data.model.UserData;
import com.data.dao.UserDataRepo;

@Controller
public class DataControlller implements WebMvcConfigurer{
	
	@Autowired
	UserDataRepo repo;
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/welcome").setViewName("welcome");
    }

	
	@GetMapping("/")
	public String indexpage(UserData userData) {
		return "home";
	}
	
	
	  @PostMapping("/registration-successful")
	    public String checkInfo(@Valid UserData userData, BindingResult bindingResult, Model model) {

	       if (bindingResult.hasErrors()) {
	            return "register";
	        }
	        repo.save(userData);
	        model.addAttribute("name", userData.getName());
	        model.addAttribute("email", userData.getEmail());
	        model.addAttribute("password", userData.getPassword());	 
	        model.addAttribute("birthday", userData.getBirthday());	
	        model.addAttribute("cellphone", userData.getCellphone());	        
	        model.addAttribute("profession", userData.getProfession());
	        model.addAttribute("income", userData.getIncome());


	        return "welcome";
	    }
	  
	  @PostMapping("/register")
	    public String register(UserData userData) {
	            return "register";
	       
	    }
	  
}
