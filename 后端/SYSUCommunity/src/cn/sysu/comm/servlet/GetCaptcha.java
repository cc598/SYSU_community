package cn.sysu.comm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sysu.comm.vcode.Captcha;
import cn.sysu.comm.vcode.GifCaptcha;

public class GetCaptcha extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/gif");

		// 生成gif验证码图的长、宽、字符数
		Captcha captcha = new GifCaptcha(164, 42, 4);
		// 输出到响应中
		captcha.out(response.getOutputStream());
		// 在session中设置正确的验证码
		request.getSession().setAttribute("captcha", captcha.text().toLowerCase());
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
