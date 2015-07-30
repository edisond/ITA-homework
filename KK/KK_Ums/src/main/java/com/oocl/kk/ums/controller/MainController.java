package com.oocl.kk.ums.controller;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import com.oocl.kary.pojo.User;
import com.oocl.kk.dao.impl.UserDaoImpl;
import com.oocl.kk.ums.service.CommandReader;
import com.oocl.kk.ums.service.Displayer;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 主控制器
 * 
 * @author GUOKA2
 * 
 */
public class MainController {

	private UserDaoImpl dao = new UserDaoImpl();
	private List<User> list;
	private String[] commands;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Displayer displayer = new Displayer();
	private CommandReader commandReader = new CommandReader();
	private boolean isExit = false;

	public MainController() {
		commands = new String[] { "l", "d", "f", "s", "a", "q" };
	}

	public void init() {
		list = dao.findAll();
		displayer.welcomeMessage();
		execCommand("-l");
	}

	/**
	 * 执行指令
	 * 
	 * @param command
	 *            读入的指令
	 */
	public void execCommand(String command) {
		if (commandReader.setCommand(command)) {

			switch (commandReader.getCommand()) {
			/**
			 * 进行列表操作
			 */
			case "l":
				if (commandReader.getParams().length == 0) {
					list = dao.findAll();
					showData(list.toArray());
				} else {
					list = new LinkedList<User>();
					for (String param : commandReader.getParams()) {
						if (param.contains("-")) {
							try {
								int i = Integer.parseInt(param.substring(0,
										param.indexOf("-")));
								int j = Integer.parseInt(param.substring(param
										.indexOf("-") + 1));
								List<User> temp = dao.findAll(i, j);
								for (int m = 0, n = temp.size(); m < n; m++) {
									User c = temp.get(m);
									if (!list.contains(c)) {
										list.add(c);
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
								User c = dao.findAll(i, 1).get(0);
								if (!list.contains(c)) {
									list.add(c);
								}
							} catch (NumberFormatException e) {
								displayer.error("参数中含有非数字字符");
							} catch (IndexOutOfBoundsException e) {
								displayer.error("指定的行数超出数据范围");
							}
						}
					}
					showData(list.toArray());
				}
				showCommand();
				break;
			/**
			 * 进行删除操作
			 */
			case "d":
				if (commandReader.getParams().length == 0) {
					displayer.error("请指定删除行");
				} else {
					List<Integer> rows = new LinkedList<Integer>();
					for (String param : commandReader.getParams()) {
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
								if (data.get(j).getId() == list.get(
										rows.get(i) - 1).getId()) {
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
				showCommand();
				break;
			/**
			 * 进行查找操作
			 */
			case "f":
				if (commandReader.getParams().length == 0) {
					displayer.error("请指定查询条件");
				} else {
					String params[] = commandReader.getParams()[0].split(" ");
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
					list = new LinkedList<User>();
					List<User> data = dao.findAll();
					for (int i = 0, j = data.size(); i < j; i++) {
						try {
							if (params[2].equals("eq")) {
								User ci = data.get(i);
								if (targetField.get(ci).toString()
										.equals(params[1])) {
									list.add(ci);
								}
							} else if (params[2].equals("lt")) {
								User ci = data.get(i);
								if (targetField.get(ci).toString()
										.compareTo(params[1]) < 0) {
									list.add(ci);
								}
							} else if (params[2].equals("gt")) {
								User ci = data.get(i);
								if (targetField.get(ci).toString()
										.compareTo(params[1]) > 0) {
									list.add(ci);
								}
							}
						} catch (IllegalArgumentException
								| IllegalAccessException e) {
							e.printStackTrace();
						}
					}
					showData(list.toArray());
				}
				showCommand();
				break;
			/**
			 * 进行排序操作
			 */
			case "s":
				if (commandReader.getParams().length == 0) {
					displayer.error("请指定排序条件");
				} else {
					String params[] = commandReader.getParams()[0].split(" ");
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
						Collections.sort(list,new Comparator<User>() {
							@Override
							public int compare(User u1, User u2) {
								// TODO Auto-generated method stub
								return 0;
							}
						});
						// list.selectionSort(targetField,
						// params[1].equals("desc") ? false : true);
						showData(list.toArray());
					} catch (Exception e) {
						displayer.error("列名不存在");
					}
				}
				showCommand();
				break;
			/**
			 * 进行插入操作
			 */
			case "a":
				User c = new User();
				String[] params = commandReader.getParams();
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
				showCommand();
				break;
			case "q":
				exit();
				break;
			default:
				displayer.error("指令不在指令集中");
				break;
			}
		} else {
			displayer.error("指令格式不正确");
		}
	}

	/**
	 * 输出可用指令
	 */
	public void showCommand() {
		displayer.commands(commands);
	}

	/**
	 * 输出数据集
	 */
	public void showData() {
		displayer.data(dao.findAll().toArray());
	}

	/**
	 * 输出数据集
	 * 
	 * @param data
	 *            待输出数据
	 */
	public void showData(Object[] data) {
		displayer.data(data);
	}

	/**
	 * 退出程序
	 */
	public void exit() {
		displayer.bye();
		isExit = true;
	}

	/**
	 * 判断退出
	 * 
	 * @return 是否已退出
	 */
	public boolean isExit() {
		return isExit;
	}
}
