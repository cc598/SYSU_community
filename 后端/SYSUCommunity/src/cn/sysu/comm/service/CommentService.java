package cn.sysu.comm.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.jdbc.JdbcUtils;
import cn.sysu.comm.dao.CommentMapper;
import cn.sysu.comm.dao.CommentMapperImpl;
import cn.sysu.comm.entity.Comment;

public class CommentService {
	private CommentMapper commentDao = new CommentMapperImpl();

	public void add(Comment comment) {
		try {
			JdbcUtils.beginTransaction();
			commentDao.addComment(comment);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
		
		
	}

	public boolean delete(int c_id, String userid) {
		Comment comment = commentDao.findCommentById(c_id);
		if(comment.getAuthorId().equals(userid) || userid.equalsIgnoreCase("admin")){
			try {
				JdbcUtils.beginTransaction();
				commentDao.deleteComment(c_id);//只能删除当前的
				JdbcUtils.commitTransaction();
				return true;
			} catch (SQLException e) {
				try {
					JdbcUtils.rollbackTransaction();
					return false;
				} catch (SQLException e1) {
					throw new RuntimeException(e);
				}
			}
		}
		else {
			return false;
		}
	}
	
	public Comment findLastInsertComment() {
		return commentDao.findLastInsert();
	}
	
	public List<Comment> loadMyComments(String userid) {
		return commentDao.findCommentsByAuthorId(userid);
	}
	
	public Comment findCommentByCid(int cid) {
		return commentDao.findCommentById(cid);
	}

	public List<Comment> findCommentsBykey(String keyword) {
		return commentDao.findCommentByContent(keyword);
	}
	
}
