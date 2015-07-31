package com.oocl.kk.ums.action;

import com.oocl.kk.ums.controller.MainController;

public interface Action {
	public static final String LIST = "l";
	public static final String FIND = "f";
	public static final String DELETE = "d";
	public static final String ADD = "a";
	public static final String SORT = "s";
	public static final String QUIT = "q";

	public void execute(String[] params, MainController controller);
}
