package com.example.project.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.select.Elements;

import com.example.project.cv.CrawlingCV;

public class ConvertElementToList {

	public static final String OID_PATTERN = "oid=(\\d+)";
	public static final String AID_PATTERN = "aid=(\\d+)";
	
	public static List<String> convertElementsToList(Elements rankingHeadLineAs) {

		List<String> aids = new ArrayList<>();

		for (int i = 0; i < rankingHeadLineAs.size(); i++) {
			String aHref = rankingHeadLineAs.get(i).attr("href");

			String aid = "";

			Pattern oidPattern = Pattern.compile(OID_PATTERN);

			Pattern aidPattern = Pattern.compile(CrawlingCV.AID_PATTERN);

			Matcher matcher = oidPattern.matcher(aHref);
			Matcher matcher2 = aidPattern.matcher(aHref);

			while (matcher.find()) {
				aid += matcher.group(1);
			}

			while (matcher2.find()) {
				aid += matcher2.group(1);
			}

			aids.add(aid);
		}

		return aids;
	}
}
