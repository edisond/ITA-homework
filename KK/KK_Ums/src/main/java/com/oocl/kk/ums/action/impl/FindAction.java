package com.oocl.kk.ums.action.impl;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import com.oocl.kary.pojo.User;
import com.oocl.kk.dao.impl.UserDaoImpl;
import com.oocl.kk.ums.action.Action;
import com.oocl.kk.ums.controller.MainController;
import com.oocl.kk.ums.service.Displayer;

public class FindAction implements Action {

	@Override
	public void execute(String[] params, MainController controller) {
		UserDaoImpl dao = new UserDaoImpl();
		Displayer displayer = new Displayer();

		if (params.length == 0) {
			displayer.error("请指定查询条件");
		} else {
			params = params[0].split(" ");
			for (int i = 0; i < params.length; i++) {
				params[i] = params[i].trim();
			}
			Class<? extends User> cls = new User().getClass();
			Field[] fields = cls.getDeclaredFields();
			Field targetField = null;
			for (Field field : fields) {
				if (field.getName().equals(params[0])) {
					targetField = field;
					targetField.setAccessible(true);
				}
			}
			controller.setList(new LinkedList<User>());
			List<User> data = dao.findAll();
			for (int i = 0, j = data.size(); i < j; i++) {
				try {
					if (params[2].equals("eq")) {
						User ci = data.get(i);
						if (targetField.get(ci).toString().equals(params[1])) {
							controller.getList().add(ci);
						}
					} else if (params[2].equals("lt")) {
						User ci = data.get(i);
						if (targetField.get(ci).toString().compareTo(params[1]) < 0) {
							controller.getList().add(ci);
						}
					} else if (params[2].equals("gt")) {
						User ci = data.get(i);
						if (targetField.get(ci).toString().compareTo(params[1]) > 0) {
							controller.getList().add(ci);
						}
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			displayer.data(controller.getList().toArray());
		}
	}

}
