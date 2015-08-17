package com.kary.mvc.framework;

import java.util.HashMap;

public class ModelAndView {
	private String viewName;
	private HashMap<String, Object> model = new HashMap<String, Object>();

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getViewName() {
		return viewName;
	}

	public void addObject(String key, Object obj) {
		model.put(key, obj);
	}

	public HashMap<String, Object> getModel() {
		return model;
	}
}
