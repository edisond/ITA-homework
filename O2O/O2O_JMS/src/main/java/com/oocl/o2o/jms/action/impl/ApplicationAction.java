package com.oocl.o2o.jms.action.impl;

import com.oocl.o2o.jms.action.Action;
import com.oocl.o2o.jms.util.JmsUtil;

public class ApplicationAction implements Action {

	public void execute(String msg) {
		JmsUtil.produce(msg);
	}

}
