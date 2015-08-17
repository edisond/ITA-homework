package com.kary.mvc.framework.handleradapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kary.mvc.framework.ModelAndView;

public interface HandlerAdapter {
	ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}

