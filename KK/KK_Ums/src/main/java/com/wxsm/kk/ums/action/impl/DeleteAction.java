package com.wxsm.kk.ums.action.impl;

import java.util.LinkedList;
import java.util.List;

import com.oocl.kary.pojo.User;
import com.oocl.kk.dao.impl.UserDaoImpl;
import com.wxsm.kk.ums.action.Action;
import com.wxsm.kk.ums.controller.MainController;
import com.wxsm.kk.ums.service.Displayer;

public class DeleteAction implements Action {

	@Override
	public void execute(String[] params, MainController controller) {
		UserDaoImpl dao = new UserDaoImpl();
		Displayer displayer = new Displayer();

		if (params.length == 0) {
			displayer.error("请指定删除行");
		} else {
			List<Integer> rows = new LinkedList<Integer>();
			for (String param : params) {
				if (param.contains("-")) {
					try {
						int i = Integer.parseInt(param.substring(0,
								param.indexOf("-")));
						int j = Integer.parseInt(param.substring(param
								.indexOf("-") + 1));
						if (i > j) {
							int temp = i;
							i = j;
							j = temp;
						}
						while (i <= j) {
							if (!rows.contains(i)) {
								rows.add(i);
							}
							i++;
						}
					} catch (NumberFormatException e) {
						displayer.error("参数中含有非数字字符");
					} catch (IndexOutOfBoundsException e) {
						displayer.error("指定的行数超出数据范围");
					}
				} else {
					try {
						int i = Integer.parseInt(param);
						if (!rows.contains(i)) {
							rows.add(i);
						}
					} catch (NumberFormatException e) {
						displayer.error("参数中含有非数字字符");
					} catch (IndexOutOfBoundsException e) {
						displayer.error("指定的行数超出数据范围");
					}
				}
			}
			int length = rows.size();
			int[] arr = new int[length];
			List<User> data = dao.findAll();
			try {
				for (int i = 0; i < length; i++) {
					for (int j = 0, k = data.size(); j < k; j++) {
						if (data.get(j).getId() == controller.getList().get(rows.get(i) - 1)
								.getId()) {
							arr[i] = j;
						}
					}
				}
				for (int i = 0; i < arr.length; i++) {
					dao.delete(data.get(arr[i]).getId());
				}
				displayer.success();
			} catch (IndexOutOfBoundsException e) {
				displayer.error("指定的行数超出数据范围");
			}
		}
	}

}
