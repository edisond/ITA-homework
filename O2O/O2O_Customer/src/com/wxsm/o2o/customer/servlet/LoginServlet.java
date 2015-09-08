package com.wxsm.o2o.customer.servlet;

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
import com.wxsm.o2o.customer.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String psw = request.getParameter("psw");
		HttpSession session = request.getSession();

		User user = UserService.doLogin(name, psw);

		Gson gson = new Gson();
		Packet packet = new Packet();
		PrintWriter writer = response.getWriter();

		if (user != null) {
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
