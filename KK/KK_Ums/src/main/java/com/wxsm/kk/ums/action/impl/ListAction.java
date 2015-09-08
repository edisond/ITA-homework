package com.wxsm.kk.ums.action.impl;

import java.util.LinkedList;
import java.util.List;

import com.oocl.kary.pojo.User;
import com.oocl.kk.dao.impl.UserDaoImpl;
import com.wxsm.kk.ums.action.Action;
import com.wxsm.kk.ums.controller.MainController;
import com.wxsm.kk.ums.service.Displayer;

public class ListAction implements Action {

	@Override
	public void execute(String[] params, MainController controller) {
		UserDaoImpl dao = new UserDaoImpl();
		Displayer displayer = new Displayer();

		if (params.length == 0) {
			controller.setList(dao.findAll());
			displayer.data(controller.getList().toArray());
		} else {
			controller.setList(new LinkedList<User>());
			for (String param : params) {
				if (param.contains("-")) {
					try {
						int i = Integer.parseInt(param.substring(0,
								param.indexOf("-")));
						int j = Integer.parseInt(param.substring(param
								.indexOf("-") + 1));
						
						List<User> temp = dao.findAll(i - 1, j - i + 1);
						System.out.println(temp.size());
						for (int m = 0, n = temp.size(); m < n; m++) {
							User c = temp.get(m);
							if (!controller.getList().contains(c)) {
								controller.getList().add(c);
							}
						}
					} catch (NumberFormatException e) {
						displayer.error("参数中含有非数字字符");
					} catch (IndexOutOfBoundsException e) {
						displayer.error("指定的行数超出数据范围");
					}
				} else {
					try {
						int i = Integer.parseInt(param);
						User c = dao.findAll(i - 1, 1).get(0);
						if (!controller.getList().contains(c)) {
							controller.getList().add(c);
						}
					} catch (NumberFormatException e) {
						displayer.error("参数中含有非数字字符");
					} catch (IndexOutOfBoundsException e) {
						displayer.error("指定的行数超出数据范围");
					}
				}
			}
			displayer.data(controller.getList().toArray());
		}
	}
}
