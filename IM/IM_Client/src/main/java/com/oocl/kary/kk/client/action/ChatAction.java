package com.oocl.kary.kk.client.action;

import java.awt.EventQueue;
import com.oocl.kary.kk.client.model.KPacket;
import com.oocl.kary.kk.client.ui.MainFrame;

public class ChatAction implements Action {
	@Override
	public void execute(KPacket packet, final MainFrame frame) {
		packet.read = false;
		frame.getMsgPackets().add(packet);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame.updateChatContainer();
			}
		});
	}
}
