package com.example.shop.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shop.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
}
