package cn.sysu.comm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.sysu.comm.entity.Article;
import cn.sysu.comm.entity.Comment;
import cn.sysu.comm.entity.User;

/*
author: kingchiu
 */

public class ArticleMapperImpl implements ArticleMapper {

	QueryRunner qRunner = new TxQueryRunner();	
	
	@Override
	public List<Article> findArticleByContent(String content) {
		String sql = "select * from article where content like ?";
		try {
			return qRunner.query(sql, new BeanListHandler<Article>(Article.class), "%"+content+"%");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Article findArticleById(int artId) {
		String sql = "select * from article where art_id = ?";
		try {
			return qRunner.query(sql, new BeanHandler<Article>(Article.class), artId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addArticle(Article article) {
		String sql = "insert into article(title, content, authorId, releaseTime, lastChangeTime,  classification) " +
						"values( ?, ?, ?, ?, ?, ?)";
		Object[] params = {article.getTitle(), article.getContent(), article.getAuthorId(), article.getReleaseTime(), article.getLastChangeTime(), article.getClassification()};
		try {
			qRunner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteArticle(int artId) {
		String sql = "DELETE FROM article WHERE art_id = ?";
		try {
			qRunner.update(sql, artId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void updateArticle(Article article) {
		String sql = "UPDATE article SET title=?, content=?, lastChangeTime=?, classification=? WHERE art_id = ?";
		Object[] params = {article.getTitle(), article.getContent(), article.getLastChangeTime(), 
				article.getClassification(),article.getArt_id()};
		try {
			qRunner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	@Override
	public List<Article> findArticleByName(String artName) {
		String sql = "select * from article where title like ?";
		try {
			return qRunner.query(sql, new BeanListHandler<Article>(Article.class), "%"+artName+"%");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Article findLastInsert() {
		String sql = "select * from article order by art_id desc limit 1";
		try {
			return qRunner.query(sql, new BeanHandler<Article>(Article.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Article> findArticlesByAuthorId(String authorId) {
		String sql = "select * from article where authorId = ?";
		try {
			return qRunner.query(sql, new BeanListHandler<Article>(Article.class), authorId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Article> findArticlesWithPage(int size) {
		String sql = "select * from article order by art_id limit 0,?";
		try {
			return qRunner.query(sql, new BeanListHandler<Article>(Article.class), size);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
