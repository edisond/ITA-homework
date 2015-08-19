package com.kary.spring.dao;

import java.util.List;

import com.kary.spring.dao.util.SearchCriteria;
import com.kary.spring.pojo.User;

/**
 * @author edisond@qq.com
 * @version 15-08-05
 */
public interface IUserDao {

	Integer insert(User user);

	Integer insertAll(List<User> users);

	Integer delete(User user);

	Integer deleteAll(List<User> users);

	Integer update(User user);

	Integer updateAll(List<User> users);

	List<User> findAll();

	List<User> findAllByCriteria(SearchCriteria criteria);

	User findByNameAndPassword(String name, String password);
}
