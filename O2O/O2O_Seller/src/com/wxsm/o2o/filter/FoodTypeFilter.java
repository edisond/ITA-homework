package com.wxsm.o2o.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.oocl.o2o.dao.impl.FoodTypeDao;
import com.oocl.o2o.pojo.FoodType;

/**
 * Servlet Filter implementation class FoodTypeFilter
 */
public class FoodTypeFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public FoodTypeFilter() {
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
		FoodTypeDao dao = new FoodTypeDao();
		List<FoodType> list = dao.findAll();
		request.setAttribute("foodTypes", list);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
