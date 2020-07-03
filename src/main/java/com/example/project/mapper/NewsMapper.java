package com.example.project.mapper;

import java.util.List;

import com.example.project.domain.NewsVO;

public interface NewsMapper {

	public void newsInsert(NewsVO vo) throws Exception;

	public List<NewsVO> newsSelect() throws Exception;

	public void newsUpdate(int id) throws Exception;

}
