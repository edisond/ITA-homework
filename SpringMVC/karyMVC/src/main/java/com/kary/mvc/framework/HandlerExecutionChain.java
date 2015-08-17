package com.kary.mvc.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kary.mvc.framework.interceptor.HandlerInterceptor;

public class HandlerExecutionChain {
	private Object handler;
	private HandlerInterceptor[] interceptors;
	private boolean[] preHandelResults;

	boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (preHandelResults == null) {
			preHandelResults = new boolean[interceptors.length];
			for (int i = 0; i < preHandelResults.length; i++) {
				preHandelResults[i] = false;
			}
		}

		for (int i = 0; i < interceptors.length; i++) {
			preHandelResults[i] = interceptors[i].preHandle(request, response, handler);
			if (!preHandelResults[i]) {
				triggerAfterCompletion(request, response, null);
				return false;
			}
		}
		return true;
	}

	void applyPostHandle(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) throws Exception {
		for (int i = interceptors.length - 1; i >= 0; i--) {
			if (!preHandelResults[i]) {
				break;
			}
			interceptors[i].postHandle(request, response, handler, mv);
		}
	}

	/**
	 * Trigger afterCompletion callbacks on the mapped HandlerInterceptors. Will just invoke afterCompletion for all interceptors whose
	 * preHandle invocation has successfully completed and returned true.
	 */
	void triggerAfterCompletion(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {
		for (int i = interceptors.length - 1; i >= 0; i--) {
			if (preHandelResults[i]) {
				interceptors[i].afterCompletion(request, response, handler, ex);
			}
		}
	}

	public Object getHandler() {
		return handler;
	}

	public void setHandler(Object handler) {
		this.handler = handler;
	}

	public HandlerInterceptor[] getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(HandlerInterceptor[] interceptors) {
		this.interceptors = interceptors;
	}

}
