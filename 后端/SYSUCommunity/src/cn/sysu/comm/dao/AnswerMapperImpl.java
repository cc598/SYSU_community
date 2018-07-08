package cn.sysu.comm.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.sysu.comm.entity.Answer;
import cn.sysu.comm.entity.Comment;
import cn.sysu.comm.entity.Question;

/*
author: kingchiu
 */

public class AnswerMapperImpl implements AnswerMapper {

	QueryRunner qRunner = new TxQueryRunner();
	
	@Override
	public List<Answer> findAnswerByContent(String content) {
		String sql = "select * from answer where content like ?";
		try {
			return qRunner.query(sql, new BeanListHandler<Answer>(Answer.class), "%"+content+"%");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Answer> findAnswersByAuthorId(String authorId) {
		String sql = "select * from answer where authorId = ?";
		try {
			return qRunner.query(sql, new BeanListHandler<Answer>(Answer.class), authorId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Answer findAnswerById(int ansId) {
		String sql = "select * from answer where ans_id =?";
		try {
			return qRunner.query(sql, new BeanHandler<Answer>(Answer.class), ansId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addAnswer(Answer answer) {
		String sql = "insert into answer(content, authorId, ques_id, releaseTime) " +
					"values(?, ?, ?, ?)";
		Object[] params = {answer.getContent(), answer.getAuthorId(), 
				answer.getQues_id(), answer.getReleaseTime()};
		try {
			qRunner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteAnswer(int ansId) {
		String sql = "delete from answer where ans_id = ?";
		Object[] params = {ansId};
		try {
			qRunner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void updateAnswer(Answer answer) {
		String sql = "update answer set content = ? where ans_id=?";
		Object[] params = {answer.getContent(), answer.getAns_id()};
		try {
			qRunner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Answer> loadAnswers(int ques_id) {
		String sql = "select * from answer where ques_id =?";
		try {
			return qRunner.query(sql, new BeanListHandler<Answer>(Answer.class), ques_id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Answer findLastInsert() {
		String sql = "select * from answer order by ans_id desc limit 1";
		try {
			return qRunner.query(sql, new BeanHandler<Answer>(Answer.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
