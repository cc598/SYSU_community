package cn.sysu.comm.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
/**
 * 
 * @Description: 后台管理员的servlet
 * @CreateTime: 2018-5-10 下午10:32:38 
 * @author: bee
 */
public class Admin extends BaseServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	}

}
