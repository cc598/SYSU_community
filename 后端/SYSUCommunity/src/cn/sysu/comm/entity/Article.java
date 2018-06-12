package cn.sysu.comm.entity;

import java.util.Date;
import java.util.List;

import cn.sysu.comm.dao.UserMapperImpl;

public class Article {
	int art_id;// 文章id
	String title; // 标题
	String content;//内容
	String authorId; // 作者id
	Date releaseTime; // 发布时间
	Date lastChangeTime; // 上次修改时间
	String classification; // 分类
	List<Comment> comments;
	public int getArt_id() {
		return art_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setArt_id(int art_id) {
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
	public String getAuthor() {
		return new UserMapperImpl().findUserById(authorId).getUsername();
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
	
	@Override
	public String toString() {
		return "Article [art_id=" + art_id + ", title=" + title + ", content="
				+ content + ", authorId=" + authorId + ", releaseTime="
				+ releaseTime + ", lastChangeTime=" + lastChangeTime
				+ ", classification=" + classification + ", comments="
				+ comments + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (art_id != other.art_id)
			return false;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (classification == null) {
			if (other.classification != null)
				return false;
		} else if (!classification.equals(other.classification))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (lastChangeTime == null) {
			if (other.lastChangeTime != null)
				return false;
		} else if (!lastChangeTime.equals(other.lastChangeTime))
			return false;
		if (releaseTime == null) {
			if (other.releaseTime != null)
				return false;
		} else if (!releaseTime.equals(other.releaseTime))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
}