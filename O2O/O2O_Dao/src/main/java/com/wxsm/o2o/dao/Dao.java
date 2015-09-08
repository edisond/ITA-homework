package com.wxsm.o2o.dao;

import java.util.List;

import com.wxsm.o2o.util.SearchCriteria;

/**
 * @author edisond@qq.com
 * @version 15-08-05
 */
public interface Dao<T> {

	Integer insert(T t);

	Integer insertAll(List<T> t);

	Integer delete(T t);

	Integer deleteAll(List<T> t);

	Integer update(T t);

	Integer updateAll(List<T> t);

	List<T> findAll();

	List<T> findAllByCriteria(SearchCriteria criteria);
}
