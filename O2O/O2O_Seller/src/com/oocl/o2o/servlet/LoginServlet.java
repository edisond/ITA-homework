package com.oocl.o2o.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oocl.o2o.pojo.Packet;
import com.oocl.o2o.pojo.User;
import com.oocl.o2o.service.UserService;
import com.oocl.o2o.util.Constants;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String psw = request.getParameter("psw");
		HttpSession session = request.getSession();

		User user = UserService.doLogin(name, psw);

		Gson gson = new Gson();
		Packet packet = new Packet();
		PrintWriter writer = response.getWriter();

		if (user != null && user.getRole().equals(Constants.ROLE_SELLER)) {
			session.setAttribute("user", user);
			packet.setStatus("success");
		} else {
			packet.setStatus("fail");
			packet.setMessage("Wrong user name/password.");
		}
		writer.write(gson.toJson(packet));
		writer.flush();
		writer.close();
	}

}

