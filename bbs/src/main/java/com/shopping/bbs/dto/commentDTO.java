package com.shopping.bbs.dto;

public class commentDTO {
	private int commentID;
	private int bbsID;
	private int comCategory;
	private String userID;
	private String content;
	
	private String result;
	
	public int getBbsID() {
		return bbsID;
	}
	public void setBbsID(int bbsID) {
		this.bbsID = bbsID;
	}
	public int getComCategory() {
		return comCategory;
	}
	public void setComCategory(int comCategory) {
		this.comCategory = comCategory;
	}
	public int getCommentID() {
		return commentID;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
