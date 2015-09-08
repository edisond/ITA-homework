package com.wxsm.o2o.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oocl.o2o.dao.impl.ImageDao;
import com.oocl.o2o.pojo.Image;
import com.oocl.o2o.pojo.Packet;
import com.oocl.o2o.pojo.User;
import com.wxsm.o2o.service.UserService;
import com.wxsm.o2o.util.FileUploadHelper;
import com.wxsm.o2o.util.JmsUtil;
import com.wxsm.o2o.validator.impl.LengthValidator;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = UUID.randomUUID().toString();
		FileUploadHelper helper = new FileUploadHelper(request);
		Map<String, String> params = helper.getParams();
		boolean validate = true;

		if (!new LengthValidator(4, 8).validate(params.get("name"))) {
			validate = false;
		}
		if (!new LengthValidator(6).validate(params.get("psw"))) {
			validate = false;
		}
		if (params.get("psw") == null || params.get("confirmPsw") == null || !params.get("psw").equals(params.get("confirmPsw"))) {
			validate = false;
		}
		if (!new LengthValidator(11, 11).validate(params.get("tel"))) {
			validate = false;
		}
		if (!new LengthValidator(8, 8).validate(params.get("idCard"))) {
			validate = false;
		}

		Gson gson = new Gson();
		Packet packet = new Packet();
		PrintWriter writer = response.getWriter();

		if (validate) {
			User user = new User();
			user.setIdCard(params.get("idCard"));
			user.setUserName(params.get("name"));
			user.setPassWord(params.get("psw"));
			user.setTel(params.get("tel"));
			user.setStatusId(2);
			user.setRole("seller");
			byte[] imgBody = helper.getFile();
			if (imgBody != null) {
				user.setLicense(fileName);
				new ImageDao().insert(new Image(fileName, imgBody));
			}
			if (UserService.doRegister(user)) {
				request.getSession().setAttribute("user", user);
				packet.setStatus("success");
				JmsUtil.produce("msg");
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
