package com.oocl.kary.kk.ui.prototypes;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class ShakableJframe {
	// 窗口距离中心左右晃动的最大距离
	public static final int SHAKE_DISTANCE = 10;
	// 窗口晃动一个循环（中间，右，中间，左， 中间）所需要的时间 (ms),
	// 这个值越小， 晃动的就越快。
	public static final double SHAKE_CYCLE = 80;
	// 整个晃动所需要的时间。
	public static final int SHAKE_DURATION = 300;
	// 这个是设定 Swing 多长时间（ ms ）更新窗口的位置。
	public static final int SHAKE_UPDATE = 5;

	private JFrame dialog;
	private Point naturalLocation;
	private long startTime;
	private Timer shakeTimer;
	private final double HALF_PI = Math.PI / 2.0;
	private final double TWO_PI = Math.PI * 2.0;

	public ShakableJframe(JFrame d) {
		dialog = d;
	}

	public void startShake() {
		// 保存窗口的原始位置
		naturalLocation = dialog.getLocation();
		// 保存开始的时间
		startTime = System.currentTimeMillis();
		shakeTimer = new Timer(SHAKE_UPDATE, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 计算倒目前为止花费的时间
				long elapsed = System.currentTimeMillis() - startTime;
				// 下了三角公式是为了利用时间计算出某一时刻晃动的幅度，举例见 A.
				double waveOffset = (elapsed % SHAKE_CYCLE) / SHAKE_CYCLE;
				double angle = waveOffset * TWO_PI;
				double angley = waveOffset * TWO_PI;

				int shakenX = (int) ((Math.sin(angle) * SHAKE_DISTANCE) + naturalLocation.x);
				int shakenY = (int) ((Math.sin(angley) * SHAKE_DISTANCE) + naturalLocation.y);
				dialog.setLocation(shakenX, shakenY);
				dialog.repaint();

				// should we stop timer?
				if (elapsed >= SHAKE_DURATION)
					stopShake();
			}
		});

		shakeTimer.start();
	}

	public void stopShake() {
		shakeTimer.stop();
		dialog.setLocation(naturalLocation);
		dialog.repaint();
	}
	// 去掉了 原始代码中的 main
	
	public static void main(String[] args) {
		JFrame jd=new JFrame();
		jd.setBounds(0, 0, 400, 400);
		jd.setLocationRelativeTo(null);
		jd.setVisible(true);
		ShakableJframe jf=new ShakableJframe(jd);
		jf.startShake();
	}
}
