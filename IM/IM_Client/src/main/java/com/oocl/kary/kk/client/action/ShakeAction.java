package com.oocl.kary.kk.client.action;

import com.oocl.kary.kk.client.model.KPacket;
import com.oocl.kary.kk.client.ui.MainFrame;

public class ShakeAction implements Action {

	@Override
	public void execute(KPacket packet, MainFrame frame) {
		frame.startShake();
	}

}
