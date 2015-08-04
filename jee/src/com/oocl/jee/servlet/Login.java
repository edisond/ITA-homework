package com.oocl.jee.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.jee.pojo.User;
import com.oocl.jee.service.UserMng;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		String name = request.getParameter("name");
		String psw = request.getParameter("psw");
		User user = UserMng.doLogin(name, psw);

		if (user == null) {
			response.getWriter().print(
					"<script>alert('Wrong user name/password.');location.href='index.html';</script>");
		} else {
			ServletContext application = this.getServletContext();
			@SuppressWarnings("unchecked")
			Set<Integer> onlineUserIds = (Set<Integer>) application.getAttribute("onlineUserIds");
			if (onlineUserIds == null) {
				onlineUserIds = new HashSet<Integer>();
			}
			onlineUserIds.add(user.getId());
			application.setAttribute("onlineUserIds", onlineUserIds);
			
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("UserList").forward(request, response);
		}
	}

}
