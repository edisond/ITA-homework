package com.wxsm.o2o.customer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oocl.o2o.pojo.Packet;
import com.oocl.o2o.pojo.User;
import com.oocl.o2o.util.Constants;
import com.wxsm.o2o.customer.service.UserService;
import com.wxsm.o2o.customer.validator.impl.LengthValidator;
/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		String name=request.getParameter("name");
		String psw=request.getParameter("psw");
		String confirmPsw=request.getParameter("confirmPsw");
		String tel=request.getParameter("tel");

		boolean validate = true;

		if (!new LengthValidator(4, 8).validate(name)) {
			validate = false;
		}
		if (!new LengthValidator(6).validate(psw)) {
			validate = false;
		}
		if (psw == null || confirmPsw == null || !psw.equals(confirmPsw)) {
			validate = false;
		}
		if (!new LengthValidator(11, 11).validate(tel)) {
			validate = false;
		}

		Gson gson = new Gson();
		Packet packet = new Packet();
		PrintWriter writer = response.getWriter();

		if (validate) {
			User user = new User();
			user.setUserName(name);
			user.setPassWord(psw);
			user.setTel(tel);
			user.setStatusId(1);
			user.setRole(Constants.ROLE_Customer);
			if (UserService.doRegister(user)) {
				request.getSession().setAttribute("user", user);
				packet.setStatus("success");
				//JmsUtil.produce("msg");
			} else {
				packet.setStatus("fail");
				packet.setMessage("ERROR");
			}
		} else {
			packet.setStatus("fail");
			packet.setMessage("ERROR");
		}

		String json=gson.toJson(packet);
		writer.write(json);
		writer.flush();
		writer.close();
	}

}
