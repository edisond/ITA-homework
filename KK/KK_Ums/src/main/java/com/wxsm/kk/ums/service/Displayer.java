package com.wxsm.kk.ums.service;

import java.lang.reflect.Field;


/**
 * 显示数据
 * @author GUOKA2
 *
 */
public class Displayer {

	/**
	 * 显示欢迎信息
	 */
	public void welcomeMessage() {
		System.out.println("欢迎使用客户信息管理系统");
		System.out.println("-------------------");
	}

	/**
	 * 显示指令集
	 * @param commands
	 */
	public void commands(String[] commands) {
		System.out.println("-------------------");
		String str = "执行指令 （";
		for (String command : commands) {
			str += "-" + command + " ";
		}
		str += "）：";
		System.out.println(str);
	}

	/**
	 * 显示数据集
	 * @param data
	 */
	public void data(Object[] data) {

		if (data.length == 0) {
			return;
		}

		Class<? extends Object> cls = data[0].getClass();
		Field[] field = cls.getDeclaredFields();

		System.out.print("row\t");
		for (Field f : field) {
			System.out.print(f.getName() + "\t");
		}
		System.out.println();
		for (int i = 0; i < data.length; i++) {
			System.out.print((i + 1) + "\t");
			for (Field f : field) {
				f.setAccessible(true);
				try {
					System.out.print(f.get(data[i]) + "\t");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			System.out.println();
		}

	}

	/**
	 * 显示错误信息
	 * @param str
	 */
	public void error(String str) {
		System.out.println("错误： " + str);
	}

	/**
	 * 显示成功信息
	 */
	public void success() {
		System.out.println("操作成功");
	}

	/**
	 * 显示退出信息
	 */
	public void bye() {
		System.out.println("系统正在退出......");
		System.out.println("退出成功");
	}
}
