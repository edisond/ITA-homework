package com.kary.spring.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.kary.spring.dao.IBookDao;
import com.kary.spring.dao.IDb;
import com.kary.spring.dao.util.SearchCriteria;
import com.kary.spring.pojo.Book;

public class BookDao implements IBookDao {

	private IDb db;

	public BookDao(IDb db) {
		super();
		this.db = db;
	}

	@Override
	public Integer insert(Book book) {
		Integer id = -1;
		try {
			Connection connection = db.connect();
			String sql = "insert into books (name,isbn,category) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, book.getName());
			statement.setString(2, book.getIsbn());
			statement.setString(3, book.getCategory());
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.disconnect();
		}
		return id;
	}

	@Override
	public Integer insertAll(List<Book> books) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Book book) {
		Integer result = 0;
		try {
			Connection connection = db.connect();
			String sql = "delete from books where id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, book.getId());
			result = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.disconnect();
		}
		return result;
	}

	@Override
	public Integer deleteAll(List<Book> books) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Book book) {
		Integer result = 0;
		try {
			Connection connection = db.connect();
			String sql = "update books set name=?,isbn=?,category=? where id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, book.getName());
			statement.setString(2, book.getIsbn());
			statement.setString(3, book.getCategory());
			statement.setInt(4, book.getId());
			result = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.disconnect();
		}
		return result;
	}

	@Override
	public Integer updateAll(List<Book> books) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findAll() {
		List<Book> books = new LinkedList<>();
		try {
			Connection connection = db.connect();
			String sql = "SELECT * FROM books";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				books.add(buildBookFromResultSet(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.disconnect();
		}
		return books;
	}

	@Override
	public List<Book> findAllByCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book findById(Integer id) {
		Book book = null;
		try {
			Connection connection = db.connect();
			String sql = "SELECT * FROM books where id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				book = buildBookFromResultSet(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.disconnect();
		}
		return book;
	}

	private Book buildBookFromResultSet(ResultSet resultSet) {
		Book book = new Book();
		try {
			book.setId(resultSet.getInt("id"));
			book.setName(resultSet.getString("name"));
			book.setIsbn(resultSet.getString("isbn"));
			book.setCategory(resultSet.getString("category"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

}
