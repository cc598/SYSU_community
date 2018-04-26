package cn.sysu.comm.entity;

import java.util.Date;
import java.util.List;

public class Article {
	String art_id;
	String title;
	String content;
	String authorId;
	Date relaseTime;
	Date lastChangeTime;
	String classification;
	List<Comment> comments;
	public String getArt_id() {
		return art_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setArt_id(String art_id) {
		this.art_id = art_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public Date getRelaseTime() {
		return relaseTime;
	}
	public void setRelaseTime(Date relaseTime) {
		this.relaseTime = relaseTime;
	}
	public Date getLastChangeTime() {
		return lastChangeTime;
	}
	public void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
}