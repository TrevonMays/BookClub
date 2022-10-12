package beltexam.test.controllers;


import beltexam.test.models.Book;
import beltexam.test.models.User;
import beltexam.test.services.BookService;
import beltexam.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
///////////////////////////////CREATE///////////////////////////////
    @GetMapping("/books/new")
    public String newBook(@ModelAttribute("book") Book book, Model model, HttpSession session){
        Long id = (Long) session.getAttribute("userId");
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "books/new.jsp";
    }
    @PostMapping("/books")
    public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "books/new.jsp";
        } else {
            bookService.create(book);
            return "redirect:/books";
        }
    }
    ///////////////////////////////READ ALL///////////////////////////////
    @GetMapping("/books")
    public String allBooks(Model model, HttpSession session){
       List<Book> books = bookService.getAll();
        Long id = (Long) session.getAttribute("userId");
        User user = (User) userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("books", books);
        return "books/index.jsp";
    }

    ///////////////////////////////READ ONE///////////////////////////////
    @GetMapping("/books/{id}")
    public String show(HttpSession session, Model model, @PathVariable Long id){
        Book book = bookService.getOne(id);
        model.addAttribute("book", book);
        Long userId = (Long) session.getAttribute("userId");
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "books/show.jsp";
    }

    ///////////////////////////////EDIT///////////////////////////////

    @GetMapping("/books/edit/{id}")
    public String edit(@PathVariable("id")Long id, Model model, HttpSession session){
        Book book = bookService.getOne(id);
        model.addAttribute("book", book);

        Long userId = (Long) session.getAttribute("userId");
        User user = userService.findById(userId);

        model.addAttribute("user", user);
        return "books/edit.jsp";
    }

    @PutMapping("/books/{id}")
    public String updateBook(@Valid @ModelAttribute("book")Book book, BindingResult result, @PathVariable("id")Long id){
        if(result.hasErrors()){
            return "books/edit.jsp";
        } else {
            bookService.update(book);
            return "redirect:/books";
        }
    }
    ///////////////////////////////DELETE///////////////////////////////
    @DeleteMapping("/books/destroy/{id}")
    public String destroy(@PathVariable("id")Long id){
        bookService.destroy(bookService.getOne(id));
        return "redirect:/books";
    }
}
