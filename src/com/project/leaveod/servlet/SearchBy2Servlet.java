package com.project.leaveod.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchBy2Servlet
 */
@WebServlet("/SearchBy2Servlet")
public class SearchBy2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SearchBy2Servlet() {
       
    }

	public void init(ServletConfig config) throws ServletException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String searchBy1=request.getParameter("searchby1");
		String searchBy2=request.getParameter("searchby2");
		String searchValue=request.getParameter("searchvalue");
		HttpSession session=request.getSession(false);
		if(session!=null)
		{
		//request.setAttribute("searchby1",searchBy1);
		request.setAttribute("searchby2",searchBy2);
		request.setAttribute("searchvalue",searchValue);
		RequestDispatcher rd= request.getRequestDispatcher("SearchResult2.jsp");
		rd.forward(request,response);
		}		
		else
		{
			response.sendRedirect("/LeaveOndutyForm/form-login.html");
		}
	}
	
}