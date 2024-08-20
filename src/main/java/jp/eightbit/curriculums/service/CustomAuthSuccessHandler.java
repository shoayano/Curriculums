package jp.eightbit.curriculums.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.eightbit.curriculums.entity.User;
import jp.eightbit.curriculums.repository.UserRepository;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
	private final UserRepository userRepo;

	@Autowired
	public CustomAuthSuccessHandler(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		System.out.println("test auth success");
		System.out.println();
		
		String newPass = request.getParameter("newPass");
		
		if(newPass != null && !newPass.isEmpty()) {
			User user = userRepo.findByEmail(request.getParameter("username"));
			String hashPass = new BCryptPasswordEncoder().encode(newPass);
			user.setPassword(hashPass);
			userRepo.save(user);
		}
		
		response.sendRedirect("/curriculums/routing");
	}

}
