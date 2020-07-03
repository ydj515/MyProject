package com.example.project.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Crawler {

	private final String URL = "https://news.naver.com/main/ranking/popularDay.nhn?rankingType=popular_day&sectionId=105";
	private final String TITLE_TAG = "div.ranking_headline a[title]";
	private final String CONTENT_TAG = "div.ranking_lede";
	private final String AID_TAG = "div.ranking_headline a";
	private final String IMAGE_TAG = "div.ranking_thumb img";
	private final String VIEW_TAG = "div.ranking_view";

	private Document doc = null;

	@Bean
	public List<Elements> run() {

		List<Elements> elementsList = new ArrayList<>();

		setHeaderOption();

		// TITLE
		Elements titles = doc.select(TITLE_TAG);
		elementsList.add(titles);

		// CONTENT
		Elements contents = doc.select(CONTENT_TAG);
		elementsList.add(contents);

		// AID
		Elements rankingHeadLineAs = doc.select(AID_TAG);
		elementsList.add(rankingHeadLineAs);

		// IMAGE
		Elements images = doc.select(IMAGE_TAG);
		elementsList.add(images);

		// VIEW
		Elements RankingViews = doc.select(VIEW_TAG);
		elementsList.add(RankingViews);

		return elementsList;
	}

	@Bean
	private void setHeaderOption() {

		// GET 방식으로 HTML 가져오기
		try {

			doc = Jsoup.connect(URL).get();

			// POST 방식으로 HTML 가져오기
			// doc = Jsoup.connect(URL).post();

			// 결과를 못가져오는 경우 header에 값을 추가해주어야한다.
			doc = Jsoup.connect(URL).userAgent(
					"Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36") // explorer,
																																		// chrome
																																		// 등
					.header("scheme", "https")
					.header("accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
					.header("accept-encoding", "gzip, deflate, br")
					.header("accept-language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,es;q=0.6") // language
					.header("cache-control", "no-cache").header("pragma", "no-cache") // 캐시
																						// 전략
					.header("upgrade-insecure-requests", "1").get();
			// 3. 가져온 HTML Document 를 확인하기
			// System.out.println(doc.toString());

			// text : 안에 값만
			// toString() : 전체 싹다

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
