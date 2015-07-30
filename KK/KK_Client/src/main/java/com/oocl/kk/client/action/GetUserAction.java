package com.oocl.kk.client.action;

import java.awt.EventQueue;
import java.util.LinkedList;

import com.google.gson.reflect.TypeToken;
import com.oocl.kary.pojo.Packet;
import com.oocl.kary.pojo.User;
import com.oocl.kk.client.ui.MainFrame;

public class GetUserAction implements Action {
	@SuppressWarnings("unchecked")
	public void execute(Packet packet, final MainFrame frame) {
		frame.setUsers((LinkedList<User>) GSON.fromJson(packet.getBody().toString(),
				new TypeToken<LinkedList<User>>() {
				}.getType()));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame.updateContactContainer();
			}
		});
	}
}
