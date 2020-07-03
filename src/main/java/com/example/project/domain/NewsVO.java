package com.example.project.domain;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsVO {

	private int ID;
	private String AID;
	private String TITLE;
	private String CONTENT;
	private String IMAGE;
	private Date REG_DT;
	private Date UPT_DT;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getAID() {
		return AID;
	}

	public void setAID(String AID) {
		this.AID = AID;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String TITLE) {
		this.TITLE = TITLE;
	}

	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String CONTENT) {
		this.CONTENT = CONTENT;
	}

	public String getIMAGE() {
		return IMAGE;
	}

	public void setIMAGE(String IMAGE) {
		this.IMAGE = IMAGE;
	}

	public Date getREG_DT() {
		return REG_DT;
	}

	public void setREG_DT(Date REG_DT) {
		this.REG_DT = REG_DT;
	}

	public Date getUPT_DT() {
		return UPT_DT;
	}

	public void setUPT_DT(Date UPT_DT) {
		this.UPT_DT = UPT_DT;
	}
}
