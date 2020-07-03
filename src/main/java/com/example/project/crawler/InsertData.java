package com.example.project.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.select.Elements;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.project.cv.CrawlingCV;
import com.example.project.domain.NewsInfoVO;
import com.example.project.domain.NewsVO;
import com.example.project.mapper.NewsInfoMapper;
import com.example.project.mapper.NewsMapper;

@Configuration
public class InsertData {

	public static final String SRC_TAG = "src";

	@Bean
	public String insert(List<Elements> elementsList, NewsMapper newsMapper, NewsInfoMapper newsInfoMapper) {

		Elements titles = elementsList.get(0);
		Elements contents = elementsList.get(1);
		Elements rankingHeadLineAs = elementsList.get(2);
		Elements images = elementsList.get(3);
		Elements RankingViews = elementsList.get(4);

		List<String> aids = ConvertElementToList.convertElementsToList(rankingHeadLineAs);

		try {
			for (int i = 0; i < titles.size(); i++) {

				HashMap<String, Integer> hashMap = new HashMap<>(); // aid: id
				List<NewsVO> list = new ArrayList<>();

				list = newsMapper.newsSelect();

				for (int j = 0; j < list.size(); j++) {
					hashMap.put(list.get(j).getAID(), list.get(j).getID());
				}

				String imgSrc = images.get(i).attr(SRC_TAG);

				NewsVO newsVO = new NewsVO();
				NewsInfoVO newsInfoVO = new NewsInfoVO();

				newsVO.setAID(aids.get(i)); // string
				newsVO.setTITLE(titles.get(i).text()); // string
				newsVO.setCONTENT(contents.get(i).text()); // string
				newsVO.setIMAGE(imgSrc); // string

				if (hashMap.get(aids.get(i)) != null) { // 이미 테이블에 데이터가
														// 존재한다면 UPT_DT만 update
					newsMapper.newsUpdate(hashMap.get(aids.get(i)));

				} else {
					newsMapper.newsInsert(newsVO);
					hashMap.put(aids.get(i), hashMap.size() + 1);
				}

				newsInfoVO.setNEWS_ID(hashMap.get(aids.get(i))); // int
				newsInfoVO.setRANK((i + 1)); // int
				newsInfoVO.setVIEW(Integer.parseInt(RankingViews.get(i).text().replace(",", ""))); // string

				newsInfoMapper.newsInfoInsert(newsInfoVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
