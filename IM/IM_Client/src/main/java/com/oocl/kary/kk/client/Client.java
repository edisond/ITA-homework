package com.oocl.kary.kk.client;

import java.awt.EventQueue;
import com.oocl.kary.kk.client.ui.LoginFrame;

public class Client {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
