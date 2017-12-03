package com.project.leaveod.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.project.leaveod.dao.UpdateFromFacultyOdDao;

/**
 * Servlet implementation class UpdateFromFaculty
 */
@WebServlet("/UpdateFromFacultyOdServlet")
public class UpdateFromFacultyOdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UpdateFromFacultyOdDao updateFromFacultyDaoObj;
    public UpdateFromFacultyOdServlet() {
        
    }

	public void init(ServletConfig config) throws ServletException {
		updateFromFacultyDaoObj = new UpdateFromFacultyOdDao();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String getFacultySelection[] = request.getParameterValues("facultyselection");
		
		PrintWriter out = response.getWriter();
		
		if(getFacultySelection==null)
			out.println("<html><body><script> alert(' Please Select Any Option !!!'); </script></body></html>");
		
		else if(updateFromFacultyDaoObj.updateOdApplied(getFacultySelection))
		{
			
			//session.setAttribute("Name",facultyLoginDaoObj.getStudentName());
			//request.setAttribute("regno", getRegno);
			//response.sendRedirect("/LeaveOndutyForm/LeaveAppliedFacultyList.jsp");
			//RequestDispatcher rd=request.getRequestDispatcher("/LeaveOdForm/StudentHomePage.jsp");
			//rd.forward(request,response);
			out.println("<html><body><script> alert(' Updated Successfully !!!'); </script></body></html>");
		}
		else
		{
			out.println("<html><body><script> alert(' Wrong Username or Password !!!');</script></body></html>");
		}
	}

}
