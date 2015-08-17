package com.kary.mvc.framework.viewresolver.impl;

import java.util.Map;

import com.kary.mvc.framework.viewresolver.ViewResolver;

public class ViewResolverFactory {
	public ViewResolver newInstance(Class<?> clazz, Map<String, String> properties) {
		ViewResolver resolver = null;
		if (clazz.equals(InternalResourceViewResolver.class)) {
			resolver = new InternalResourceViewResolver(properties.get("prefix"), properties.get("suffix"));
		} else {
			try {
				resolver = (ViewResolver) clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return resolver;
	}
}
