package cn.sysu.comm.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.sysu.comm.entity.Comment;
import cn.sysu.comm.service.CommentService;

/**
 * 
 * @Description: 作为Comment专用的的Servlet
 * 1.添加comment
 * 2.删除comment
 * 3.查看我的comment
 * 4.关键词查找评论
 * @CreateTime: 2018-5-8 下午4:36:37 
 * @author: bee
 */
public class CommentServlet extends BaseServlet {

	private CommentService commentService = new CommentService();
	
	/**
	 * 
	 * @Description: 增加一条评论
	 * 传入参数：content, art_id, authorId
	 * @author: bee
	 * @CreateTime: 2018-5-9 上午12:06:45 
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Comment comment = CommonUtils.toBean(request.getParameterMap(), Comment.class);
		// 作者id
		comment.setAuthorId((String) request.getSession().getAttribute("user_id"));
		// 发布时间
		Timestamp releaseTime = new Timestamp(new java.util.Date().getTime());
		comment.setReleaseTime(releaseTime);
		commentService.add(comment);
		// 重定向到文章页面
		return "r:/ArticleServlet?method=show&art_id="+comment.getArt_id();
	}
	
	/**
	 * 
	 * @Description: 删除评论
	 * 传入参数：评论id, 文章id
	 * @author: bee
	 * @CreateTime: 2018-5-9 上午12:32:37 
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("c_id");
		int c_id = Integer.parseInt(id);
		String userid = (String) request.getSession().getAttribute("user_id");
		if(commentService.delete(c_id, userid)) {
			return "r:/ArticleServlet?method=show&art_id="  + request.getParameter("art_id");
		} else {//删除失败，因为不是当前评论作者
			request.setAttribute("msg", "你没有权限删除此评论!");
			return "f:/ArticleServlet?method=show&art_id="  + request.getParameter("art_id");
		}
		
		
	}
	/**
	 * 
	 * @Description: 我的评论
	 * @author: bee
	 * @CreateTime: 2018-5-9 下午2:22:11 
	 */
	public String findMyComments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = (String) request.getSession().getAttribute("user_id");
		List<Comment> comments = commentService.loadMyComments(userid);
		request.setAttribute("foundList", comments);
		return "f:/comment_show.jsp";
	}

	/**
	 * 
	 * @Description: 通过关键词查找评论
	 * 传入参数：关键词
	 * @author: bee
	 * @CreateTime: 2018-5-9 下午4:40:34 
	 */
	public String findCommentsByKey(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Comment> result = commentService.findCommentsBykey(keyword);
		request.setAttribute("foundList", result);
		return "f:/commentJsps/show.jsp";
	}
}
