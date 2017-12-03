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

import com.project.leaveod.dao.OdFormDao;

/**
 * Servlet implementation class OdFormServlet
 */
@WebServlet("/OdFormServlet")
public class OdFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OdFormDao odFormDaoObj;
    public OdFormServlet() {
       
    }
    
	public void init(ServletConfig config) throws ServletException {
		odFormDaoObj = new OdFormDao();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromDate=request.getParameter("permissionfrom");
		String toDate=request.getParameter("permissionto");
		String reason=request.getParameter("reason");
		String workAssignedBy=request.getParameter("workby");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession(false);
		//out.println("<h1>"+fromDate+"<br/>"+toDate+"</h1>");
		
		if(session!=null)
		{
			String getRegno=(String) session.getAttribute("Regno");
			
			if(odFormDaoObj.applyOnduty(fromDate,toDate,reason,workAssignedBy,getRegno))
			{
				out.println("<html><body><script> alert(' Onduty Applied Successfully !!!'); location.href = '/LeaveOndutyForm/OdForm.jsp';</script></body></html>");
				//response.sendRedirect("/LeaveOdForm/StudentHomePage.jsp");
				//response.sendRedirect("/LeaveOdForm/StudentHomePage.html");
				//out.println("<h1> Logged in Successfully !!");
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