package cn.sysu.comm.dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;


import cn.sysu.comm.entity.Article;

/*
author: kingchiu
 */

public class ArticleMapperImplTest {

	@Test
	public void test() {
		ArticleMapperImpl impl = new ArticleMapperImpl();
		Article article = new Article();
		article.setTitle("u dont no who i am");
		article.setAuthorId("1");
		article.setClassification("2");
		article.setContent("milkwakeee");
		Timestamp date = new Timestamp(new java.util.Date().getTime());
		article.setReleaseTime(date);
		article.setLastChangeTime(date);
		impl.addArticle(article);
	}

	@Test
	public void test1() {
		ArticleMapperImpl impl = new ArticleMapperImpl();
		Article article = new Article();
		article.setArt_id(5);
		article.setTitle("u gotta no who i am");
		article.setAuthorId("1");
		article.setClassification("2");
		article.setContent("buuuulls");
		Timestamp date = new Timestamp(new java.util.Date().getTime());
		article.setReleaseTime(date);
		article.setLastChangeTime(date);
		impl.updateArticle(article);
	}
	
	@Test
	public void test2() {
		ArticleMapper impl = new ArticleMapperImpl();
		Article article = new Article();
		article.setArt_id(2);
		article.setTitle("u gotta no who i am");
		article.setAuthorId("1");
		article.setClassification("2");
		article.setContent("buuuulls");
		Date date = new Date(new java.util.Date().getTime());
		article.setReleaseTime(date);
		article.setLastChangeTime(date);
		impl.deleteArticle(article.getArt_id());
	}
	
	@Test
	public void test3() {
		ArticleMapper impl = new ArticleMapperImpl();
		Article article = new Article();
		article.setArt_id(4);
		article.setTitle("u gotta no who i am");
		article.setAuthorId("1");
		article.setClassification("2");
		article.setContent("buuuulls");
		Date date = new Date(new java.util.Date().getTime());
		article.setReleaseTime(date);
		article.setLastChangeTime(date);
		System.out.println(impl.findArticleById(article.getArt_id()).getTitle());
	}
	@Test
	public void test4() {
		ArticleMapper impl = new ArticleMapperImpl();
		List<Article> contentList = impl.findArticleByContent("");
		List<Article> nameList = impl.findArticleByName("");
		contentList.removeAll(nameList);
		System.out.println(contentList);
	}
	
	@Test
	public void test5() {
		ArticleMapper impl = new ArticleMapperImpl();
		Article article = new Article();
		System.out.println(impl.findArticleByContent("mil").get(0).getTitle());
	}
}
