package com.wxsm.jee.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxsm.jee.service.UserMng;

public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id").toString());
		UserMng.deleteUserById(id);
		ServletContext application = this.getServletContext();
		@SuppressWarnings("unchecked")
		Set<Integer> onlineUserIds = (Set<Integer>) application.getAttribute("onlineUserIds");
		if (onlineUserIds == null) {
			onlineUserIds = new HashSet<Integer>();
		}
		onlineUserIds.remove(id);
		request.getRequestDispatcher("UserList").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
