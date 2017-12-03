package com.project.leaveod.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.leaveod.dao.LoginDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginDao loginDaoObj;
    public LoginServlet() {
        
    }

	public void init(ServletConfig config) throws ServletException {
		loginDaoObj = new LoginDao();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String getRegno = request.getParameter("regno");
		String getPass =request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		if(getRegno.length()==0)
			out.println("<html><body><script> alert(' Please Enter Regno. !!!'); </script></body></html>");
		else if(getPass.length()==0)
			out.println("<html><body><script> alert(' Please Enter Password !!!');</script></body></html>");
		
		else if(loginDaoObj.verifyUser(getRegno,getPass))
		{
			HttpSession session=request.getSession();
			session.setAttribute("Regno",getRegno);
			session.setAttribute("Name",loginDaoObj.getStudentName());
			//request.setAttribute("regno", getRegno);
			response.sendRedirect("/LeaveOndutyForm/LeaveForm.jsp");
			//RequestDispatcher rd=request.getRequestDispatcher("/LeaveOdForm/StudentHomePage.jsp");
			//rd.forward(request,response);
			//out.println("<h1> Logged in Successfully !!");
		}
		else
		{
			out.println("<html><body><script> alert(' Wrong Username or Password !!!');</script></body></html>");
		}
	}

}
