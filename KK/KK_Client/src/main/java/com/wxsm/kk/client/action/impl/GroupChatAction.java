package com.wxsm.kk.client.action.impl;

import java.awt.EventQueue;

import com.oocl.kary.pojo.Packet;
import com.wxsm.kk.client.action.Action;
import com.wxsm.kk.client.ui.MainFrame;

public class GroupChatAction implements Action {
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
