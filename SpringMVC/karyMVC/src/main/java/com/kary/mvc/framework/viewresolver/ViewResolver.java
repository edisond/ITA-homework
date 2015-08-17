package com.kary.mvc.framework.viewresolver;

import java.util.Locale;

import com.kary.mvc.framework.view.View;

public interface ViewResolver {	
	View resolveViewName(String viewName, Locale locale) throws Exception;
}