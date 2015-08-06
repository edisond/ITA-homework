package com.oocl.o2o.dao;

import java.util.List;

import com.oocl.o2o.pojo.User;
import com.oocl.o2o.util.SearchCriteria;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public interface UserDao {
	int addUser(User user);

	int deleteUser(User user);

	int deleteUserList(List<User> userList);

	int updateUser(User user);

	int updateUserList(List<User> userList);

	User getById(Integer userId);

	User findByName(String userName);

	List<User> findAll();

	List<User> findByCriteria(SearchCriteria searchCriteria);

}
