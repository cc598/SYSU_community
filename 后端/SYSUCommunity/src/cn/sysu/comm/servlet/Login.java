package cn.sysu.comm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sysu.comm.entity.Answer;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public Login() {
        // TODO Auto-generated constructor stub
    }

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set utf-8
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //get data
        String user_id = request.getParameter("user_id");
        String password = request.getParameter("password");
        
        //query service
        ServiceUser user;
        int status = user.login(user_id,password);
        
        //jump
        if (status) {		//success
            //set session
            HttpSession session = request.getSession();
            session.setAttribute("user_id", user_id);
            
            //goto homepage
        	response.sendRedirect("/home.html");
        }
        else {		//failed
        	request.setAttribute("errmsg", "username or password incorrect");
        	request.getRequestDispatcher("/login.html").forward(request, response);
        }
	}

}
