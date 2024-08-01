package jp.eightbit.curriculums.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.eightbit.curriculums.entity.User;
import jp.eightbit.curriculums.repository.UserRepository;

@Service
public class LoginUserService implements UserDetailsService {
	private final UserRepository userRepo;
	
	public LoginUserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userOptional = Optional.of(userRepo.findByEmail(email));
		return userOptional.map(user -> new UserDetailsImpl(user))
				.orElseThrow(() -> new UsernameNotFoundException("not found"));
	}
}
