package cn.sysu.comm.dao;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import cn.sysu.comm.entity.Comment;


/*
author: kingchiu
 */

public class CommentMapperImplTest {

	@Test
	public void test() {
		CommentMapper impl = new CommentMapperImpl();
		Comment comment =  new Comment();
		comment.setArt_id(4);
		comment.setAuthorId("2");
		Date releaseTime = new Date(new java.util.Date().getTime());
		comment.setReleaseTime(releaseTime);
		comment.setContent("this article cool haha");
		impl.addComment(comment);
	}
	

	@Test
	public void test1() {
		CommentMapper impl = new CommentMapperImpl();
		Comment comment =  new Comment();
		comment.setArt_id(4);
		comment.setAuthorId("2");
		Date releaseTime = new Date(new java.util.Date().getTime());
		comment.setReleaseTime(releaseTime);
		comment.setContent("this article cool haha");
		impl.deleteComment(1);
	}
	
	@Test
	public void test2() {
		CommentMapper impl = new CommentMapperImpl();
		System.out.println(impl.findCommentsByAuthorId("2").get(0).getContent());
	}
	@Test
	public void test3() {
		CommentMapper impl = new CommentMapperImpl();
		System.out.println(impl.findCommentByContent("arti").get(0).getContent());
	}
	
	@Test
	public void test4() {
		CommentMapper impl = new CommentMapperImpl();
		System.out.println(impl.findCommentById(2).getContent());
	}
	@Test
	public void test5() {
		CommentMapper impl = new CommentMapperImpl();
		System.out.println(impl.loadComments(4));
	}
}
