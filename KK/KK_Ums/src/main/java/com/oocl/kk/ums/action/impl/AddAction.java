package com.oocl.kk.ums.action.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.oocl.kary.pojo.User;
import com.oocl.kk.dao.impl.UserDaoImpl;
import com.oocl.kk.ums.action.Action;
import com.oocl.kk.ums.controller.MainController;
import com.oocl.kk.ums.service.Displayer;

public class AddAction implements Action {

	@Override
	public void execute(String[] params, MainController controller) {
		UserDaoImpl dao = new UserDaoImpl();
		Displayer displayer = new Displayer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User c = new User();
		c.setPassword("123");
		c.setAddr("Earth");
		c.setName("newUser");
		c.setSex("m");
		c.setTel("110");
		c.setBirth(new Date());
		Class<? extends User> cls = c.getClass();
		Field[] fields = cls.getDeclaredFields();
		try {
			for (String param : params) {
				String key = "", value = "";
				key = param.substring(0, param.indexOf(":")).trim();
				value = param.substring(param.indexOf(":") + 1).trim();
				for (Field field : fields) {
					field.setAccessible(true);
					if (field.getName().equals(key)) {
						if (field.getName().equals("birth")) {
							field.set(c, sdf.parse(value));
						} else {
							field.set(c, value);
						}

					}

				}
			}
			dao.add(c);
			displayer.success();
		} catch (Exception e) {
			displayer.error("键值对的格式不符合要求");
		}

	}

}
