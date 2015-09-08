package com.wxsm.jee.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxsm.jee.pojo.User;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		@SuppressWarnings("unchecked")
		Set<Integer> onlineUserIds = (Set<Integer>) application.getAttribute("onlineUserIds");
		if (onlineUserIds == null) {
			onlineUserIds = new HashSet<Integer>();
		}
		User user = (User) request.getSession().getAttribute("user");
		onlineUserIds.remove(user.getId());
		application.setAttribute("onlineUserIds", onlineUserIds);

		request.getSession().invalidate();
		response.sendRedirect("index.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
