package com.wxsm.kk.ums.action.impl;

import com.wxsm.kk.ums.action.Action;
import com.wxsm.kk.ums.controller.MainController;
import com.wxsm.kk.ums.service.Displayer;

public class QuitAction implements Action {

	@Override
	public void execute(String[] params, MainController controller) {
		new Displayer().bye();
		controller.setExit(true);
	}

}
