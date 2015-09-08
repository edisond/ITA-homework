package com.wxsm.jee.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		ServletContext context = event.getSession().getServletContext();
		Integer count = (Integer) context.getAttribute("onlineCount");
		if (count == null) {
			count = 1;
		} else {
			count += 1;
		}
		context.setAttribute("onlineCount", count);
		System.out.println("online: " + count);
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		ServletContext context = event.getSession().getServletContext();
		Integer count = (Integer) context.getAttribute("onlineCount");
		count -= 1;
		context.setAttribute("onlineCount", count);
		System.out.println("online: " + count);
	}

}
