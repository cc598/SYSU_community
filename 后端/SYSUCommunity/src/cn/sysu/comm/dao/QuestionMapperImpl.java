package cn.sysu.comm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.sysu.comm.entity.Article;
import cn.sysu.comm.entity.Question;

/*
author: kingchiu
 */

public class QuestionMapperImpl implements QuestionMapper {
	
	QueryRunner qRunner = new TxQueryRunner();
	
	@Override
	public List<Question> findQuestionByName(String question) {
		String sql = "select * from question where title like ?";
		try {
			return qRunner.query(sql, new BeanListHandler<Question>(Question.class), "%"+question+"%");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Question> findQuestionByContent(String content) {
		String sql = "select * from question where content like ?";
		try {
			return qRunner.query(sql, new BeanListHandler<Question>(Question.class), "%"+content+"%");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Question findQuetionById(int quesId) {
		String sql = "select * from question where question_id = ?";
		try {
			return qRunner.query(sql, new BeanHandler<Question>(Question.class), quesId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Question> findQuestionsByAuthorId(String authorId) {
		String sql = "select * from question where authorId = ?";
		try {
			return qRunner.query(sql, new BeanListHandler<Question>(Question.class), authorId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int findLastInsert() {
		String sql = "select question_id from question order by question_id desc limit 1";
		try {
			return (Integer) qRunner.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addQuestion(Question question) {
		String sql = "insert into question(title, content, authorId, releaseTime) " +
				"values(?, ?, ?, ?)";
		Object[] params = {question.getTitle(), question.getContent(), 
				question.getAuthorId(),question.getReleaseTime()};
		try {
			qRunner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void deleteQuestion(int quesId) {
		String sql = "delete from question where question_id = ?";
		try{
			qRunner.update(sql, quesId);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void updateQuestion(Question question) {
		String sql = "update question set title=?, content=? where question_id=?";
		Object[] params = {question.getTitle(), question.getContent(),question.getQuestion_id()};
		try {
			qRunner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Question> findQuestionsWithPage(int size) {
		String sql = "select * from question order by question_id limit 0,?";
		try {
			return qRunner.query(sql, new BeanListHandler<Question>(Question.class), size);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
