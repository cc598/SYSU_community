package cn.sysu.comm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class isLogin
 */
public class isLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public isLogin() {
        // TODO Auto-generated constructor stub
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set utf-8
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        //get session
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        
        //get uri
        String requestUri = request.getRequestURI();
        
        if (session.isNew()) {		//not logined
        	request.setAttribute("errmsg", "not logined");
        	request.getRequestDispatcher(requestUri).forward(request, response);
        }
        else {		//logined
        	String userid = session.getAttribute("user_id");
        	request.setAttribute("msg", user_id);
        	request.getRequestDispatcher(requestUri).forward(request, response);
        }
        
	}

}
