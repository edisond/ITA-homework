package com.kary.spring.dao;

import java.sql.Connection;

public interface IDb {
	public Connection connect();

	public void disconnect();
}
