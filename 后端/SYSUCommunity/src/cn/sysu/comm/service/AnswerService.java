package cn.sysu.comm.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.jdbc.JdbcUtils;
import cn.sysu.comm.dao.AnswerMapper;
import cn.sysu.comm.dao.AnswerMapperImpl;
import cn.sysu.comm.entity.Answer;
import cn.sysu.comm.entity.Comment;

public class AnswerService {
	private AnswerMapper answerDao = new AnswerMapperImpl();
	
	public void add(Answer answer) {
		try {
			JdbcUtils.beginTransaction();
			answerDao.addAnswer(answer);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
		
		
	}

	public boolean delete(int ansId, String userid) {
		// 只有管理员和作者有权限删除
		Answer answer = answerDao.findAnswerById(ansId);
		if(answer.getAuthorId().equals(userid) || userid.equalsIgnoreCase("admin")){
			try {
				JdbcUtils.beginTransaction();
				answerDao.deleteAnswer(ansId);
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
		} else {
			return false;
		}
	}
	
	public List<Answer> loadMyAnswers(String userid) {
		return answerDao.findAnswersByAuthorId(userid);
	}
	
	public Answer findAnswerByCid(int ansId) {
		return answerDao.findAnswerById(ansId);
	}

	public List<Answer> findAnswersBykey(String keyword) {
		return answerDao.findAnswerByContent(keyword);
	}
	public Answer findLastInsertAnswer() {
		return answerDao.findLastInsert();
	}
}
