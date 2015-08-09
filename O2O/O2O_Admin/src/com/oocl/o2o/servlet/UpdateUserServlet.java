package com.oocl.o2o.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.o2o.dao.impl.StatusDao;
import com.oocl.o2o.dao.impl.UserDao;
import com.oocl.o2o.pojo.Status;
import com.oocl.o2o.pojo.User;

/**
 * Servlet implementation class UpdateUserServlet
 */
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		UserDao dao = new UserDao();
		User user = dao.findById(id);
		request.setAttribute("user", user);

		StatusDao daoImpl2 = new StatusDao();
		List<Status> list = daoImpl2.findAll();
		request.setAttribute("status", list);

		request.getRequestDispatcher("/main/updateUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
