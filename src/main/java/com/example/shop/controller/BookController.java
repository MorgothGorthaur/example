package com.example.shop.controller;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.model.Book;

@RestController
@RequestMapping("/books")
public class BookController {
	@GetMapping
	@PreAuthorize("permitAll()")
	public Map<String, String> getAllBooks(){
		return null;
	}
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('read')")
	public Book getBookInfo(@PathVariable Long id) {
		return null;
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('write')")
	public Long addBook(Authentication authentication,Book book) {
		return null;
	}
	
	@DeleteMapping
	@PreAuthorize("hasAuthority('write')")
	public Long deleteBook(Authentication authentication,Long id) {
		return null;
	}
	
	@GetMapping("/{id}/update")
	@PreAuthorize("hasAuthority('write')")
	public Book getFullBookInfo(@PathVariable Long id) {
		return null;
	}
	@PatchMapping
	@PreAuthorize("hasAuthority('write')")
	public Long updateBook(Authentication authentication,Book book) {
		return null;
	}
	
}
