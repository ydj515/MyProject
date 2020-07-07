package com.example.project.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.project.cv.CrawlingCV;
import com.example.project.domain.NewsItem;

@Component
public class Crawler {

	@Bean
	public Map<Integer, NewsItem> run() throws IOException {

		Document doc = makeConnection(CrawlingCV.URL);

		Map<Integer, NewsItem> newsItemMap = new HashMap<>();

		List<String> categories = new ArrayList<String>();

		Elements categoryElements = doc.select("li.ranking_category_item");

		// thread 돌려서 한번에 parsing

		Elements items = doc.select(CrawlingCV.NEWS_ITEM);

		for (int i = 0; i < items.size(); i++) {
			Elements titles = items.get(i).select(CrawlingCV.TITLE_TAG);// TITLE
			Elements contents = items.get(i).select(CrawlingCV.CONTENT_TAG); // CONTENT
			Elements rankingHeadLineAs = items.get(i).select(CrawlingCV.AID_TAG); // AID
			Elements images = items.get(i).select(CrawlingCV.IMAGE_TAG); // IMAGE
			Elements rankingViews = items.get(i).select(CrawlingCV.VIEW_TAG); // VIEW
			NewsItem newsItem = makeNewsItem("IT/과학", titles, contents, images, rankingViews, rankingHeadLineAs);
			newsItemMap.put(i, newsItem);
		}

		return newsItemMap;
	}

	/* GET */
	private Document makeConnection(String url) throws IOException {
		return Jsoup.connect(url).userAgent(CrawlingCV.USER_AGENT).header("scheme", CrawlingCV.SCHEME)
				.header("accept", CrawlingCV.ACCEPT).header("accept-encoding", CrawlingCV.ACCEPT_ENCODING)
				.header("accept-language", CrawlingCV.ACCEPT_LANGUAGE).header("cache-control", CrawlingCV.CACHE_CONTROL)
				.header("pragma", CrawlingCV.PRAGMA).get();
	}

	private NewsItem makeNewsItem(String category, Elements titles, Elements contents, Elements images,
			Elements rankingViews, Elements rankingHeadLineAs) {
		String aid = makeAid(rankingHeadLineAs);
		return new NewsItem(category, titles.text(), contents.text(), images.attr(CrawlingCV.SRC_TAG),
				rankingViews.text(), aid);
	}

	private String makeAid(Elements rankingHeadLineAs) {
		String aHref = rankingHeadLineAs.attr("href");

		StringBuilder stringBuilder = new StringBuilder();

		Pattern oidPattern = Pattern.compile(CrawlingCV.OID_PATTERN);
		Pattern aidPattern = Pattern.compile(CrawlingCV.AID_PATTERN);

		Matcher matcher = oidPattern.matcher(aHref);
		Matcher matcher2 = aidPattern.matcher(aHref);

		while (matcher.find()) {
			stringBuilder.append(matcher.group(1));
		}

		while (matcher2.find()) {
			stringBuilder.append(matcher2.group(1));
		}

		return stringBuilder.toString();
	}

}
