package com.kary.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kary.mvc.framework.Controller;
import com.kary.mvc.framework.ModelAndView;
import com.kary.mvc.pojo.User;

public class LoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User u = new User();
		u.setName(name);
		u.setPassword(password);
		ModelAndView mv = new ModelAndView();
		mv.addObject("u", u);
		mv.setViewName("suc");
		return mv;
	}

}
