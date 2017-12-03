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

import com.project.leaveod.dao.FacultyLoginDao;

/**
 * Servlet implementation class FacultyLoginServlet
 */
@WebServlet("/FacultyLoginServlet")
public class FacultyLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FacultyLoginDao facultyLoginDaoObj;
    public FacultyLoginServlet() {
        
    }

	public void init(ServletConfig config) throws ServletException {
		facultyLoginDaoObj = new FacultyLoginDao();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String getUserName = request.getParameter("username");
		String getPass =request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		if(getUserName.length()==0)
			out.println("<html><body><script> alert(' Please Enter Regno. !!!'); </script></body></html>");
		else if(getPass.length()==0)
			out.println("<html><body><script> alert(' Please Enter Password !!!');</script></body></html>");
		
		else if(facultyLoginDaoObj.verifyUser(getUserName,getPass))
		{
			HttpSession session=request.getSession();
			session.setAttribute("Username",getUserName);
		
			//session.setAttribute("Name",facultyLoginDaoObj.getStudentName());
			//request.setAttribute("regno", getRegno);
			response.sendRedirect("/LeaveOndutyForm/LeaveAppliedFacultyList.jsp");
			//RequestDispatcher rd=request.getRequestDispatcher("/LeaveOdForm/StudentHomePage.jsp");
			//rd.forward(request,response);
			//out.println("<h1> Logged in Successfully !!</h1>");
		}
		else
		{
			out.println("<html><body><script> alert(' Wrong Username or Password !!!');</script></body></html>");
		}
	}

}
