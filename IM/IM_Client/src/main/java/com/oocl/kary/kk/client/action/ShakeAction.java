package com.oocl.kary.kk.client.action;

import com.oocl.kary.kk.client.model.Packet;
import com.oocl.kary.kk.client.ui.MainFrame;

public class ShakeAction implements Action {

	@Override
	public void execute(Packet packet, MainFrame frame) {
		frame.startShake();
	}

}
