package com.wxsm.o2o.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.oocl.o2o.dao.impl.UserDao;
import com.oocl.o2o.pojo.User;
import com.wxsm.o2o.util.Criteria;
import com.wxsm.o2o.util.SearchCriteria;

/**
 * Servlet Filter implementation class UserFilter
 */
public class UserFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public UserFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String role = req.getParameter("role");
		Integer page = req.getParameter("p") == null ? 1 : Integer.parseInt(req.getParameter("p"));

		UserDao dao = new UserDao();
		SearchCriteria criteria = new SearchCriteria();

		if (role != null) {
			criteria.getCriteria().add(new Criteria("role", "'" + role + "'", Criteria.EQUAL));
		}

		int count = dao.findAllByCriteria(criteria).size();

		criteria.setStart((page - 1) * 5);
		criteria.setLength(5);
		List<User> list = dao.findAllByCriteria(criteria);

		request.setAttribute("users", list);
		request.setAttribute("usersCount", count);

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
