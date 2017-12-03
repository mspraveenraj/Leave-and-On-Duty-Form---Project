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
 * Servlet implementation class SearchByOd2Servlet
 */
@WebServlet("/SearchByOd2Servlet")
public class SearchByOd2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchByOd2Servlet() {
        super();
        
    }

	public void init(ServletConfig config) throws ServletException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchBy2=request.getParameter("searchby2");
		String searchValue=request.getParameter("searchvalue");
		HttpSession session=request.getSession(false);
		if(session!=null)
		{
		;
		request.setAttribute("searchby2",searchBy2);
		request.setAttribute("searchvalue",searchValue);
		RequestDispatcher rd= request.getRequestDispatcher("SearchOdResult2.jsp");
		rd.forward(request,response);
		}		
		else
		{
			response.sendRedirect("/LeaveOndutyForm/form-login.html");
		}
	}

}
