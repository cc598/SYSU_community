import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import cn.sysu.comm.entity.Answer;
import cn.sysu.comm.entity.Article;
import cn.sysu.comm.entity.Comment;
import cn.sysu.comm.entity.Question;
import cn.sysu.comm.entity.User;
import cn.sysu.comm.service.AnswerService;
import cn.sysu.comm.service.ArticleService;
import cn.sysu.comm.service.CommentService;
import cn.sysu.comm.service.QuestionService;
import cn.sysu.comm.service.UserService;
import cn.sysu.json.helper.Util;


public class test {
//	@Test
//	public void test() {
//		UserService userService = new UserService();
//		User user = userService.me("1");
//		String json = Util.beanToJson(user,"yyyy-MM-dd HH:mm:ss");
//		System.out.println(json);
//	}
	
	@Test
	public void test2() {
		ArticleService articleService = new ArticleService();
		List<Article> articles = articleService.findByKeywords("");
		JSONArray json = JSONArray.fromObject(articles);
		String json1 = Util.arrayToJson(articles);
		System.out.println(json1);
	}
	
	@Test
	public void test3() {
		CommentService commentService = new CommentService();
		List<Comment> articles = commentService.loadMyComments("1");
		String json1 = Util.arrayToJson(articles);
		System.out.println(json1);
	}
	
	@Test
	public void test4() {
		QuestionService questionService = new QuestionService();
		List<Question> articles = questionService.findByKeywords(" ");
		String json1 = Util.arrayToJson(articles);
		System.out.println(json1);
	}
	
	@Test
	public void test5() {
		AnswerService answerService = new AnswerService();
		List<Answer> articles = answerService.findAnswersBykey("");
		String json1 = Util.arrayToJson(articles);
		System.out.println(json1);
	}
}
