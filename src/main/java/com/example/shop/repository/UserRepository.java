package com.example.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shop.model.User;




public interface UserRepository extends JpaRepository <User, Long> {
		Optional<User> findByEmail(String email);
		Optional<User> findById(Long id);

}
