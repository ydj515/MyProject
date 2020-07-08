package com.example.project.crawler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

import com.example.project.domain.NewsInfoVO;
import com.example.project.domain.NewsItem;
import com.example.project.domain.NewsVO;
import com.example.project.mapper.NewsInfoMapper;
import com.example.project.mapper.NewsMapper;

@Configuration
public class InsertData {

	public String insert(Map<Integer, NewsItem> newsItemMap, NewsMapper newsMapper, NewsInfoMapper newsInfoMapper) {

		try {
			HashMap<String, Integer> dbHashMap = new HashMap<>(); // aid: id

			for (Integer key : newsItemMap.keySet()) {
				getNewsListAndPutMap(newsMapper, dbHashMap);

				NewsVO newsVO = makeNewsVO(newsItemMap, key);
				NewsInfoVO newsInfoVO = makeNewsInfoVO(newsItemMap, key);

				// 이미 테이블에 데이터가 존재한다면 UPT_DT만 update
				if (dbHashMap.get(newsItemMap.get(key).getAid()) != null) {
					newsMapper.newsUpdate(dbHashMap.get(newsItemMap.get(key).getAid()));
				} else {
					newsMapper.newsInsert(newsVO);
					getNewsListAndPutMap(newsMapper, dbHashMap);
				}
				newsInfoVO.setNEWS_ID(dbHashMap.get(newsItemMap.get(key).getAid())); // int
				newsInfoMapper.newsInfoInsert(newsInfoVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private void getNewsListAndPutMap(NewsMapper newsMapper, HashMap<String, Integer> dbHashMap) throws Exception {
		newsMapper.newsSelect().stream().forEach(i -> dbHashMap.put(i.getAID(), i.getID()));
	}

	private NewsVO makeNewsVO(Map<Integer, NewsItem> newsItemMap, Integer key) {
		NewsVO newsVO = new NewsVO();
		// string으로 다 세팅
		newsVO.setAID(newsItemMap.get(key).getAid());
		newsVO.setCATEGORY(newsItemMap.get(key).getCategory());
		newsVO.setURL(newsItemMap.get(key).getUrl());
		newsVO.setTITLE(newsItemMap.get(key).getTitle());
		newsVO.setCONTENT(newsItemMap.get(key).getContent());
		newsVO.setIMAGE(newsItemMap.get(key).getImage());

		return newsVO;
	}

	private NewsInfoVO makeNewsInfoVO(Map<Integer, NewsItem> newsItemMap, Integer key) {
		NewsInfoVO newsInfoVO = new NewsInfoVO();

		newsInfoVO.setRANK((key + 1)); // int
		newsInfoVO.setVIEW(Integer.parseInt(newsItemMap.get(key).getRankingViews().replace(",", ""))); // string

		return newsInfoVO;
	}

}
