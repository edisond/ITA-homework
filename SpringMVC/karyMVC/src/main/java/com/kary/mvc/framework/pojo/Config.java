package com.kary.mvc.framework.pojo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.kary.mvc.framework.interceptor.HandlerInterceptor;

public class Config {
	private Class<?> handlerAdapter;
	private Class<?> handlerMapping;
	private Class<?> viewResolver;
	private Map<String, String> viewResolverProperties;
	private List<HandlerInterceptor> interceptors;
	private Map<String, Class<?>> handlers;

	public Config() {
		this.interceptors = new LinkedList<>();
		this.handlers = new HashMap<>();
		this.viewResolverProperties = new HashMap<>();
	}

	public Class<?> getHandlerAdapter() {
		return handlerAdapter;
	}

	public void setHandlerAdapter(Class<?> handlerAdapter) {
		this.handlerAdapter = handlerAdapter;
	}

	public Class<?> getViewResolver() {
		return viewResolver;
	}

	public void setViewResolver(Class<?> viewResolver) {
		this.viewResolver = viewResolver;
	}

	public List<HandlerInterceptor> getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(List<HandlerInterceptor> interceptors) {
		this.interceptors = interceptors;
	}

	public Map<String, Class<?>> getHandlers() {
		return handlers;
	}

	public Class<?> getHandlerMapping() {
		return handlerMapping;
	}

	public void setHandlerMapping(Class<?> handlerMapping) {
		this.handlerMapping = handlerMapping;
	}

	public void setHandlers(Map<String, Class<?>> handlers) {
		this.handlers = handlers;
	}

	public Map<String, String> getViewResolverProperties() {
		return viewResolverProperties;
	}

	public void setViewResolverProperties(Map<String, String> viewResolverProperties) {
		this.viewResolverProperties = viewResolverProperties;
	}
}
