package com.kary.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kary.mvc.framework.handleradapter.HandlerAdapter;
import com.kary.mvc.framework.handlermapping.HandlerMapping;
import com.kary.mvc.framework.pojo.Config;
import com.kary.mvc.framework.util.ConfigReader;
import com.kary.mvc.framework.view.View;
import com.kary.mvc.framework.viewresolver.ViewResolver;
import com.kary.mvc.framework.viewresolver.impl.ViewResolverFactory;

public class DispatchServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		String realPath = getServletContext().getRealPath("/");
		String servletName = getServletName();
		ConfigReader reader = new ConfigReader();
		Config config = reader.read(realPath + "WEB-INF/" + servletName + "-servlet.xml");
		ServletContext application = getServletContext();
		application.setAttribute("karyMVC", config);
	};

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doDispatch(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doDispatch(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ServletContext application = request.getServletContext();
		Config config = (Config) application.getAttribute("karyMVC");

		HandlerMapping handlerMapping = (HandlerMapping) config.getHandlerMapping().newInstance();
		HandlerExecutionChain chain = handlerMapping.getHandler(request);
		if (chain.applyPreHandle(request, response)) {
			HandlerAdapter adapter = (HandlerAdapter) config.getHandlerAdapter().newInstance();
			ModelAndView mv = adapter.handle(request, response, chain.getHandler());
			chain.applyPostHandle(request, response, mv);

			ViewResolverFactory viewResolverFactory = new ViewResolverFactory();
			ViewResolver resolver = viewResolverFactory.newInstance(config.getViewResolver(), config.getViewResolverProperties());

			View view = resolver.resolveViewName(mv.getViewName(), null);
			chain.triggerAfterCompletion(request, response, null);

			view.render(mv.getModel(), request, response);
		} else {
			return;
		}

	}

}
