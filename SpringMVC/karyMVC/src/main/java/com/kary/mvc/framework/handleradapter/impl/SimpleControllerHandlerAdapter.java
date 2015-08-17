package com.kary.mvc.framework.handleradapter.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kary.mvc.framework.Controller;
import com.kary.mvc.framework.ModelAndView;
import com.kary.mvc.framework.handleradapter.HandlerAdapter;

public class SimpleControllerHandlerAdapter implements HandlerAdapter {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Controller controller = (Controller) handler;
		return controller.handleRequest(request, response);
	}

}
