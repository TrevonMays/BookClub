package beltexam.test.services;


import beltexam.test.models.Book;
import beltexam.test.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;


    public void create(Book book) {
        bookRepository.save(book);

    }

    public List<Book> getAll() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book getOne(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    public void update(Book book) {
        bookRepository.save(book);
    }

    public void destroy(Book book) {
        bookRepository.delete(book);
    }



    public Book findBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }
}
