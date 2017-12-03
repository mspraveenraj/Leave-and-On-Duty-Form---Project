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

import com.project.leaveod.dao.LeaveAppliedFacultyDao;

/**
 * Servlet implementation class SearchByServlet
 */
@WebServlet("/SearchByServlet")
public class SearchByServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LeaveAppliedFacultyDao leaveAppliedFacultyObj;
	
	public SearchByServlet() {
        
    }
	
	//private String searchBy;
	
	//LinkedList<LeaveFormBean> leaveFormList;
	//LinkedList<StudentBean> studentList;
	
	public void init(ServletConfig config) throws ServletException {
		//leaveAppliedFacultyObj = new LeaveAppliedFacultyDao();
		//leaveFormList = new LinkedList<>();
		//studentList = new LinkedList<>();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchBy1=request.getParameter("searchby1");
		
		HttpSession session=request.getSession(false);
		if(session!=null)
		{
		request.setAttribute("searchby1",searchBy1);
		
		RequestDispatcher rd= request.getRequestDispatcher("SearchResult.jsp");
		rd.forward(request,response);
		}		
		else
		{
			response.sendRedirect("/LeaveOndutyForm/form-login.html");
		}
	}
	
}
