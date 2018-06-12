package cn.sysu.comm.entity;

import java.util.Date;

import cn.sysu.comm.dao.QuestionMapperImpl;
import cn.sysu.comm.dao.UserMapperImpl;

public class Answer {
	int ans_id;
	String content;
	String authorId;
	int ques_id;
	Date releaseTime;
	public int getAns_id() {
		return ans_id;
	}
	public void setAns_id(int ans_id) {
		this.ans_id = ans_id;
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
	public String getAuthor() {
		return new UserMapperImpl().findUserById(authorId).getUsername();
	}
	public String getQuestion(){
		return new QuestionMapperImpl().findQuetionById(ques_id).getTitle();
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
