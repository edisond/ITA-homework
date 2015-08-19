package com.kary.spring.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kary.spring.pojo.Book;
import com.kary.spring.pojo.Packet;
import com.kary.spring.pojo.User;
import com.kary.spring.service.BookService;
import com.kary.spring.service.UserService;

@RestController
@RequestMapping("/api")
public class CommonController {

	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "bookService")
	private BookService bookService;

	@RequestMapping("/login")
	public Packet login(User user, HttpSession session) {
		Packet packet = new Packet();
		user = userService.doLogin(user.getName(), user.getPassword());
		if (user != null) {
			session.setAttribute("user", user);
			packet.setStatus("success");
		} else {
			packet.setStatus("fail");
			packet.setMessage("Wrong user name/password.");
		}
		return packet;
	}

	@RequestMapping("/get/book")
	public List<Book> book() {
		return bookService.findAll();
	}

	@RequestMapping("/get/book/{id}")
	public Book book(@PathVariable Integer id) {
		return bookService.findById(id);
	}

	@RequestMapping("/new/book")
	public Packet newBook(Book book) {
		Integer id = bookService.insert(book);
		Packet packet = new Packet();
		if (id > 0) {
			book.setId(id);
			packet.setMessage(book);
			packet.setStatus("success");
		} else {
			packet.setStatus("fail");
		}
		return packet;
	}

	@RequestMapping("/delete/book")
	public Packet deleteBook(Book book) {
		Packet packet = new Packet();
		if (bookService.delete(book) > 0) {
			packet.setStatus("success");
		} else {
			packet.setStatus("fail");
		}
		return packet;
	}

	@RequestMapping("/update/book")
	public Packet updateBook(Book book) {
		Packet packet = new Packet();
		if (bookService.update(book) > 0) {
			packet.setStatus("success");
			packet.setMessage(book);
		} else {
			packet.setStatus("fail");
		}
		return packet;
	}
}
