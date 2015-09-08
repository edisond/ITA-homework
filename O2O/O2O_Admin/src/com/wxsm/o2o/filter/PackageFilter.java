package com.wxsm.o2o.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import com.oocl.o2o.dao.impl.PackageDao;
import com.oocl.o2o.pojo.Package;
import com.wxsm.o2o.util.SearchCriteria;

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
		Integer page = request.getParameter("p") == null ? 1 : Integer.parseInt(request.getParameter("p"));

		PackageDao dao = new PackageDao();
		SearchCriteria criteria = new SearchCriteria();
		
		int count = dao.findAllByCriteria(criteria).size();
		
		criteria.setStart((page - 1) * 5);
		criteria.setLength(5);
		List<Package> list = dao.findAllByCriteria(criteria);

		request.setAttribute("packages", list);
		request.setAttribute("packagesCount", count);

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
