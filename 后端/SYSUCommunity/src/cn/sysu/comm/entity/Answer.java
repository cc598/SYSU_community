package cn.sysu.comm.entity;

import java.util.Date;

public class Answer {
	String ans_Id;
	String text;
	String authorId;
	String ques_id;
	Date releaseTime;
	public String getAns_Id() {
		return ans_Id;
	}
	public void setAns_Id(String ans_Id) {
		this.ans_Id = ans_Id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getQues_id() {
		return ques_id;
	}
	public void setQues_id(String ques_id) {
		this.ques_id = ques_id;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
}
