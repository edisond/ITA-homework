package com.wxsm.o2o.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import com.oocl.o2o.dao.impl.FoodDao;
import com.oocl.o2o.pojo.Food;
import com.wxsm.o2o.util.SearchCriteria;

/**
 * Servlet Filter implementation class AllFoodFilter
 */
public class AllFoodFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AllFoodFilter() {
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
		FoodDao dao = new FoodDao();
		SearchCriteria criteria = new SearchCriteria();

		List<Food> list = dao.findAllByCriteria(criteria);

		request.setAttribute("foods", list);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
