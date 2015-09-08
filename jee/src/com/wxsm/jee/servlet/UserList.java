package com.wxsm.jee.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxsm.jee.pojo.User;
import com.wxsm.jee.service.UserMng;

public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		List<User> users = UserMng.getAllUsers();
		ServletContext application = this.getServletContext();
		@SuppressWarnings("unchecked")
		Set<Integer> onlineUserIds = (Set<Integer>) application.getAttribute("onlineUserIds");
		if (onlineUserIds == null) {
			onlineUserIds = new HashSet<Integer>();
		}
		for (User u : users) {
			u.setOnline(onlineUserIds.contains(u.getId()));
		}
		request.getSession().setAttribute("users", users);
		response.sendRedirect("main.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
