package com.example.project.cv;

public class CrawlingCV {

	// Header 정보
	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36";
	public static final String SCHEME = "https";
	public static final String ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
	public static final String ACCEPT_ENCODING = "gzip, deflate, br";
	public static final String ACCEPT_LANGUAGE = "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,es;q=0.6";
	public static final String CACHE_CONTROL = "no-cache";
	public static final String PRAGMA = "no-cache";
	public static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";

	public static final String BASE_URL = "https://news.naver.com/main/ranking/popularDay.nhn?rankingType=popular_day&sectionId=";

	public static final String NEWS_ITEM = "li.ranking_item";

	public static final String TITLE_TAG = " > div.ranking_text > div.ranking_headline a[title]";
	public static final String CONTENT_TAG = " > div.ranking_text > div.ranking_lede";
	public static final String AID_TAG = " > div.ranking_text > div.ranking_headline a";
	public static final String IMAGE_TAG = " > div.ranking_thumb img";
	public static final String VIEW_TAG = " > div.ranking_text > div.ranking_view";

	public static final String SRC_TAG = "src";

	public static final String OID_PATTERN = "oid=(\\d+)";
	public static final String AID_PATTERN = "aid=(\\d+)";

	public static final String POLITICS = "정치";
	public static final String ECONOMY = "경제";
	public static final String SOCIETY = "사회";
	public static final String CULTURE = "생활/문화";
	public static final String GLOBAL = "세계";
	public static final String IT = "IT/과학";

	public static final String NEWS_BASE_URL = "https://news.naver.com";
}
