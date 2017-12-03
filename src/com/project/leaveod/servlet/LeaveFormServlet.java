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

import com.project.leaveod.dao.LeaveFormDao;

/**
 * Servlet implementation class LeaveFormServlet
 */
@WebServlet("/LeaveFormServlet")
public class LeaveFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LeaveFormDao leaveFormDaoObj;
    public LeaveFormServlet() {
        
    }

	
	public void init(ServletConfig config) throws ServletException {
		leaveFormDaoObj = new LeaveFormDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromDate=request.getParameter("fromdate");
		String toDate=request.getParameter("todate");
		String reason=request.getParameter("reason");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession(false);
		//out.println("<h1>"+fromDate+"<br/>"+toDate+"</h1>");
		
		if(session!=null)
		{
			String getRegno=(String) session.getAttribute("Regno");
			if(leaveFormDaoObj.applyLeave(fromDate,toDate,reason,getRegno))
			{
				out.println("<html><body><script> alert(' Leave Applied Successfully !!!'); location.href = '/LeaveOndutyForm/LeaveForm.jsp'; </script></body></html>");
				//response.sendRedirect("/LeaveOdForm/StudentHomePage.jsp");
				//out.println("<h1> Logged in Successfully !!");location.href = '/LeaveOdForm/LeaveForm.jsp';
			}
			else
			{
				out.println("<html><body><script> alert(' Wrong Username or Password !!!');</script></body></html>");
			}
		}
		else
		{
			response.sendRedirect("/LeaveOndutyForm/form-login.html");
		}
	}
}
