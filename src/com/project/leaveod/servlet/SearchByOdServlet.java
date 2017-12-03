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

import com.project.leaveod.dao.OdAppliedFacultyDao;

/**
 * Servlet implementation class SearchByOdServlet
 */
@WebServlet("/SearchByOdServlet")
public class SearchByOdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OdAppliedFacultyDao odAppliedFacultyObj;
    public SearchByOdServlet() {
        super();
      
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchBy1=request.getParameter("searchby1");
		
		HttpSession session=request.getSession(false);
		if(session!=null)
		{
		request.setAttribute("searchby1",searchBy1);
		
		RequestDispatcher rd= request.getRequestDispatcher("SearchOdResult.jsp");
		rd.forward(request,response);
		}		
		else
		{
			response.sendRedirect("/LeaveOndutyForm/form-login.html");
		}
	}
}
