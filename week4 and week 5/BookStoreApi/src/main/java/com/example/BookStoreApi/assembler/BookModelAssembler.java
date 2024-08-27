package com.example.BookStoreApi.assembler;

import com.example.BookStoreApi.BookController;
import com.example.BookStoreApi.Book;
import com.example.BookStoreApi.model.BookModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class BookModelAssembler {

    public EntityModel<BookModel> toModel(Book book) {
        BookModel bookModel = new BookModel(book.getId(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getIsbn());

        return EntityModel.of(bookModel,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBook(book.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("books"));
    }
}
