package com.edudigital.cloudy.amp.order.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping(value = "/test")
	public String test(HttpServletRequest req, HttpServletResponse res) {
		return "";
	}
}
