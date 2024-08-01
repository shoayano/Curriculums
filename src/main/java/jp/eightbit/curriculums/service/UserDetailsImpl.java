package jp.eightbit.curriculums.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jp.eightbit.curriculums.entity.User;

public class UserDetailsImpl implements UserDetails {
	private final User loginUser;
	private final Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(User loginUser) {
		this.loginUser = loginUser;
		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		list.add(new SimpleGrantedAuthority("ROLE_" + loginUser.getProperty().getName()));
		this.authorities = list;
		
		System.out.println("test login");
		System.out.println(this.loginUser);
		System.out.println(this.authorities + "\n");
	}
	
	public User getUser() {
		return loginUser;
	}
	
	public int getUserId() {
		return loginUser.getUserId();
	}
	
	public String getPropertyName() {
		return loginUser.getProperty().getName();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return loginUser.getPassword();
	}
	
	@Override
	public String getUsername() {
		return loginUser.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

}
