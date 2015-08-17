package com.kary.mvc.framework.viewresolver.impl;

import java.util.Locale;

import com.kary.mvc.framework.view.View;
import com.kary.mvc.framework.view.impl.InternalResourceView;
import com.kary.mvc.framework.viewresolver.ViewResolver;

public class InternalResourceViewResolver implements ViewResolver {
	private String prefix;
	private String suffix;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public InternalResourceViewResolver() {
		super();
		this.prefix = "";
		this.suffix = "";
	}

	public InternalResourceViewResolver(String prefix, String suffix) {
		super();
		this.prefix = prefix == null ? "" : prefix;
		this.suffix = suffix == null ? "" : suffix;
	}

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		InternalResourceView view = new InternalResourceView();
		view.setUrl(prefix + viewName + suffix);
		return view;
	}

}