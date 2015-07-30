package com.oocl.kk.ums;

import java.util.Scanner;

import com.oocl.kk.ums.controller.MainController;

/**
 * 程序入口
 * 
 */
public class Ums {
	public static void main(String[] args) {
		MainController ctrl = new MainController();
		ctrl.init();
		Scanner sc = new Scanner(System.in);
		while (!ctrl.isExit()) {
			ctrl.execCommand(sc.nextLine());
		}
		sc.close();
	}
}
 