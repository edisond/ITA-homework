package com.oocl.o2o.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.oocl.o2o.dao.impl.FoodDao;
import com.oocl.o2o.pojo.Food;
import com.oocl.o2o.pojo.User;
import com.oocl.o2o.util.Constants;
import com.oocl.o2o.util.Criteria;
import com.oocl.o2o.util.SearchCriteria;

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
		HttpServletRequest req = (HttpServletRequest) request;
		User user = (User) req.getSession().getAttribute("user");

		FoodDao dao=new FoodDao();
		SearchCriteria criteria = new SearchCriteria();

		criteria.getCriteria().add(new Criteria("userid", user.getUserId(), Criteria.EQUAL));
		criteria.getCriteria().add(new Criteria("statusId", Constants.STATUS_DELETED, Criteria.NOT_EQUAL));

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
