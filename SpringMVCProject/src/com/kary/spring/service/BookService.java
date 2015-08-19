package com.kary.spring.service;

import java.util.List;

import com.kary.spring.dao.IBookDao;
import com.kary.spring.pojo.Book;

public class BookService {
	private IBookDao dao;

	public BookService(IBookDao dao) {
		super();
		this.dao = dao;
	}

	public List<Book> findAll() {
		return dao.findAll();
	}

	public Book findById(Integer id) {
		return dao.findById(id);
	}

	public Integer update(Book book) {
		return dao.update(book);
	}

	public Integer insert(Book book) {
		return dao.insert(book);
	}

	public Integer delete(Book book) {
		return dao.delete(book);
	}
}
