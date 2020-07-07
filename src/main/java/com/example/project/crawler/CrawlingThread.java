package com.example.project.crawler;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.project.domain.NewsItem;
import com.example.project.mapper.NewsInfoMapper;
import com.example.project.mapper.NewsMapper;

@Component
public class CrawlingThread implements Runnable {

	NewsMapper newsMapper;
	NewsInfoMapper newsInfoMapper;
	Crawler crawler;
	InsertData insertDB;

	public CrawlingThread(NewsMapper newsMapper, NewsInfoMapper newsInfoMapper, Crawler crawler, InsertData insertDB) {
		this.newsMapper = newsMapper;
		this.newsInfoMapper = newsInfoMapper;
		this.crawler = crawler;
		this.insertDB = insertDB;
	}

	@Override
	public void run() {
		Map<Integer, NewsItem> newsItemMap = null;

		try {
			newsItemMap = crawler.run(Thread.currentThread().getName());
		} catch (IOException e) {
			e.printStackTrace();
		}

		insertDB.insert(newsItemMap, newsMapper, newsInfoMapper);
	}

}
