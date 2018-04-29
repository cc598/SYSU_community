package cn.sysu.comm.dao;

import java.util.List;

import cn.sysu.comm.entity.Answer;

/**
 * 
 * @Description: TODO
 * Answer数据层接口 
 * @CreateTime: 2018-4-24 下午8:16:29 
 * @author: bee
 * @version V1.0
 */
public interface AnswerMapper {
	/*
	 * 利用回答内容关键词查找回答
	 * 传入参数：content
	 * 返回List<Answer>
	 */
	public List<Answer> findAnswerByContent(String content);
	/*
	 * 利用作者ID查找作者的回答
	 * 传入参数：authorId
	 * 返回List<Answer>
	 */
	public List<Answer> findAnswersByAuthorId(String authorId);
	public Answer findAnswerById(int ansId);
	/*
	 * 增加回答
	 * 传入参数为一个Question对象
	 */
	public void addAnswer(Answer answer);
	/*
	 * 通过ansId删除回答
	 */
	public void deleteAnswer(int ansId);
	/*
	 * 更新文章
	 * 传入参数为一个Answer对象
	 */
	public void updateAnswer(Answer answer);
}
