package com.example.shop.controller;



import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.shop.model.User;
import com.example.shop.service.UserService;




@RestController
@RequestMapping("/users")
public class UserController {
	
	
	@Autowired 
	private UserService userService;
	@PostMapping
	public Long registration(User user) {
		return userService.addOrUpdate(user);
	}
	@GetMapping
	public Map <Long, String> list()
	{
		return userService.getAllSellersInfo();
	}
	@GetMapping("/userinfo")
	public User getUserInfo( Long id) {
		return userService.getSellerInfo(id);
	}
	@PreAuthorize("hasAuthority('read')")
	@GetMapping("userinfo/update")
	public User getUpdateUserInfo(Authentication authentication) {
		return userService.getAllUserInfo(authentication.getName());
		  
	}
	@PreAuthorize("hasAuthority('read')")
	@PatchMapping("userinfo/update")
	public String updateUser(Authentication authentication, User user) {
		userService.updateUser(authentication.getName(), user);
		return "redirect:userinfo/update";
		  
	}
	@PreAuthorize("hasAuthority('read')")
	@DeleteMapping
	public String delete(Authentication authentication) {
		return "Del";
	}
	
}
