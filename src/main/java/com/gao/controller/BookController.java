package com.gao.controller;

import com.gao.pojo.Books;
import com.gao.service.BookService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    // controller 调用service层
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @RequestMapping("/allBook")
    //查询全部书籍，并且返回一个书籍展示页面
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list", list);
        return "allBook";
    }

    @RequestMapping("/toAddBook")
    public String toAddBook() {
        return "addBook";
    }

    @RequestMapping("/addBook")
    public String addBook(Books books) {
        System.out.println("addBook=>" + books);
        bookService.addBook(books);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(int bookId, Model model) {
//        System.out.println(book);
        Books book = bookService.queryBookById(bookId);
        model.addAttribute("book", book);
        return "updateBook";
    }

    @RequestMapping("/updateBook")
    public String updateBook(Books books) {
        bookService.updateBook(books);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(int bookId) {
        bookService.deleteBookById(bookId);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/selectByName")
    public String selectByName(String bookName,Model model){
//        final Books book = bookService.queryBookByName(bookName);
        List<Books> list = bookService.queryBookByName(bookName);
//        list.add(book);
        model.addAttribute("list",list);
        return "allBook";
    }

}
