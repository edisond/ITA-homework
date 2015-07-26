package com.oocl.kary.kk.client.action;

import java.awt.EventQueue;
import java.util.LinkedList;
import com.google.gson.reflect.TypeToken;
import com.oocl.kary.kk.client.model.Packet;
import com.oocl.kary.kk.client.model.User;
import com.oocl.kary.kk.client.ui.MainFrame;

public class GetUserAction implements Action {
	@SuppressWarnings("unchecked")
	@Override
	public void execute(Packet packet, final MainFrame frame) {
		frame.setUsers((LinkedList<User>) GSON.fromJson(packet.body.toString(),
				new TypeToken<LinkedList<User>>() {
				}.getType()));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame.updateContactContainer();
			}
		});
	}
}
