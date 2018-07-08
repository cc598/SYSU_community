package cn.sysu.comm.dao;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import cn.sysu.comm.entity.Answer;

/*
author: kingchiu
 */

public class AnswerMapperImplTest {

	@Test
	public void test() {
		AnswerMapper impl = new AnswerMapperImpl();
		Answer answer = new Answer();
		answer.setAuthorId("1");
		answer.setContent("nope u r cool");
		answer.setQues_id(3);
		Date releaseTime = new Date(new java.util.Date().getTime());
		answer.setReleaseTime(releaseTime);
		impl.addAnswer(answer);
	}
	
	@Test
	public void test1() {
		AnswerMapper impl = new AnswerMapperImpl();
		Answer answer = new Answer();
		answer.setAns_id(2);
		answer.setAuthorId("1");
		answer.setContent("yes i am dumb, u r cool");
		answer.setQues_id(3);
		Date releaseTime = new Date(new java.util.Date().getTime());
		answer.setReleaseTime(releaseTime);
		impl.updateAnswer(answer);
	}
	
	@Test
	public void test3() {
		AnswerMapper impl = new AnswerMapperImpl();
		Answer answer = new Answer();
		answer.setAns_id(2);
		answer.setAuthorId("1");
		answer.setContent("yes i am dumb, u r cool");
		answer.setQues_id(3);
		Date releaseTime = new Date(new java.util.Date().getTime());
		answer.setReleaseTime(releaseTime);
		impl.deleteAnswer(2);
	}

	@Test
	public void test4() {
		AnswerMapper impl = new AnswerMapperImpl();
		System.out.println(impl.findAnswersByAuthorId("11").size());
	}
	
	@Test
	public void test5() {
		AnswerMapper impl = new AnswerMapperImpl();
		System.out.println(impl.findAnswerByContent("nope u r").size());
	}
	
	@Test
	public void test6() {
		AnswerMapper impl = new AnswerMapperImpl();
		System.out.println(impl.findAnswerById(1));
	}
}
