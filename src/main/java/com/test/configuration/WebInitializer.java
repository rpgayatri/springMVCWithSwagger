package com.test.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

public class WebInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfiguration.class);
		ctx.setServletContext(servletContext);

		Dynamic servlet = servletContext.addServlet("dispatcher",
				new DispatcherServlet(ctx));
		servlet.addMapping("/*");
		servlet.setLoadOnStartup(1);

		/*
		 * servletContext.addFilter("springSecurityFilterChain", new
		 * DelegatingFilterProxy("springSecurityFilterChain"))
		 * .addMappingForUrlPatterns(null, false, "/users/*");
		 */
		
		//uncomment this to enable spring security
		/*servletContext.addFilter("springSecurityFilterChain",
				new DelegatingFilterProxy("springSecurityFilterChain"))
				.addMappingForUrlPatterns(null, false, "/address/*",
						"/users/*", "/sessions/*");*/

		servletContext.addListener(new ContextLoaderListener(ctx));

		servletContext.setInitParameter("log4jConfigLocation",
				"/WEB-INF/classes/log4j.properties");
		Log4jConfigListener log4jListener = new Log4jConfigListener();
		servletContext.addListener(log4jListener);

	}

}
