package com.wxsm.kk.client.action.impl;

import com.oocl.kary.pojo.Packet;
import com.wxsm.kk.client.action.Action;
import com.wxsm.kk.client.ui.MainFrame;

public class ShakeAction implements Action {

	public void execute(Packet packet, MainFrame frame) {
		frame.startShake();
	}

}
