package cn.sysu.comm.dao;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import cn.sysu.comm.entity.Question;

/*
author: kingchiu
 */

public class QuestionMapperImplTest {

	@Test
	public void test() {
		QuestionMapper impl = new QuestionMapperImpl();
		Question question = new Question();
		question.setAuthorId("1");
		question.setContent("why am i so so ugly");
		Date releaseTime = new Date(new java.util.Date().getTime());
		question.setReleaseTime(releaseTime);
		question.setTitle("dumb n silly crying");
		impl.addQuestion(question);
	}
	
	@Test
	public void test1() {
		QuestionMapper impl = new QuestionMapperImpl();
		Question question = new Question();
		question.setQuestion_id(3);
		question.setAuthorId("1");
		question.setContent("why am i so so ugly yeah i know that!");
		Date releaseTime = new Date(new java.util.Date().getTime());
		question.setReleaseTime(releaseTime);
		question.setTitle("dumb crying lol");
		impl.updateQuestion(question);
	}

	@Test
	public void test2() {
		QuestionMapper impl = new QuestionMapperImpl();
		Question question = new Question();
		question.setQuestion_id(2);
		question.setAuthorId("1");
		question.setContent("why am i so so ugly yeah i know that!");
		Date releaseTime = new Date(new java.util.Date().getTime());
		question.setReleaseTime(releaseTime);
		question.setTitle("dumb crying lol");
		impl.deleteQuestion(question.getQuestion_id());
	}
	
	@Test
	public void test3() {
		QuestionMapper impl = new QuestionMapperImpl();
		Question question = new Question();
		question.setQuestion_id(1);
		question.setAuthorId("1");
		question.setContent("why am i so so ugly yeah i know that!");
		Date releaseTime = new Date(new java.util.Date().getTime());
		question.setReleaseTime(releaseTime);
		question.setTitle("dumb crying lol");
		System.out.println(impl.findQuetionById(question.getQuestion_id()).getContent());
	}
	
	@Test
	public void test4() {
		QuestionMapper impl = new QuestionMapperImpl();
		System.out.println(impl.findQuestionByName("um").get(0).getTitle());
	}
	
	@Test
	public void test5() {
		QuestionMapper impl = new QuestionMapperImpl();
		System.out.println(impl.findQuestionByContent("ugly").get(0).getTitle());
	}
}
