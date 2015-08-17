package com.kary.mvc.framework.handlermapping.impl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.kary.mvc.framework.HandlerExecutionChain;
import com.kary.mvc.framework.handlermapping.HandlerMapping;
import com.kary.mvc.framework.interceptor.HandlerInterceptor;
import com.kary.mvc.framework.pojo.Config;

public class BeanNameUrlHandlerMapping implements HandlerMapping {

	@Override
	public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
		HandlerExecutionChain chain = new HandlerExecutionChain();
		ServletContext application = request.getServletContext();
		Config config = (Config) application.getAttribute("karyMVC");

		HandlerInterceptor[] interceptors = new HandlerInterceptor[config.getInterceptors().size()];
		chain.setInterceptors(config.getInterceptors().toArray(interceptors));

		String uriWithoutProjectName=request.getRequestURI().replace(request.getContextPath(), "");
		chain.setHandler(config.getHandlers().get(uriWithoutProjectName).newInstance());

		return chain;
	}

}