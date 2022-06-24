package com.example.shop.service;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.shop.model.User;
import com.example.shop.model.roles.Status;



public class SecurityUser implements UserDetails{
	private final String email;
	private final String password;
	private final Set <SimpleGrantedAuthority> authorities;
	private final boolean isActive;
	
	public SecurityUser(String email, String password, Set<SimpleGrantedAuthority> authorities, boolean isActive) {
		//super();
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.isActive = isActive;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return isActive;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return isActive;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return  isActive;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isActive;
	}
	public static SecurityUser fromUser( User user) {
		return new SecurityUser(user.getEmail(), user.getPassword(),
				 user.getRole().getAuthorities(),user.getStatus().equals(Status.ACTIVE));
	}

}
