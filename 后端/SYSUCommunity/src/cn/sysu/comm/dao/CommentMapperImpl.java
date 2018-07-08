package cn.sysu.comm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.sysu.comm.entity.Answer;
import cn.sysu.comm.entity.Article;
import cn.sysu.comm.entity.Comment;

/*
author: kingchiu
 */

public class CommentMapperImpl implements CommentMapper {

	QueryRunner qRunner = new TxQueryRunner();
	
	@Override
	public List<Comment> findCommentByContent(String content) {
		String sql = "select * from t_comment where content like ?";
		try {
			return qRunner.query(sql, new BeanListHandler<Comment>(Comment.class), "%"+content+"%");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Comment> findCommentsByAuthorId(String authorId) {
		String sql = "select * from t_comment where authorId = ?";
		try {
			return qRunner.query(sql, new BeanListHandler<Comment>(Comment.class), authorId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Comment findCommentById(int c_id) {
		String sql = "select * from t_comment where c_id =?";
		try {
			return qRunner.query(sql, new BeanHandler<Comment>(Comment.class), c_id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Comment> loadComments(int artId) {
		String sql = "select * from t_comment where art_id =?";
		try {
			return qRunner.query(sql, new BeanListHandler<Comment>(Comment.class), artId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addComment(Comment comment) {
		String sql = "insert into t_comment(content, authorId, art_id, releaseTime) " +
				"values(?, ?, ?, ?)";
		Object[] params = {comment.getContent(), comment.getAuthorId(), 
				comment.getArt_id(), comment.getReleaseTime()};
		try {
			qRunner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void deleteComment(int c_id) {
		String sql = "delete from t_comment where c_id = ?";
		Object[] params = {c_id};
		try {
			qRunner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Comment findLastInsert() {
		String sql = "select * from t_comment order by c_id desc limit 1";
		try {
			return qRunner.query(sql, new BeanHandler<Comment>(Comment.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
