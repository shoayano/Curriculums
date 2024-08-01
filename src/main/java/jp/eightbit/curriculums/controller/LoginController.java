package jp.eightbit.curriculums.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jp.eightbit.curriculums.service.UserDetailsImpl;

@Controller
public class LoginController {
		
	@GetMapping("/login")
	public String loginPage() {
		
		System.out.println("test login controller \n");
		
		return "login";
	}
	
	@GetMapping("/routing")
	public String loginRouting(@AuthenticationPrincipal UserDetailsImpl loginUser) {
		String property = loginUser.getPropertyName();
				
		if(property.equals("管理者") || property.equals("先生")) return "redirect:/studentIndex";
		else return "redirect:/mypage";
	}

}
