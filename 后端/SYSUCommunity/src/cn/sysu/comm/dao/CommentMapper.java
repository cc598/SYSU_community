package cn.sysu.comm.dao;

import java.util.List;

import cn.sysu.comm.entity.Answer;
import cn.sysu.comm.entity.Comment;

/**
 * 
 * @Description: 
 *  comment数据层接口
 *  实现对comment在数据库中的操作：增删查
 * @CreateTime: 2018-4-24 下午8:13:58 
 * @author: bee
 * @version V1.0
 */
public interface CommentMapper {
	
	/*
	 * 通过评论内容查找评论
	 * 传入参数：关键词
	 * 返回List<Comment>
	 */
	public List<Comment> findCommentByContent(String content);
	/*
	 * 通过作者Id查找其评论
	 * 参数：作者Id
	 * 返回List<Comment>
	 */
	public List<Comment> findCommentsByAuthorId(String authorId);
	/*
	 * 通过commentId查找评论
	 * 参数：comment的id
	 * 返回comment对象
	 */
	public Comment findCommentById(int c_id);
	/*
	 * 查找文章下的评论
	 * 传入参数:文章的id
	 * 返回List<Comment>
	 */
	public List<Comment> loadComments(int artId);
	/*
	 * 增加问题
	 * 传入参数为一个Comment对象
	 */
	public void addComment(Comment comment);
	/*
	 * 通过c_id删除问题
	 */
	public void deleteComment(int c_id);
	/**
	 * 通过
	 */
}
