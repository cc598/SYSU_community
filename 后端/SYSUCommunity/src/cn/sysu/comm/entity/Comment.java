package cn.sysu.comm.entity;

import java.util.Date;

import cn.sysu.comm.dao.ArticleMapperImpl;
import cn.sysu.comm.dao.UserMapperImpl;

public class Comment {
	int c_id;
	String content;
	String authorId;
	int art_id;
	Date releaseTime;
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
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
	public String getAuthor(){
		return new UserMapperImpl().findUserById(authorId).getUsername();
	}
	public String getArticle(){
		return new ArticleMapperImpl().findArticleById(art_id).getTitle();
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	public int getArt_id() {
		return art_id;
	}
	public void setArt_id(int art_id) {
		this.art_id = art_id;
	}
	
	
}