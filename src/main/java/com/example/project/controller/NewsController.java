package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.example.project.crawler.Crawler;
import com.example.project.crawler.CrawlingThread;
import com.example.project.crawler.InsertData;
import com.example.project.mapper.NewsInfoMapper;
import com.example.project.mapper.NewsMapper;

@Controller
public class NewsController {

	@Autowired(required = true)
	NewsMapper newsMapper;

	@Autowired(required = true)
	NewsInfoMapper newsInfoMapper;

	@Autowired(required = true)
	Crawler crawler;

	@Autowired(required = true)
	InsertData insertDB;

	@Autowired(required = true)
	CrawlingThread crawlingThread;

	// @RequestMapping(value = "")
	// 매 10초에 실행되니까 1분마다 실행
	@Scheduled(cron = "10 * * * * *")
	public String hellSpringBoot() throws Exception {

		// thread 6개
		for (int i = 0; i < 6; i++) {
			crawlingThread = new CrawlingThread(newsMapper, newsInfoMapper, crawler, insertDB);
			Thread thread = new Thread(crawlingThread, "10" + i);
			thread.start();
		}

		return "hello";
	}

}
