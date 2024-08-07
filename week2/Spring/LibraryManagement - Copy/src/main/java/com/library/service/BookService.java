package com.library.service;
import org.springframework.stereotype.Service;
import com.library.repository.BookRepository;
import java.util.List;

public class BookService {
    private BookRepository bookRepository;

    // Setter method for dependency injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void displayBooks() {
        List<String> books = bookRepository.getBooks();
        if (books != null && !books.isEmpty()) {
            books.forEach(System.out::println);
        } else {
            System.out.println("No books available.");
        }
    }
}
