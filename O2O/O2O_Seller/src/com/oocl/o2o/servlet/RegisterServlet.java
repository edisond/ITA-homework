package com.oocl.o2o.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.o2o.pojo.User;
import com.oocl.o2o.service.UserService;
import com.oocl.o2o.util.FileUploadHelper;
import com.oocl.o2o.validator.impl.LengthValidator;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties config = new Properties();
		config.load(new FileInputStream(getServletContext().getRealPath("/") + "config.ini"));
		
		String tempPath = getServletContext().getRealPath(config.getProperty("tempPath"));
		String fileName = UUID.randomUUID() + ".jpg";
		String filePath = getServletContext().getRealPath(config.getProperty("uploadPath") + "license/" + fileName);
		FileUploadHelper helper = new FileUploadHelper(request);
		Map<String, String> params = helper.getParams();

		Map<String, String> errorMap = new HashMap<String, String>();
		if (!new LengthValidator(4, 8).validate(params.get("name"))) {
			errorMap.put("name", "Name: 4-8 chars");
		}
		if (!new LengthValidator(6).validate(params.get("psw"))) {
			errorMap.put("psw", "Password: 6-n chars");
		}
		if (params.get("psw") == null || params.get("confirmPsw") == null || !params.get("psw").equals(params.get("confirmPsw"))) {
			errorMap.put("confirmPsw", "Passwords do not match");
		}
		if (!new LengthValidator(11, 11).validate(params.get("tel"))) {
			errorMap.put("tel", "Tel: 11 chars");
		}
		if (!new LengthValidator(8, 8).validate(params.get("idCard"))) {
			errorMap.put("idCard", "Id Card: 8 chars");
		}

		if (errorMap.size() == 0) {
			User user = new User();
			user.setIdCard(params.get("idCard"));
			user.setUserName(params.get("name"));
			user.setPassWord(params.get("psw"));
			user.setTel(params.get("tel"));
			user.setStatusId(2);
			user.setRole("seller");
			user.setLicense(fileName);
			
			if (UserService.doRegister(user)) {
				helper.saveFile(tempPath, filePath);
				request.getSession().setAttribute("user", user);
				response.sendRedirect("/O2O_Seller/main/index.jsp");
			} else {
				response.getWriter().print("<script>alert('Error');location.href='/O2O_Seller/register.jsp';</script>");
			}
		} else {
			request.setAttribute("errorMap", errorMap);
			request.setAttribute("name", params.get("name"));
			request.setAttribute("psw", params.get("psw"));
			request.setAttribute("pswConfirm", params.get("pswConfirm"));
			request.setAttribute("tel", params.get("tel"));
			request.setAttribute("idCard", params.get("idCard"));
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}

	}

}
