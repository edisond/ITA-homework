package com.oocl.o2o.jms.action;


public interface Action {
	public static final String APPLICATION = "application";

	public void execute(String msg);
}
