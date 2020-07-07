package com.example.project.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsItem {

	private String category;
	private String title;
	private String content;
	private String image;
	private String rankingViews;
	private String aid;

	public NewsItem(String category, String title, String content, String image, String rankingViews, String aid) {
		this.category = category;
		this.title = title;
		this.content = content;
		this.image = image;
		this.rankingViews = rankingViews;
		this.aid = aid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRankingViews() {
		return rankingViews;
	}

	public void setRankingViews(String rankingViews) {
		this.rankingViews = rankingViews;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}
}
