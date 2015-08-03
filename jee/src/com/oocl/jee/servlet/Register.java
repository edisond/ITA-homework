package com.oocl.jee.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.jee.pojo.User;
import com.oocl.jee.service.UserMng;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		final String WRONG_MSG_HEAD = "Check your form:\\r\\n";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

		String name = request.getParameter("name");
		String psw = request.getParameter("psw");
		String psw2 = request.getParameter("psw2");
		String nickname = request.getParameter("nickname");
		String idcard = request.getParameter("idcard");
		String email = request.getParameter("email");
		Boolean sex = new Boolean(request.getParameter("sex"));
		Date birth = null;
		try {
			birth = sdf.parse(request.getParameter("birth"));
		} catch (Exception e) {

		}

		String wrongMsg = WRONG_MSG_HEAD;
		if (name == null || name.length() < 4 || name.length() > 8) {
			wrongMsg += "Name: 4-8 chars\\r\\n";
		}
		if (psw == null || psw.length() < 6) {
			wrongMsg += "Password: 6-n chars\\r\\n";
		}
		if (psw == null || psw2 == null || !psw.equals(psw2)) {
			wrongMsg += "Passwords do not match\\r\\n";
		}
		if (birth == null || birth.getTime() > new Date().getTime()) {
			wrongMsg += "Date: 1900-now\\r\\n";
		}
		if (nickname == null || nickname.length() < 1 || nickname.length() > 8) {
			wrongMsg += "Nickname: 1-8 chars\\r\\n";
		}
		if (idcard == null || idcard.length() != 8) {
			wrongMsg += "Id Card: 8 chars\\r\\n";
		}
		if (email == null || !emailPattern.matcher(email).matches()) {
			wrongMsg += "Email: Illegal format\\r\\n";
		}

		if (wrongMsg.equals(WRONG_MSG_HEAD)) {
			User user = new User();
			user.setBirth(birth);
			user.setEmail(email);
			user.setIdCard(idcard);
			user.setName(name);
			user.setPassword(psw);
			user.setSex(sex);
			user.setNickName(nickname);
			if (UserMng.doRegister(user)) {
				request.getSession().setAttribute("user", user);
				response.sendRedirect("success.jsp");
			} else {
				response.getWriter().print("<script>alert('Error');location.href='register.jsp';</script>");
			}
		} else {
			response.getWriter().print("<script>alert('" + wrongMsg + "');location.href='register.jsp';</script>");
		}

	}
}
