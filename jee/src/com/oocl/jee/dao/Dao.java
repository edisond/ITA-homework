package com.oocl.jee.dao;

import java.util.List;

public interface Dao<T> {
	public List<T> findAll();

	public List<T> findAll(int start, int length);

	public T find(int id);

	public int add(T t);

	public int[] addAll(List<T> list);

	public int delete(int id);

	public int update(T t);
}
