package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewsController {

	@RequestMapping(value = "/")
	public String hellSpringBoot() throws Exception {

		return "hello";
	}

}
