package com.shopping.bbs.form;

public class commentForm {
	private int commentID;
	private int bbsID;
	private int comCategory;
	private String userID;
	private int isImage;
	private String content;
	
	String result;
	int num;
	int displayPost;
	int postNum;
	int pageNum;
	
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
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public int getIsImage() {
		return isImage;
	}
	public void setIsImage(int isImage) {
		this.isImage = isImage;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getDisplayPost() {
		return displayPost;
	}
	public void setDisplayPost(int displayPost) {
		this.displayPost = displayPost;
	}
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
}
