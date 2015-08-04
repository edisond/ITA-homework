package com.oocl.jee.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oocl.jee.pojo.User;

public class LoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			req.getRequestDispatcher("index.html").forward(req, res);
		} else {
			ServletContext application = req.getServletContext();
			@SuppressWarnings("unchecked")
			Set<Integer> onlineUserIds = (Set<Integer>) application.getAttribute("onlineUserIds");
			User user = (User) req.getSession().getAttribute("user");
			if (onlineUserIds == null) {
				onlineUserIds = new HashSet<Integer>();
			}
			if (!onlineUserIds.contains(user.getId())) {
				req.getRequestDispatcher("index.html").forward(req, res);
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
