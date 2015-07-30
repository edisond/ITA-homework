package com.oocl.kk.client.action;

import com.oocl.kary.pojo.Packet;
import com.oocl.kk.client.ui.MainFrame;

public class ShakeAction implements Action {

	public void execute(Packet packet, MainFrame frame) {
		frame.startShake();
	}

}
