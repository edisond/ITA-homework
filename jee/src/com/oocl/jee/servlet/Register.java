package com.oocl.jee.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.jee.pojo.User;
import com.oocl.jee.service.UserMng;
import com.oocl.jee.validator.impl.LengthValidator;
import com.oocl.jee.validator.impl.RegexValidator;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> errorMap = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String name = request.getParameter("name");
		String psw = request.getParameter("psw");
		String psw2 = request.getParameter("psw2");
		String nickName = request.getParameter("nickName");
		String idCard = request.getParameter("idCard");
		String email = request.getParameter("email");
		Boolean sex = new Boolean(request.getParameter("sex"));
		Date birth = null;
		try {
			birth = sdf.parse(request.getParameter("birth"));
		} catch (Exception e) {
			errorMap.put("birth", "Birth format do not match.");
		}

		if (!new LengthValidator(4, 8).validate(name)) {
			errorMap.put("name", "Name: 4-8 chars");
		}
		if (!new LengthValidator(6).validate(psw)) {
			errorMap.put("psw", "Password: 6-n chars");
		}
		if (psw == null || psw2 == null || !psw.equals(psw2)) {
			errorMap.put("psw2", "Passwords do not match");
		}
		if (birth == null || birth.getTime() > new Date().getTime()) {
			errorMap.put("birth", "Birth: 1900-now");
		}
		if (!new LengthValidator(1, 8).validate(nickName)) {
			errorMap.put("nickName", "Nick Name: 1-8 chars");
		}
		if (!new LengthValidator(8, 8).validate(idCard)) {
			errorMap.put("idCard", "Id Card: 8 chars");
		}
		if (!new RegexValidator("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*").validate(email)) {
			errorMap.put("email", "Email: Illegal format");
		}

		if (errorMap.size() == 0) {
			User user = new User();
			user.setBirth(birth);
			user.setEmail(email);
			user.setIdCard(idCard);
			user.setName(name);
			user.setPassword(psw);
			user.setSex(sex);
			user.setNickName(nickName);
			if (UserMng.doRegister(user)) {
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("UserList").forward(request, response);
			} else {
				response.getWriter().print("<script>alert('Error');location.href='register.jsp';</script>");
			}
		} else {
			request.setAttribute("errorMap", errorMap);
			request.setAttribute("name", name);
			request.setAttribute("psw", psw);
			request.setAttribute("psw2", psw2);
			request.setAttribute("nickName", nickName);
			request.setAttribute("idCard", idCard);
			request.setAttribute("email", email);
			request.setAttribute("sex", sex);
			request.setAttribute("birth", request.getParameter("birth"));
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}

	}
}
