package com.example.project.domain;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsInfoVO {

	private int ID;
	private int NEWS_ID;
	private int RANK;
	private int VIEW;
	private Date REG_DT;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getNEWS_ID() {
		return NEWS_ID;
	}

	public void setNEWS_ID(int NEWS_ID) {
		this.NEWS_ID = NEWS_ID;
	}

	public int getRANK() {
		return RANK;
	}

	public void setRANK(int RANK) {
		this.RANK = RANK;
	}

	public int getVIEW() {
		return VIEW;
	}

	public void setVIEW(int VIEW) {
		this.VIEW = VIEW;
	}

	public Date getREG_DT() {
		return REG_DT;
	}

	public void setREG_DT(Date REG_DT) {
		this.REG_DT = REG_DT;
	}

}
