package com.oocl.o2o.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.oocl.o2o.dao.impl.ImageDao;
import com.oocl.o2o.pojo.Image;

import sun.misc.BASE64Encoder;

/**
 * Servlet Filter implementation class ImageFilter
 */
public class ImageFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ImageFilter() {
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
		@SuppressWarnings("unchecked")
		Map<String, String> images = (Map<String, String>) request.getAttribute("images");
		if (images != null) {
			ImageDao dao = new ImageDao();
			BASE64Encoder encoder = new BASE64Encoder();
			for (String key : images.keySet()) {
				Image image = dao.findById(key);
				if (image != null) {
					images.put(key, encoder.encode(image.getBody()));
				}
			}
			request.setAttribute("images", images);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
