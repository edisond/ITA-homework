package com.oocl.o2o.filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.oocl.o2o.dao.impl.PackageDaoImpl;
import com.oocl.o2o.pojo.Package;
import com.oocl.o2o.pojo.User;
import com.oocl.o2o.util.Constants;

/**
 * Servlet Filter implementation class PackageFilter
 */
public class PackageFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public PackageFilter() {
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
		User user = (User) req.getSession().getAttribute("user");

		PackageDaoImpl daoImpl = new PackageDaoImpl();
		List<Package> list = daoImpl.findAllByUserId(user.getUserId());
		for (Iterator<Package> iterator = list.iterator(); iterator.hasNext();) {
			Package p = (Package) iterator.next();
			if (p.getStatusId() == Constants.STATUS_DELETED) {
				iterator.remove();
			}
		}

		request.setAttribute("packages", list);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
