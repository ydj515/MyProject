package com.example.project.cv;

public class CrawlingCV {

	public static final String URL = "https://news.naver.com/main/ranking/popularDay.nhn?rankingType=popular_day&sectionId=105";
	public static final String TITLE_TAG = "div.ranking_headline a[title]";
	public static final String CONTENT_TAG = "div.ranking_lede";
	public static final String AID_TAG = "div.ranking_headline a";
	public static final String IMAGE_TAG = "div.ranking_thumb img";
	public static final String VIEW_TAG = "div.ranking_view";
	public static final String SRC_TAG = "src";

	public static final String OID_PATTERN = "oid=(\\d+)";
	public static final String AID_PATTERN = "aid=(\\d+)";

}
