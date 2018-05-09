package cn.sysu.comm.service;

import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;

import cn.itcast.jdbc.JdbcUtils;
import cn.sysu.comm.dao.AnswerMapper;
import cn.sysu.comm.dao.AnswerMapperImpl;
import cn.sysu.comm.dao.QuestionMapper;
import cn.sysu.comm.dao.QuestionMapperImpl;
import cn.sysu.comm.entity.Question;

public class QuestionService {
	private QuestionMapper questionDao = new QuestionMapperImpl();
	private AnswerMapper answerDao = new AnswerMapperImpl();
	
	public void add(Question question) {
		try {
			JdbcUtils.beginTransaction();
			questionDao.addQuestion(question);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}

	}
	
	public int findLastInsertQuestionId() {
		return questionDao.findLastInsert();
	}

	public Question findQuestion(int ques_id) {
		Question question = questionDao.findQuetionById(ques_id);
		question.setAnswers(answerDao.loadAnswers(ques_id));
		return question;
	}

	public void update(Question question) {
		try {
			JdbcUtils.beginTransaction();
			questionDao.updateQuestion(question);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
		
	}

	public void delete(int ques_id) {
		try {
			JdbcUtils.beginTransaction();
			questionDao.deleteQuestion(ques_id);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
	}

	public List<Question> findByKeywords(String key) {
		List<Question> contentList = questionDao.findQuestionByContent(key);
		List<Question> nameList = questionDao.findQuestionByName(key);
		contentList.removeAll(nameList);
		nameList.addAll(contentList);
		return nameList;
	}
	
}
