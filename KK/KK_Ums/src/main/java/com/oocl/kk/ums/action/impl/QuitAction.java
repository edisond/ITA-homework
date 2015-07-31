package com.oocl.kk.ums.action.impl;

import com.oocl.kk.ums.action.Action;
import com.oocl.kk.ums.controller.MainController;
import com.oocl.kk.ums.service.Displayer;

public class QuitAction implements Action {

	@Override
	public void execute(String[] params, MainController controller) {
		new Displayer().bye();
		controller.setExit(true);
	}

}
