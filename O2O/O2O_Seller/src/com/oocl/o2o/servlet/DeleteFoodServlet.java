package com.oocl.o2o.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oocl.o2o.dao.impl.FoodDao;
import com.oocl.o2o.pojo.Food;
import com.oocl.o2o.pojo.Packet;

/**
 * Servlet implementation class DeleteFoodServlet
 */
public class DeleteFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		FoodDao dao = new FoodDao();
		Food food = dao.findById(id);
		Packet packet=new Packet();
		if(dao.delete(food)>0){
			packet.setStatus("success");
		}else{
			packet.setStatus("fail");
		}
		Gson gson=new Gson();
		String json=gson.toJson(packet);
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
