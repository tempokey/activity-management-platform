package com.edudigital.cloudy.amp.news.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController extends BaseController {

	@RequestMapping(value = "/")
	public String foyer(HttpServletRequest req, HttpServletResponse res) {
		logger.info("Now it is ampService.");
		return "redirect:/index";
	}

	@RequestMapping(value = "/index")
	public String Index(HttpServletRequest req, HttpServletResponse res) {
		logger.info("Now it is ampService.");
		return "Now it is ampService.";
	}

	@RequestMapping(value = "/info")
	public String info(HttpServletRequest rep, HttpServletResponse res) {
		logger.info("info...");
		return "ing...";
	}

	@RequestMapping(value = "/health")
	public String health(HttpServletRequest rep, HttpServletResponse res) {
		logger.info("health...");
		return "health...";
	}
}
