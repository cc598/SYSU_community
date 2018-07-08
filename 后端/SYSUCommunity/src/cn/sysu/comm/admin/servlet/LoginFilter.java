package cn.sysu.comm.admin.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
author: kingchiu
 */
/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String userid = (String) req.getSession().getAttribute("user_id");
		String url = req.getRequestURI();
		if(userid != null || url.contains("ogin") || url.contains("GetCaptcha")) {
			chain.doFilter(request, response);
		} else {
			req.setAttribute("msg", "请先登录！");
			req.getRequestDispatcher("/login.jsp").forward(req, response);
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
