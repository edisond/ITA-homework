package com.kary.mvc.framework.handlermapping;

import javax.servlet.http.HttpServletRequest;

import com.kary.mvc.framework.HandlerExecutionChain;

public interface HandlerMapping {
	HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;
}
