package com.kary.spring.dao;

import java.util.List;

import com.kary.spring.dao.util.SearchCriteria;
import com.kary.spring.pojo.Book;

/**
 * @author edisond@qq.com
 * @version 15-08-05
 */
public interface IBookDao {

	Integer insert(Book book);

	Integer insertAll(List<Book> books);

	Integer delete(Book book);

	Integer deleteAll(List<Book> books);

	Integer update(Book book);

	Integer updateAll(List<Book> books);

	List<Book> findAll();

	List<Book> findAllByCriteria(SearchCriteria criteria);

	Book findById(Integer id);
}
