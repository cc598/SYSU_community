package cn.sysu.comm.entity;

import java.util.Date;
import java.util.List;

public class Question {
	String question_id;
	String title;
	String content;
	String authorId;
	Date releaseTime;
	List<Answer> answers;
	
	public String getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(String questionId) {
		this.question_id = questionId;
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
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	
}
