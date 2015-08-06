package com.oocl.o2o.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.o2o.dao.impl.FoodDaoImpl;
import com.oocl.o2o.pojo.Food;

/**
 * Servlet implementation class DeleteFoodServlet
 */
public class DeleteFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		FoodDaoImpl daoImpl = new FoodDaoImpl();
		Food food = daoImpl.findById(id);
		daoImpl.deleteFood(food);
		response.sendRedirect("/O2O_Seller/main/food.jsp?type=" + food.getFoodTypeId());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
