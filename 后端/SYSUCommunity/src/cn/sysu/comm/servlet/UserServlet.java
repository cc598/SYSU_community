package cn.sysu.comm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import cn.itcast.servlet.BaseServlet;
import cn.sysu.comm.entity.User;
import cn.sysu.comm.service.UserService;
import cn.sysu.json.helper.Util;

public class UserServlet extends BaseServlet {
	UserService userService = new UserService();
	/**
	 * 
	 * @Description: 注销功能 
	 * @author: bee
	 * @CreateTime: 2018-5-9 下午3:45:58 
	 */
	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = (String) request.getSession().getAttribute("user_id");
		if(userid == null) {
			return "r:/login.jsp";
		}
		request.getSession().invalidate();
		return "r:/login.jsp";
	}

	/**
	 * 
	 * @Description: 我的个人资料
	 * 包括 我的评论、我的文章、我的问题、我的回答 
	 * @author: bee
	 * @CreateTime: 2018-5-9 下午3:46:16 
	 */
	
	public void myAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("user_id");
		User user = userService.me(userid);
		String json = Util.beanToJson(user,"yyyy-MM-dd HH:mm:ss");
		response.getWriter().write(json);
		System.out.println(json);
//		request.setAttribute("user", user);
//		return "f:/myAll.jsp";
	}
	
}
