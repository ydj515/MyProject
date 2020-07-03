package com.example.project.controller;

import java.util.List;

import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.crawler.Crawler;
import com.example.project.crawler.InsertData;
import com.example.project.mapper.NewsInfoMapper;
import com.example.project.mapper.NewsMapper;

@Controller
public class NewsController {

	@Autowired(required=true)
	NewsMapper newsMapper;

	@Autowired(required=true)
	NewsInfoMapper newsInfoMapper;

	@Autowired(required=true)
	Crawler crawler;

	@Autowired(required=true)
	InsertData insertDB;

	@RequestMapping(value = "/")
	public String hellSpringBoot() throws Exception {

		List<Elements> elementsList = crawler.run();

		insertDB.insert(elementsList, newsMapper, newsInfoMapper);
		return "hello";
	}

}
