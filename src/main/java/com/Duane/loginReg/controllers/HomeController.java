package com.Duane.loginReg.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Duane.loginReg.models.User;
import com.Duane.loginReg.services.AppService;
import com.Duane.loginReg.validation.UserValidator;


@Controller
public class HomeController {

	private final AppService appServ;
	private final UserValidator userValidator;
	
	public HomeController(AppService appServ, UserValidator userValidator) {
		this.appServ = appServ;
		this.userValidator =userValidator;
	}
	
	@GetMapping("/")
	public String index(@ModelAttribute("user") User user) {
		return "Index.jsp";
	}
	
	@PostMapping("registration")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, 	HttpSession session) {
		userValidator.validate(user, result);
		if(result.hasErrors()) {
		return "Index.jsp";
		}
		User u = this.appServ.registerUser(user);
		session.setAttribute("UserId", u.getId());
		return "redirect:/dashboard";
	}	
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		Long id = (Long)session.getAttribute("UserId");
		User loggedinuser = this.appServ.findUserById(id);
		model.addAttribute("loggedinuser", loggedinuser);
		return"Dashboard.jsp";
	}
	
	@GetMapping ("/logout")
	public String logour(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(@Valid @RequestParam("email") String email, 		@RequestParam("password") String password, HttpSession session, RedirectAttributes 		redirectAttributes) {
		Boolean isLegit = this.appServ.authenticateUser(email, password);
		
		if(isLegit) {
			User user = this.appServ.findByEmail(email);
			session.setAttribute("UserId", user.getId());
			return "redirect:/dashboard";
		}
			
		redirectAttributes.addFlashAttribute("error", "Invalid login");
		return "redirect:/dashboard";
	}
	
};
