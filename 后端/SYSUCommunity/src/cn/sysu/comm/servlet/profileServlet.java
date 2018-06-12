package cn.sysu.comm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import cn.itcast.servlet.BaseServlet;
import cn.sysu.comm.entity.User;
import cn.sysu.comm.service.UserService;
/**
 * 
 * @Description:查看个人资料页面 
 * @CreateTime: 2018-5-8 下午12:43:45 
 * @author: bee
 */
public class profileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		UserService userService = new UserService();
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("user_id");
		if(userid != null) {
			User user = userService.findUser(userid);
			// 转换成JSON对象
			JSONObject map = JSONObject.fromObject(user);
			String json = map.toString();
			// 放置个人资料
			response.getWriter().write(json);
		} else {
			response.getWriter().write("请先登录！<br/>5s后跳转到登录页面...");
			response.setHeader("Refresh", "5;URL="+ request.getContextPath() +"/login.jsp");
		}
	}

}
