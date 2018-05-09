package cn.sysu.comm.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.JdbcUtils;
import cn.sysu.comm.dao.ArticleMapper;
import cn.sysu.comm.dao.ArticleMapperImpl;
import cn.sysu.comm.dao.CommentMapper;
import cn.sysu.comm.dao.CommentMapperImpl;
import cn.sysu.comm.entity.Article;

public class ArticleService {
	ArticleMapper articleDao = new ArticleMapperImpl();
	CommentMapper commentDao = new CommentMapperImpl();
	/**
	 * 
	 * @Description: 
	 * 业务层 增加文章
	 * @author: bee
	 * @CreateTime: 2018-5-8 下午5:18:46 
	 */
	public void add(Article article) {
		try {
			JdbcUtils.beginTransaction();
			articleDao.addArticle(article);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try{
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {// 一般不会到这一步
				throw new RuntimeException(e);
			}
		}
	}
	/**
	 * 
	 * @Description: 更新文章
	 * @author: bee
	 * @CreateTime: 2018-5-8 下午5:20:20 
	 * @param article  
	 */
	public void update(Article article) {
		try {
			JdbcUtils.beginTransaction();
			articleDao.updateArticle(article);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try{
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {// 一般不会到这一步
				throw new RuntimeException(e);
			}
		}
	}
	
	public void delete(int art_id) {
		try {
			JdbcUtils.beginTransaction();
			articleDao.deleteArticle(art_id);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try{
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {// 一般不会到这一步
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 
	 * @Description: 通过art_id查找文章,同时加载评论
	 * @author: bee
	 * @CreateTime: 2018-5-8 下午6:56:04 
	 */
	public Article findArticle(int art_id) {
		Article article = articleDao.findArticleById(art_id);
		article.setComments(commentDao.loadComments(art_id));
		return article;
	}
	
	/**
	 * 
	 * @Description: 查找最后一条插入的记录的文章id
	 * @author: bee
	 * @CreateTime: 2018-5-8 下午6:56:25 
	 */
	public int findLastInsertArticleId() {
		return articleDao.findLastInsert().getArt_id();
	}

	/**
	 * 
	 * @Description: 关键词查找
	 * @author: bee
	 * @CreateTime: 2018-5-8 下午7:05:53 
	 */
	public List<Article> findByKeywords(String key) {
		
		List<Article> contentList = articleDao.findArticleByContent(key);
		List<Article> nameList = articleDao.findArticleByName(key);

		contentList.removeAll(nameList);// 去重
		System.out.println(contentList);
		nameList.addAll(contentList);// 求并集
		return nameList;
	}
}
