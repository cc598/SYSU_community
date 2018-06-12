package cn.sysu.comm.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.sysu.comm.entity.Article;
import cn.sysu.comm.service.ArticleService;
import cn.sysu.comm.service.UserService;
import cn.sysu.json.helper.Util;

/**
 * 
 * @Description: ArticleServlet
 * html请求要加上一个参数 method=xxx(调用的方法名称)
 * 对于处理关于Article的所有请求
 * 1. 添加文章
 * 2. 更新文章
 * 3. 删除文章
 * 4. 查看文章
 * 5. 查看该文章下所有评论
 * 6. 查找文章（通过关键词）
 * @CreateTime: 2018-5-8 下午4:42:42 
 * @author: bee
 */

public class ArticleServlet extends BaseServlet {
	
	private ArticleService articleService = new ArticleService();

	/**
	 * 
	 * @Description: 
	 * 添加文章
	 * 需要html传来参数：Userid（在session中）	文章内容  文章标题 分类 
	 * @author: bee
	 * @CreateTime: 2018-5-8 下午4:48:22 
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取userid
		String userid = (String) request.getSession().getAttribute("user_id");
		// 获取文章内容 标题 分类
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		String classic = request.getParameter("classification");
		
		/*
		 * 创造Article对象
		 * 补全创建时间，上次更新时间
		 * id由数据库自增	
		 */
		Article article = new Article();
		article.setClassification(classic);
		article.setContent(content);
		article.setTitle(title);
		article.setAuthorId(userid);
		
		/**
		 * 其实上面这段可以用toBeans函数包装
		 * 前提是给的参数名称和Article类的属性名相同
		 */
		// Article article = CommonUtils.toBean(request.getParameterMap(), Article.class);
		
		Timestamp releaseTime = new Timestamp(new java.util.Date().getTime());
		article.setReleaseTime(releaseTime);
		article.setLastChangeTime(releaseTime);
		
		// 调用service函数
		articleService.add(article);
		// 重定向到该文章页面
		request.setAttribute("art_id", String.valueOf(articleService.findLastInsertArticleId()));
		 show(request, response);
		
	}
	/**
	 * 
	 * @Description: 
	 * 更新文章
	 * 需要参数：文章id，标题，内容，分类
	 * @author: bee
	 * @CreateTime: 2018-5-8 下午5:12:55 
	 */
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Article article = CommonUtils.toBean(request.getParameterMap(), Article.class);
		// 设置上次更新时间
		Timestamp lastChangeTime = new Timestamp(new java.util.Date().getTime());
		article.setLastChangeTime(lastChangeTime);
		articleService.update(article);
		Article updated = articleService.findArticle(article.getArt_id());
		request.getSession().setAttribute("art", updated);
		String json = Util.beanToJson(updated,"yyyy-MM-dd HH:mm:ss");
		response.getWriter().write(json);
		// 重定向到该文章页面
		//return "r:/articleJsps/article.jsp";
	}
	/**
	 * 
	 * @Description: 删除文章
	 * 传进来的参数为articleid
	 * @author: bee
	 * @CreateTime: 2018-5-8 下午5:23:27 
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("art_id");
		int art_id = Integer.valueOf(id);
		if(articleService.delete(art_id, (String) request.getSession().getAttribute("user_id"))){
			// 重定向到  [哪个页面？]
			response.getWriter().write("success");
		} else{
			request.setAttribute("msg", "您没有权限删除此文章！");
			request.setAttribute("art_id", id);
			// 返回到文章页面
			response.getWriter().write("fail");
			show(request, response);
		}

		
	}
	
	/**
	 * 
	 * @Description: 查看某一文章
	 * 传递参数：文章id
	 * @author: bee
	 * @CreateTime: 2018-5-8 下午5:28:06 
	 */
	public void show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到文章的id
		String id = request.getParameter("art_id");
		if(id == null) {
			// servlet内部调用
			id = (String) request.getAttribute("art_id");
		}
		int art_id = Integer.parseInt(id);
		
		// 获取到该文章
		Article article = articleService.findArticle(art_id);
		if(article != null) {
			// 传递JSON对象
			/*
			 * 未完成
			 */
			String json = Util.beanToJson(article,"yyyy-MM-dd HH:mm:ss");
			response.getWriter().write(json);
			//request.setAttribute("art", article);
		} else {
			String msg = "该文章不存在！";
			request.setAttribute("msg", msg);
			response.getWriter().write("fail");
//			return "f:/welcome.jsp";
		}
//		return "f:/articleJsps/article.jsp";
	}
	
	/**
	 * 
	 * @Description: 通过关键词查找文章
	 * 传入参数为关键词
	 * @author: bee
	 * @CreateTime: 2018-5-8 下午7:05:13 
	 */
	public void findByKey(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String key = request.getParameter("keyword");
		List<Article> result = articleService.findByKeywords(key);
		request.setAttribute("foundList", result);
		String json = Util.arrayToJson(result);
		response.getWriter().write(json);
		// 重定向到  [哪个页面？]
		//return "f:/articleJsps/all.jsp";
	}
	
	public void getArticles(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Ssize = request.getParameter("size");
		int size = 0;
		if(Ssize == null) {
			// 默认值
			size = 10;
		} else {
			size = Integer.valueOf(Ssize);
		}
		
		List<Article> articles = articleService.getArticlesWithPages(size);
		String json = Util.arrayToJson(articles);
		response.getWriter().write(json);
		
		// 重定向到  [哪个页面？]
		//return "f:/articleJsps/all.jsp";
	}

	
}
