package com.oocl.kk.ums.action.impl;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import com.oocl.kary.pojo.User;
import com.oocl.kk.ums.action.Action;
import com.oocl.kk.ums.controller.MainController;
import com.oocl.kk.ums.service.Displayer;

public class SortAction implements Action {

	@Override
	public void execute(String[] params, MainController controller) {
		Displayer displayer = new Displayer();

		if (params.length == 0) {
			displayer.error("请指定排序条件");
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
			try {
				Collections.sort(controller.getList(), new Comparator<User>() {
					@Override
					public int compare(User u1, User u2) {
						// TODO Auto-generated method stub
						return 0;
					}
				});
				// list.selectionSort(targetField,
				// params[1].equals("desc") ? false : true);
				displayer.data(controller.getList().toArray());
			} catch (Exception e) {
				displayer.error("列名不存在");
			}
		}
	}

}
