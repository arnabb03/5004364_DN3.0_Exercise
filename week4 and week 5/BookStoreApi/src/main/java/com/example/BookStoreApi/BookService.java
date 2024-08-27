package com.example.BookStoreApi;

import com.example.BookStoreApi.Book;
import com.example.BookStoreApi.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book createBook(BookDTO bookDTO);

    Optional<Book> getBookById(Long id);

    List<Book> getAllBooks();

    Book updateBook(Long id, BookDTO bookDTO);

    void deleteBook(Long id);
}
