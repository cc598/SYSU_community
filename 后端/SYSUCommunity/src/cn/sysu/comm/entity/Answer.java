package cn.sysu.comm.entity;

import java.util.Date;

public class Answer {
	int ans_Id;
	String content;
	String authorId;
	int ques_id;
	Date releaseTime;
	public int getAns_Id() {
		return ans_Id;
	}
	public void setAns_Id(int ans_Id) {
		this.ans_Id = ans_Id;
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
	public int getQues_id() {
		return ques_id;
	}
	public void setQues_id(int ques_id) {
		this.ques_id = ques_id;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
}
