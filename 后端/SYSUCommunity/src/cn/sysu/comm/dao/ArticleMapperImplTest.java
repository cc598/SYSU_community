package cn.sysu.comm.dao;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;


import cn.sysu.comm.entity.Article;

public class ArticleMapperImplTest {

	@Test
	public void test() {
		ArticleMapperImpl impl = new ArticleMapperImpl();
		Article article = new Article();
		article.setTitle("u dont no who i am");
		article.setAuthorId("1");
		article.setClassification("2");
		article.setContent("milkwakeee");
		Date date = new Date(new java.util.Date().getTime());
		article.setRelaseTime(date);
		article.setLastChangeTime(date);
		impl.addArticle(article);
	}

	@Test
	public void test1() {
		ArticleMapperImpl impl = new ArticleMapperImpl();
		Article article = new Article();
		article.setArt_id(2);
		article.setTitle("u gotta no who i am");
		article.setAuthorId("1");
		article.setClassification("2");
		article.setContent("buuuulls");
		Date date = new Date(new java.util.Date().getTime());
		article.setRelaseTime(date);
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
		article.setRelaseTime(date);
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
		article.setRelaseTime(date);
		article.setLastChangeTime(date);
		System.out.println(impl.findArticleById(article.getArt_id()).getTitle());
	}
	@Test
	public void test4() {
		ArticleMapper impl = new ArticleMapperImpl();
		Article article = new Article();
		System.out.println(impl.findArticleByName("who i am").get(0).getTitle());
	}
	
	@Test
	public void test5() {
		ArticleMapper impl = new ArticleMapperImpl();
		Article article = new Article();
		System.out.println(impl.findArticleByContent("mil").get(0).getTitle());
	}
}
