package com.example.BookStoreApi;

import com.example.BookStoreApi.dto.BookDTO;
import com.example.BookStoreApi.assembler.BookModelAssembler;
import com.example.BookStoreApi.model.BookModel;
import com.example.BookStoreApi.Book;  // Ensure Book is correctly imported
import com.example.BookStoreApi.BookRepository;  // Ensure BookRepository is correctly imported
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final BookModelAssembler assembler;

    public BookController(BookRepository bookRepository, BookModelAssembler assembler) {
        this.bookRepository = bookRepository;
        this.assembler = assembler;
    }

    @GetMapping(produces = { "application/json", "application/xml" })
    public ResponseEntity<CollectionModel<EntityModel<BookModel>>> getAllBooks() {
        List<EntityModel<BookModel>> books = bookRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<BookModel>> collectionModel = CollectionModel.of(books,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withSelfRel());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "AllBooksHeader");
        return new ResponseEntity<>(collectionModel, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
    public ResponseEntity<EntityModel<BookModel>> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);

        return book.map(assembler::toModel)
                .map(entityModel -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Custom-Header", "BookDetailsHeader");
                    return ResponseEntity.ok().headers(headers).body(entityModel);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/search", produces = { "application/json", "application/xml" })
    public ResponseEntity<CollectionModel<EntityModel<BookModel>>> searchBooks(@RequestParam(required = false) String title,
                                                                               @RequestParam(required = false) String author) {
        List<EntityModel<BookModel>> filteredBooks = bookRepository.findAll().stream()
                .filter(book -> (title == null || book.getTitle().contains(title)) &&
                        (author == null || book.getAuthor().contains(author)))
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<BookModel>> collectionModel = CollectionModel.of(filteredBooks,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).searchBooks(title, author)).withSelfRel());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "SearchResultsHeader");
        return new ResponseEntity<>(collectionModel, headers, HttpStatus.OK);
    }

    @PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    public ResponseEntity<EntityModel<BookModel>> createBook(@RequestBody BookDTO bookDTO) {
        Book book = new Book(null, bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPrice(), bookDTO.getIsbn());
        Book savedBook = bookRepository.save(book);

        EntityModel<BookModel> entityModel = assembler.toModel(savedBook);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/books/" + savedBook.getId()); // Set Location header
        return new ResponseEntity<>(entityModel, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    public ResponseEntity<EntityModel<BookModel>> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Optional<Book> existingBookOpt = bookRepository.findById(id);

        return existingBookOpt.map(existingBook -> {
            existingBook.setTitle(bookDTO.getTitle());
            existingBook.setAuthor(bookDTO.getAuthor());
            existingBook.setPrice(bookDTO.getPrice());
            existingBook.setIsbn(bookDTO.getIsbn());

            Book updatedBook = bookRepository.save(existingBook);

            EntityModel<BookModel> entityModel = assembler.toModel(updatedBook);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Update-Status", "Book Updated");
            return ResponseEntity.ok().headers(headers).body(entityModel);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Deletion-Status", "Book Deleted");
            return ResponseEntity.noContent().headers(headers).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/book/{id}", produces = { "application/json", "application/xml" })
    public ResponseEntity<EntityModel<BookModel>> getBook(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);

        return book.map(assembler::toModel)
                .map(entityModel -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Custom-Header", "SingleBookHeader");
                    return ResponseEntity.ok().headers(headers).body(entityModel);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
