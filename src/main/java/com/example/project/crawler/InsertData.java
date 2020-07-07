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

			for (int i = 0; i < newsItemMap.size(); i++) {
				List<NewsVO> list = newsMapper.newsSelect();

				for (int j = 0; j < list.size(); j++) {
					dbHashMap.put(list.get(j).getAID(), list.get(j).getID());
				}

				NewsVO newsVO = new NewsVO();
				NewsInfoVO newsInfoVO = new NewsInfoVO();

				// string으로 다 세팅
				newsVO.setAID(newsItemMap.get(i).getAid());
				newsVO.setCATEGORY(newsItemMap.get(i).getCategory());
				newsVO.setURL(newsItemMap.get(i).getUrl());
				newsVO.setTITLE(newsItemMap.get(i).getTitle());
				newsVO.setCONTENT(newsItemMap.get(i).getContent());
				newsVO.setIMAGE(newsItemMap.get(i).getImage());

				// 이미 테이블에 데이터가 존재한다면 UPT_DT만 update
				if (dbHashMap.get(newsItemMap.get(i).getAid()) != null) {
					newsMapper.newsUpdate(dbHashMap.get(newsItemMap.get(i).getAid()));
					newsInfoVO.setNEWS_ID(dbHashMap.get(newsItemMap.get(i).getAid())); // int
				} else {
					newsMapper.newsInsert(newsVO);
					dbHashMap.put(newsItemMap.get(i).getAid(), newsMapper.newsSelectLastRow().getID());

					newsInfoVO.setNEWS_ID(newsMapper.newsSelectLastRow().getID()); // int
				}

				newsInfoVO.setRANK((i + 1)); // int
				newsInfoVO.setVIEW(Integer.parseInt(newsItemMap.get(i).getRankingViews().replace(",", ""))); // string

				newsInfoMapper.newsInfoInsert(newsInfoVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
