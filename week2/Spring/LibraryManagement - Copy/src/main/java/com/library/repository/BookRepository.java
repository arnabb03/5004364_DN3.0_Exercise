package com.library.repository;

import java.util.Arrays;
import java.util.List;

// Consider adding an interface to define repository methods
public class BookRepository {

    // Constructor (if needed for future extensions)
    public BookRepository() {
        // Initialization if necessary
    }

    // Method to retrieve a list of books
    public List<String> getBooks() {
        return Arrays.asList("Book 1", "Book 2", "Book 3");
    }
}
