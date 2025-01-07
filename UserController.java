package com.springthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springthymeleaf.service.UserService;
import com.springthymeleaf.user.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@GetMapping("/regPage")
	public String openReg(Model model) {
		model.addAttribute("User", new User());
		return "register";
		
	}
	@GetMapping("/loginPage")
	public String openLoginPage(Model model) {
		model.addAttribute("User", new User());
		return "login";
	}
	@PostMapping("/submitRegisForm")
	public String submitRegForm(@ModelAttribute("User") User user, Model model) {
		
		boolean status = false;
		status = userService.addUser(user);
		if(status) {
			model.addAttribute("successMessage", "User has been successfully registered");
		}else {
			model.addAttribute("errorMessage", "User has not been successfully registered");
		}
		return "register";
	}
	@PostMapping("/submitLoginForm")
	public String submitLoginForm(@ModelAttribute("User") User user, Model model) {
		User validateUser = userService.loginUser(user.getEmail(), user.getPassword());
		if(validateUser!=null) {
			model.addAttribute("ValidateUser", validateUser);
		return "profile";
		}else {
			model.addAttribute("errorMessage", "Email/Password is wrong");
			return "login";
		}
		
		
	}
	@GetMapping("/Logout")
	public String logoutSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session!=null) {
			session.invalidate();
		}
		return "redirect:/loginPage";
	}
}
