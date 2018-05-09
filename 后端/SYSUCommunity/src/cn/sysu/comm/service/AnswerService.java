package cn.sysu.comm.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.jdbc.JdbcUtils;
import cn.sysu.comm.dao.AnswerMapper;
import cn.sysu.comm.dao.AnswerMapperImpl;
import cn.sysu.comm.entity.Answer;

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
		try {
			JdbcUtils.beginTransaction();
			Answer answer = answerDao.findAnswerById(ansId);
			if(answer.getAuthorId().equals(userid)){
				answerDao.deleteAnswer(ansId);//只能删除当前的
			}
			else {
				return false;
			}
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
	
	public List<Answer> loadMyAnswers(String userid) {
		return answerDao.findAnswersByAuthorId(userid);
	}
	
	public Answer findAnswerByCid(int ansId) {
		return answerDao.findAnswerById(ansId);
	}

	public List<Answer> findAnswersBykey(String keyword) {
		return answerDao.findAnswerByContent(keyword);
	}
	
}
