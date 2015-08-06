package com.oocl.o2o.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.o2o.dao.impl.FoodDaoImpl;
import com.oocl.o2o.pojo.Food;
import com.oocl.o2o.pojo.User;
import com.oocl.o2o.util.Constants;
import com.oocl.o2o.util.FileUploadHelper;

/**
 * Servlet implementation class NewFoodServlet
 */
public class NewFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties config = new Properties();
		config.load(new FileInputStream(getServletContext().getRealPath("/") + "config.ini"));

		String tempPath = getServletContext().getRealPath(config.getProperty("tempPath"));
		String fileName = UUID.randomUUID() + ".jpg";
		String filePath = getServletContext().getRealPath(config.getProperty("uploadPath") + "food/" + fileName);
		FileUploadHelper helper = new FileUploadHelper(request);
		Map<String, String> params = helper.getParams();
		Map<String, String> errors = new HashMap<String, String>();

		if (errors.size() == 0) {
			User user = (User) request.getSession().getAttribute("user");

			FoodDaoImpl daoImpl = new FoodDaoImpl();
			Food food = new Food();
			food.setFoodName(params.get("name"));
			food.setPrice(Double.parseDouble(params.get("price")));
			food.setFoodTypeId(Integer.parseInt(params.get("type")));
			food.setPictureUrl(params.get("picture"));
			food.setStatusId(Constants.STSTUS_APPROVING);
			food.setUserId(user.getUserId());
			food.setPictureUrl(fileName);

			if (daoImpl.addFood(food) > 0) {
				helper.saveFile(tempPath, filePath);
				response.sendRedirect("/O2O_Seller/main/food.jsp?type=" + food.getFoodTypeId());
			} else {
				response.getWriter().print("<script>alert('Error');location.href='/O2O_Seller/main/newFood.jsp';</script>");
			}
		} else {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/main/newFood.jsp").forward(request, response);
		}
	}

}
