package com.oocl.o2o.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.oocl.o2o.dao.Dao;
import com.oocl.o2o.dao.Db;
import com.oocl.o2o.pojo.Order;
import com.oocl.o2o.util.SearchCriteria;

public class OrderDao extends Db implements Dao<Order> {

	@Override
	public Integer insert(Order t) {
		int result = 0;
		try {
			connect();
			String sql = "insert into orders (userid,ordertime) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			statement.setInt(1, t.getUserId());
			statement.setString(2, sdf.format(t.getOrderTime()));
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				result = rs.getInt(1);
				for (Integer key : t.getFoodMap().keySet()) {
					String sql1 = "insert into orders (orderID,FOODID,foodNum) values (?,?,?)";
					PreparedStatement statement1 = connection.prepareStatement(sql1);
					statement1.setInt(1, result);
					statement1.setInt(2, key);
					statement1.setInt(3, t.getFoodMap().get(key));
					statement1.executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	@Override
	public Integer insertAll(List<Order> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteAll(List<Order> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateAll(List<Order> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findAllByCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Order> findAllByUserId(Integer id) {
		List<Order> list = new LinkedList<Order>();
		try {
			connect();
			String sql = "select * from orders where userid=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("orderId"));
				order.setOrderTime(resultSet.getDate("orderTime"));
				order.setUserId(resultSet.getInt("userId"));

				String sql1 = "select * from orders_food where orderid=?";
				PreparedStatement statement1 = connection.prepareStatement(sql1);
				statement1.setInt(1, order.getOrderId());
				ResultSet resultSet1 = statement1.executeQuery();
				while (resultSet1.next()) {
					order.getFoodMap().put(resultSet1.getInt("foodid"), resultSet1.getInt("foodnum"));
				}

				list.add(order);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

}
