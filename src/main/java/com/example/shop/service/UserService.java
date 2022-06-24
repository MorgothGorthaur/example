package com.example.shop.service;

import java.util.Map;



import com.example.shop.model.User;



public interface UserService {
	public Long addOrUpdate(User user);
	public Map <Long, String> getAllSellersInfo();
	public User getSellerInfo(Long id);
	public User getAllUserInfo(String email);
	public void updateUser(String email, User user);
}
