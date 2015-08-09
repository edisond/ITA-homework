package com.oocl.o2o.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.o2o.dao.impl.FoodDao;
import com.oocl.o2o.dao.impl.ImageDao;
import com.oocl.o2o.pojo.Food;
import com.oocl.o2o.pojo.Image;
import com.oocl.o2o.pojo.User;
import com.oocl.o2o.util.Constants;
import com.oocl.o2o.util.FileUploadHelper;
import com.oocl.o2o.util.JmsUtil;

/**
 * Servlet implementation class NewFoodServlet
 */
public class NewFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = UUID.randomUUID().toString();
		FileUploadHelper helper = new FileUploadHelper(request);
		Map<String, String> params = helper.getParams();
		Map<String, String> errors = new HashMap<String, String>();

		if (errors.size() == 0) {
			User user = (User) request.getSession().getAttribute("user");

			FoodDao dao = new FoodDao();
			ImageDao imageDao = new ImageDao();

			Food food = new Food();
			food.setFoodName(params.get("name"));
			food.setPrice(Double.parseDouble(params.get("price")));
			food.setFoodTypeId(Integer.parseInt(params.get("type")));
			food.setPictureUrl(params.get("picture"));
			food.setStatusId(Constants.STSTUS_APPROVING);
			food.setUserId(user.getUserId());

			byte[] imgBody = helper.getFile();
			if (imgBody != null) {
				food.setPictureUrl(fileName);
				imageDao.insert(new Image(fileName, imgBody));
			}
			if (dao.insert(food) > 0) {
				response.sendRedirect("/O2O_Seller/main/food.jsp?type=" + food.getFoodTypeId());
				JmsUtil.produce("msg");
			} else {
				response.getWriter().print("<script>alert('Error');location.href='/O2O_Seller/main/newFood.jsp';</script>");
			}
		} else {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/main/newFood.jsp").forward(request, response);
		}
	}

}
