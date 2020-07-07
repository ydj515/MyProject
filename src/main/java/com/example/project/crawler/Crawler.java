package com.example.project.crawler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.example.project.cv.CrawlingCV;
import com.example.project.domain.NewsItem;

@Component
public class Crawler {

	public Map<Integer, NewsItem> run(String threadNumber) throws IOException {

		Document doc = makeConnection(CrawlingCV.BASE_URL + threadNumber);
		Map<Integer, NewsItem> newsItemMap = new HashMap<>();
		String category = getCategory(threadNumber);
		Elements items = doc.select(CrawlingCV.NEWS_ITEM);

		for (int i = 0; i < items.size(); i++) {
			Elements titles = items.get(i).select(CrawlingCV.TITLE_TAG);// TITLE
			Elements contents = items.get(i).select(CrawlingCV.CONTENT_TAG); // CONTENT
			Elements rankingHeadLineAs = items.get(i).select(CrawlingCV.AID_TAG); // AID
			String url = getNewsUrl(rankingHeadLineAs);
			Elements images = items.get(i).select(CrawlingCV.IMAGE_TAG); // IMAGE
			Elements rankingViews = items.get(i).select(CrawlingCV.VIEW_TAG); // VIEW

			NewsItem newsItem = makeNewsItem(category, url, titles, contents, images, rankingViews, rankingHeadLineAs);
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

	private String getCategory(String categoryNumber) {
		switch (Integer.valueOf(categoryNumber)) {
		case 100:
			return CrawlingCV.POLITICS;
		case 101:
			return CrawlingCV.ECONOMY;
		case 102:
			return CrawlingCV.SOCIETY;
		case 103:
			return CrawlingCV.CULTURE;
		case 104:
			return CrawlingCV.GLOBAL;
		case 105:
			return CrawlingCV.IT;
		default:
			System.out.println("Category Error");
			return "";
		}
	}

	private String getNewsUrl(Elements rankingHeadLineAs) {
		return CrawlingCV.NEWS_BASE_URL + rankingHeadLineAs.attr("href").replace("amp;", "");
	}

	private NewsItem makeNewsItem(String category, String url, Elements titles, Elements contents, Elements images,
			Elements rankingViews, Elements rankingHeadLineAs) {
		return new NewsItem(category, url, titles.text(), contents.text(), images.attr(CrawlingCV.SRC_TAG),
				rankingViews.text(), makeAid(rankingHeadLineAs));
	}

	private String makeAid(Elements rankingHeadLineAs) {
		String aHref = rankingHeadLineAs.attr("href");
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder = findString(stringBuilder, CrawlingCV.OID_PATTERN, aHref);
		stringBuilder = findString(stringBuilder, CrawlingCV.AID_PATTERN, aHref);

		return stringBuilder.toString();
	}

	private StringBuilder findString(StringBuilder stringBuilder, String patternString, String aHref) {
		Pattern oidPattern = Pattern.compile(patternString);
		Matcher matcher = oidPattern.matcher(aHref);

		while (matcher.find()) {
			stringBuilder.append(matcher.group(1));
		}

		return stringBuilder;
	}

}
