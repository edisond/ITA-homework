package com.wxsm.o2o.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.o2o.dao.impl.FoodDao;
import com.oocl.o2o.pojo.Food;

/**
 * Servlet implementation class DeleteFoodServlet
 */
public class DeleteFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		FoodDao dao = new FoodDao();
		Food food = dao.findById(id);
		dao.delete(food);
		response.sendRedirect("/O2O_Admin/main/food.jsp?type=" + food.getFoodTypeId());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
