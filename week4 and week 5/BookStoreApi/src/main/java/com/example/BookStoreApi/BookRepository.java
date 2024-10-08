package com.example.BookStoreApi;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Find books by title
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Find books by author
    List<Book> findByAuthorContainingIgnoreCase(String author);
}
