package cn.sysu.comm.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.sysu.comm.entity.Answer;
import cn.sysu.comm.entity.Comment;
import cn.sysu.comm.service.AnswerService;
import cn.sysu.json.helper.Util;

public class AnswerServlet extends BaseServlet {

	private AnswerService answerService = new AnswerService();
	
	
	/**
	 * 
	 * @Description: 增加一条回答
	 * 传入参数:content, ques_id, authorId(通过session)
	 * @author: bee
	 * @CreateTime: 2018-5-9 下午6:35:17 
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Answer answer = CommonUtils.toBean(request.getParameterMap(), Answer.class);
		// 作者id
		answer.setAuthorId((String) request.getSession().getAttribute("user_id"));
		// 发布时间
		Timestamp releaseTime = new Timestamp(new java.util.Date().getTime());
		answer.setReleaseTime(releaseTime);
		answerService.add(answer);
		// 重定向到问题页面
		String json = Util.beanToJson(answerService.findLastInsertAnswer(), "yyyy-MM-dd HH:mm:ss");
		response.getWriter().write(json);
		
	//	return "r:/QuestionServlet?method=show&ques_id="+answer.getQues_id();
	}

	/**
	 * 
	 * @Description: 删除回答
	 * 传入参数：ans_id,ques_id
	 * @author: bee
	 * @CreateTime: 2018-5-9 下午6:38:13 
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("ans_id");
		int c_id = Integer.parseInt(id);
		String userid = (String) request.getSession().getAttribute("user_id");
		if(answerService.delete(c_id, userid)) {
			response.getWriter().write("success");
			//return "r:/QuestionServlet?method=show&ques_id="  + request.getParameter("ques_id");
		} else {//删除失败，因为不是当前评论作者
			request.setAttribute("msg", "你没有权限删除此评论!");
			response.getWriter().write("fail");
			//return "f:/QuestionServlet?method=show&ques_id="  + request.getParameter("ques_id");
		}
		
	}
	
	/**
	 * 
	 * @Description: 我的回答
	 * @author: bee
	 * @CreateTime: 2018-5-9 下午6:55:39 
	 */
	public void findMyAnswers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = (String) request.getSession().getAttribute("user_id");
		List<Answer> answers = answerService.loadMyAnswers(userid);
		request.setAttribute("foundList", answers);
		String json = Util.arrayToJson(answers);
		response.getWriter().write(json);
		//return "f:/ans_show.jsp";
	}
	
	/**
	 * 
	 * @Description: 通过关键词查找回答
	 * @author: bee
	 * @CreateTime: 2018-5-9 下午6:58:15 
	 */
	public void findCommentsByKey(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Answer> result = answerService.findAnswersBykey(keyword);
		String json = Util.arrayToJson(result);
		response.getWriter().write(json);
		request.setAttribute("foundList", result);
		//return "f:/show.jsp";
	}
}
