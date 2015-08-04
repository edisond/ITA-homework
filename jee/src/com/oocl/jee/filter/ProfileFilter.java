package com.oocl.jee.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.oocl.jee.pojo.User;
import com.oocl.jee.service.UserMng;

public class ProfileFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			Integer id = Integer.parseInt(request.getParameter("id").toString());
			User user = UserMng.getUserById(id);
			request.setAttribute("profile", user);
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("UserList").forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
