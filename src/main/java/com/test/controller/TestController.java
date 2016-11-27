package com.test.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
	private static final Logger logger = Logger.getLogger(TestController.class);

	
	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	public @ResponseBody String printHelloWorld() {
		logger.info("Inside hello world");
		return "Hello World";
	}
}
