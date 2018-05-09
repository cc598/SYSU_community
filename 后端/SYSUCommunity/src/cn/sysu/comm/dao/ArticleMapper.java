package cn.sysu.comm.dao;

import java.util.List;

import cn.sysu.comm.entity.Article;
import cn.sysu.comm.entity.Comment;

/**
 * 
 * @Description: 
 * Article数据层接口：增删改查
 * @CreateTime: 2018-4-24 下午8:12:44 
 * @author: bee
 * @version V1.0
 */

public interface ArticleMapper {
	public List<Article> findArticleByName(String artName);
	public List<Article> findArticleByContent(String content);
	public List<Article> findArticlesByAuthorId(String authorId);
	public Article findArticleById(int artId);
	/*
	 * 增加文章
	 * 传入参数为一个Article对象
	 */
	public void addArticle(Article article);
	/*
	 * 通过artid删除文章
	 */
	public void deleteArticle(int artId);
	/*
	 * 更新文章
	 * 传入参数为一个Article对象
	 */
	public void updateArticle(Article article);
	/**
	 * 查询最后一条插入的记录
	 */
	public Article findLastInsert();
}
