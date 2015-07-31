package com.oocl.kk.client.action.impl;

import java.awt.EventQueue;

import com.oocl.kary.pojo.Packet;
import com.oocl.kk.client.action.Action;
import com.oocl.kk.client.ui.MainFrame;

public class ChatAction implements Action{
	public void execute(Packet packet, final MainFrame frame) {
		packet.setRead(false);
		frame.getMsgPackets().add(packet);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame.updateChatContainer();
			}
		});
	}
}
