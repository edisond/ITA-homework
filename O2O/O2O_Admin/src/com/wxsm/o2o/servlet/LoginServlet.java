package com.wxsm.o2o.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oocl.o2o.pojo.User;
import com.wxsm.o2o.service.UserService;
import com.wxsm.o2o.util.Constants;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String psw = request.getParameter("psw");
		HttpSession session = request.getSession();

		User user = UserService.doLogin(name, psw);

		if (user != null && user.getRole().equals(Constants.ROLE_Admin)) {
			session.setAttribute("user", user);
			response.sendRedirect("/O2O_Admin/main/index.jsp");
		} else {
			request.setAttribute("errMsg", "Wrong user name/password.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
