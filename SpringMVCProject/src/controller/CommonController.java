package controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import pojo.Book;
import pojo.Packet;
import pojo.User;

@RestController
@RequestMapping("/api")
@SessionAttributes(value = { "user" })
public class CommonController {

	private List<Book> books;

	public CommonController() {
		books = new LinkedList<>();
		books.add(new Book(1, "book1", "123", "Spring"));
		books.add(new Book(2, "book2", "123", "Spring"));
		books.add(new Book(3, "book3", "123", "Spring"));
		books.add(new Book(4, "book4", "123", "Spring"));
	}

	@RequestMapping(value = "/login")
	public Packet register(User user, Model model, HttpSession session) {
		Packet packet = new Packet();
		if (user.getName().equals("admin") && user.getPassword().equals("admin")) {
			model.addAttribute("user", user);
			session.setAttribute("books", books);
			packet.setStatus("success");
		} else {
			packet.setStatus("fail");
			packet.setMessage("Wrong user name/password.");
		}
		return packet;
	}

	@RequestMapping("/get/book")
	public List<Book> book() {
		return books;
	}

	@RequestMapping("/get/book/{id}")
	public List<Book> book(@PathVariable Integer id) {
		for (Book book : books) {
			if (book.getId() == id) {
				List<Book> temp = new LinkedList<>();
				temp.add(book);
				return temp;
			}
		}
		return null;
	}

	@RequestMapping(value = "/new/book", method = RequestMethod.POST)
	public Packet newBook(Book book) {
		book.setId(books.size());
		books.add(book);
		Packet packet = new Packet();
		packet.setMessage(book);
		System.out.println(book.getId());
		packet.setStatus("success");
		return packet;
	}

	@RequestMapping(value = "/delete/book")
	public Packet deleteBook(Integer id) {
		Packet packet = new Packet();
		packet.setStatus("fail");
		for (Book book : books) {
			if (book.getId() == id) {
				books.remove(book);
				packet.setStatus("success");
				break;
			}
		}
		return packet;
	}

	@RequestMapping(value = "/update/book")
	public Packet updateBook(Book book) {
		Packet packet = new Packet();
		packet.setStatus("fail");
		for (int i=0;i<books.size();i++) {
			if (books.get(i).getId() == book.getId()) {
				books.set(i, book);
				packet.setStatus("success");
				packet.setMessage(book);
				break;
			}
		}
		return packet;
	}
}
