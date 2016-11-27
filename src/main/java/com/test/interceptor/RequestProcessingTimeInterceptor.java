package com.test.interceptor;

import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestProcessingTimeInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = Logger
			.getLogger(RequestProcessingTimeInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		logger.info("preHandle Request URL::"
				+ request.getRequestURL().toString() + ":: Start Time="
				+ System.currentTimeMillis());
		logger.info(request);

		request.setAttribute("startTime", startTime);
		// logger.info("parameter names::"+request.getParameterNames());

		Enumeration<String> parameters = request.getParameterNames();
		while (parameters.hasMoreElements()) {
			String currentParameter = parameters.nextElement();
			logger.info("Request parameter::" + currentParameter + " :: "
					+ request.getParameter(currentParameter));
		}

		Enumeration<String> headers = request.getHeaderNames();
		while (headers.hasMoreElements()) {
			String currentHeader = headers.nextElement();
			logger.info("Request Header::" + currentHeader + " :: "
					+ request.getHeader(currentHeader));
		}
		// if returned false, we need to make sure 'response' is sent
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle Request URL::"
				+ request.getRequestURL().toString()
				+ " Sent to Handler :: Current Time="
				+ System.currentTimeMillis());
		// we can add attributes in the modelAndView and use that in the view
		// page
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		logger.info("afterCompletion Request URL::"
				+ request.getRequestURL().toString() + ":: End Time="
				+ System.currentTimeMillis());
		logger.info("Request URL::" + request.getRequestURL().toString()
				+ ":: Time Taken=" + (System.currentTimeMillis() - startTime));

		Collection<String> headers = response.getHeaderNames();
		for (String header : headers)
			logger.info("Response Header::" + header + " :: "
					+ response.getHeader(header));
		// response.setHeader("Access-Control-Allow-Origin","*" );
	}
}
