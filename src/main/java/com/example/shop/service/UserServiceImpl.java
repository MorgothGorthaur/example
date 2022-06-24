package com.example.shop.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shop.model.User;
import com.example.shop.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Override
	public Long addOrUpdate(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User newUser = userRepository.save(user);
		return newUser.getId();
	}

	@Override
	public Map<Long, String> getAllSellersInfo() {
		Iterable <User> userIterable = userRepository.findAll();
		Map <Long, String> users= new HashMap <>();
		for ( User user : userIterable) {
			users.put(user.getId(), user.getNickName());
		}
		return users;

	}

	

	@Override
	public User getSellerInfo(Long id) {
		User user = userRepository.findById(id).orElseThrow(() ->
        new UsernameNotFoundException("User doesn't exists"));
		return user;
	}

	@Override
	public User getAllUserInfo(String email) {
		User user = userRepository.findByEmail(email).orElseThrow(() ->
        new UsernameNotFoundException("User doesn't exists"));
		return user;
	}

	@Override
	public void updateUser(String email, User user) {
		User updatedUser = userRepository.findByEmail(email).orElseThrow(() ->
        new UsernameNotFoundException("User doesn't exists"));
		 addOrUpdate(updatedUser);
		
	}

}
