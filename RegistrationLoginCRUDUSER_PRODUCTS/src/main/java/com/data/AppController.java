package com.data;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class AppController {
 
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		
		return "register_success";
	}
	
	@GetMapping("/welcome")
	public String UserPortal() {
		return "welcome";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List <User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
	//CRUD for users
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveUser( User user)
	{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		return "redirect:/users";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditUserPage(@PathVariable(name="id") long id)
	{
		ModelAndView mav =  new ModelAndView("edit_user");
		User user = userRepo.findById(id).get();
		mav.addObject("user", user);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable(name="id") long id)
	{
		userRepo.deleteById(id);
		return "redirect:/users";
	}
	
	//CRUD for products
	@Autowired
	private ProductRepository productRepo;
	
	@RequestMapping("/products")
	public String viewHomePage(Model model)
	{
		List<Product> listProducts = productRepo.findAll();
		model.addAttribute("listProducts", listProducts);
		return "products";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model)
	{
		Product product= new Product();
		model.addAttribute("product", product);
		return "new_product";
	}
	
	@RequestMapping(value="/saveproduct", method=RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product)
	{
		productRepo.save(product);
		return "redirect:/products";
	}
	
	@RequestMapping("/editproduct/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name="id") long id)
	{
		ModelAndView mav =  new ModelAndView("edit_product");
		Product product = productRepo.findById(id).get();
		mav.addObject("product", product);
		
		return mav;
	}
	
	@RequestMapping("/deleteproduct/{id}")
	public String deleteProduct(@PathVariable(name="id") long id)
	{
		productRepo.deleteById(id);
		return "redirect:/products";
	}
}
