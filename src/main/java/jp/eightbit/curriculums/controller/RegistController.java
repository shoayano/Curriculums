package jp.eightbit.curriculums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.eightbit.curriculums.entity.Property;
import jp.eightbit.curriculums.entity.User;
import jp.eightbit.curriculums.model.UserModel;
import jp.eightbit.curriculums.service.UserDetailsImpl;
import jp.eightbit.curriculums.service.UserService;

@Controller
public class RegistController {
	
	private final UserService userService;
	
	@Autowired
	public RegistController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/registration")
	public String registrationPage(@AuthenticationPrincipal UserDetailsImpl loginUser, Model model) {
		List<Property> list = userService.getAllProperty();
		model.addAttribute("propertyList", list);
		model.addAttribute("user", new User());
		model.addAttribute("propertyId", 0);
		model.addAttribute("loginUser", new UserModel(loginUser.getUser()));
		return "registration";
	}
	
	@PostMapping("/registerUser")
	public String registerUser(@AuthenticationPrincipal UserDetailsImpl loginUser, @ModelAttribute User user, @RequestParam("propertyId") int propertyId, Model model) {
		
		System.out.println("test regist");
		System.out.println(model);
		System.out.println(propertyId);
		System.out.println();
		
		try {
			userService.registerUser(user, propertyId);
		}catch(Exception e) {
			List<Property> list = userService.getAllProperty();
			model.addAttribute("propertyList", list);
			model.addAttribute("propertyId", propertyId);
			model.addAttribute("loginUser", new UserModel(loginUser.getUser()));
			model.addAttribute("error", "error");
			return "registration";
		}
		
		return "redirect:/studentIndex";
	}


}
