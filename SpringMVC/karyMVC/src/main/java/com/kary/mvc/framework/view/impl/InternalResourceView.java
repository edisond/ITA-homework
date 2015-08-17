package com.kary.mvc.framework.view.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kary.mvc.framework.view.View;

public class InternalResourceView implements View {
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		for (String key : model.keySet()) {
			request.setAttribute(key, model.get(key));
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
