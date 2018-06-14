package cn.sysu.comm.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.sysu.comm.entity.Article;
import cn.sysu.comm.entity.Question;
import cn.sysu.comm.service.QuestionService;
import cn.sysu.json.helper.Util;

/**
 * 
 * @Description: QuestionServlet
 * html请求要加上一个参数 method=xxx(调用的方法名称)
 * 对于处理关于Question的所有请求
 * 1. 添加问题
 * 2. 更新文章
 * 3. 删除文章
 * 4. 查看文章
 * 5. 查看该文章下所有回答
 * 6. 查找问题（通过关键词）
 * @CreateTime: 2018-5-9 下午4:46:04 
 * @author: bee
 */

public class QuestionServlet extends BaseServlet {

	private QuestionService questionService = new QuestionService();
	
	
	/**
	 * 
	 * @Description: 添加问题
	 * 传入参数：title, content, authorId(session中获取)
	 * 传入的参数名称都如上!
	 * @author: bee
	 * @CreateTime: 2018-5-9 下午4:48:54 
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Question question = CommonUtils.toBean(request.getParameterMap(), Question.class);
		/*
		 * 添加上authorId,releaseTime
		 */
		question.setAuthorId((String) request.getSession().getAttribute("user_id"));
		Timestamp releaseTime = new Timestamp(new java.util.Date().getTime());
		question.setReleaseTime(releaseTime);
		questionService.add(question);
		request.setAttribute("ques_id", String.valueOf(questionService.findLastInsertQuestionId()));
		 show(request, response);
	}


	/**
	 * 
	 * @Description: 问题页面
	 * 传入参数：ques_id
	 * @author: bee
	 * @throws IOException 
	 * @CreateTime: 2018-5-9 下午6:06:55 
	 */
	public void show(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 得到文章的id
				String id = request.getParameter("ques_id");
				if(id == null) {
					// servlet内部调用
					id = (String) request.getAttribute("ques_id");
				}
				int ques_id = Integer.parseInt(id);
				
				// 获取到该文章
				Question question = questionService.findQuestion(ques_id);
				if(question != null) {
					// 传递JSON对象
					/*
					 * 未完成
					 */
					String json = Util.beanToJson(question,"yyyy-MM-dd HH:mm:ss");
					response.getWriter().write(json);
					request.setAttribute("ques", question);
				} else {
					String msg = "该问题不存在！";
					request.setAttribute("msg", msg);
//					return "f:/welcome.jsp";
				}
//				return "f:/questionJsps/question.jsp";
	}
	
	/**
	 * 
	 * @Description: 更新问题
	 * 传入参数：title, content, question_id
	 * @author: bee
	 * @CreateTime: 2018-5-9 下午6:14:49 
	 */
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Question question = CommonUtils.toBean(request.getParameterMap(), Question.class);
		questionService.update(question);
		Question updated = questionService.findQuestion(question.getQuestion_id());
		String json = Util.beanToJson(updated,"yyyy-MM-dd HH:mm:ss");
		response.getWriter().write(json);
		// 获得该更新后的question对象
		request.getSession().setAttribute("question", updated);
		// 重定向到该问题页面
//		return "r:/questionJsps/qustion.jsp";
	}
	/**
	 * 
	 * @Description: 删除问题
	 * 传入参数：ques_id
	 * @author: bee
	 * @CreateTime: 2018-5-9 下午6:20:39 
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("ques_id");
		int ques_id = Integer.valueOf(id);
		if(questionService.delete(ques_id, (String) request.getSession().getAttribute("user_id"))) {//删除成功
			// 重定向到  [哪个页面？]
		//	return "r:/user.jsp";
			response.getWriter().write("success");
		} else {
			// 重定向到question页面
			request.setAttribute("msg", "您没有权限删除此问题！");
			request.setAttribute("ques_id",	id);
			// show(request, response);
			response.getWriter().write("fail");
		}
		
		
	}
	
	/**
	 * 
	 * @Description: 通过关键词查找问题
	 * 传入参数：keyword
	 * @author: bee
	 * @CreateTime: 2018-5-9 下午6:22:21 
	 */
	public void findByKey(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String key = request.getParameter("keyword");
		List<Question> result = questionService.findByKeywords(key);
		request.setAttribute("foundList", result);
		String json = Util.arrayToJson(result);
		response.getWriter().write(json);
		// 重定向到  [哪个页面？]
//		return "f:/questionJsps/all.jsp";
	}

	public void getQuestions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Ssize = request.getParameter("size");
		int size = 0;
		if(Ssize == null) {
			// 默认值
			size = 10;
		} else {
			size = Integer.valueOf(Ssize);
		}
		
		List<Question> questions = questionService.getQuestionsWithPages(size);
		String json = Util.arrayToJson(questions);
		response.getWriter().write(json);
		
		// 重定向到  [哪个页面？]
		//return "f:/articleJsps/all.jsp";
	}
}
