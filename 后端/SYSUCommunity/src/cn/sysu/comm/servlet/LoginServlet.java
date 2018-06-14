package cn.sysu.comm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.commons.CommonUtils;
import cn.sysu.comm.service.UserService;

/**
 * Servlet层
 * 主要是与请求、响应有关
 * 依赖service层，不操作数据，请求service层函数
 * 编码都使用utf-8以防乱码
 * @CreateTime: 2018-4-24 下午8:21:41 
 * @author: bee
 * @version V1.0
 */

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserService userService = new UserService();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
		String captcha = (String) request.getSession().getAttribute("captcha");
		String vCode = request.getParameter("captcha");
		if(vCode == null || vCode.trim().equals("") ||!vCode.equalsIgnoreCase(captcha)) {
			response.getWriter().write("captcha");
			return;
		}
		
		//调用service层Login
		boolean login = userService.login(username, password);
		if(!login){//登录失败
//			request.setAttribute("msg", "用户名或密码错误");//错误信息
//			request.getRequestDispatcher("/login.html").forward(request, response);
			
			response.getWriter().write("fail");
		} else {//登录成功，跳转到用户页面
			HttpSession session = request.getSession();
			session.setAttribute("user_id", username);
			Cookie cookie = new Cookie("user_id", username);
			Cookie cookie2 = new Cookie("password", password);
			cookie.setMaxAge(60*60*24);
			cookie2.setMaxAge(60*60*24);
			response.addCookie(cookie);
			response.addCookie(cookie2);
		//	response.sendRedirect(request.getContextPath()+"/user.jsp");
			
			response.getWriter().write("success");
		}
	}

}
