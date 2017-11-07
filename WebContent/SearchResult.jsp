<%@page import="com.project.leaveod.beans.SearchByBean"%>
<%@page import="com.project.leaveod.beans.StudentBean"%>
<%@page import="com.project.leaveod.beans.LeaveFormBean"%>
<%@page import="java.util.*"%>
<%@page import="com.project.leaveod.dao.LeaveAppliedFacultyDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
    <% 
    	//String regnoFromSession="1413075";
    	//(String)session.getAttribute("Regno");
		String nameFromSession=(String)session.getAttribute("UserName");
		//if(nameFromSession==null)
		//	response.sendRedirect("/LeaveOndutyForm/form-login.html");
	String searchBy = (String)request.getParameter("searchby1");
    	LeaveAppliedFacultyDao obj= new LeaveAppliedFacultyDao();
    	//SearchByBean obj1=new SearchByBean();
    	LinkedList<LeaveFormBean> leaveFormList= obj.leaveApplied(searchBy);
    	LinkedList<StudentBean> studentList = obj.leaveAppliedStudents();
    	//LinkedList<Integer> dummy = obj.getLeaveFormId();
    	//SearchByBean sb=new SearchByBean();
    	//leaveFormList=(obj1.getLeaveFormList());
    	//studentList=(obj1.getStudentList());
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Leave Form</title>

	<link rel="stylesheet" href="assets/demo.css">
	<link rel="stylesheet" href="assets/form-basic.css">
	

</head>

	<header>
		<h1>Leave Form</h1>
        <a href="LogoutServlet">Logout</a>
    </header>
     <ul>
		<li><a href="LeaveAppliedFacultyList.jsp" class="active">Leave Form</a></li>
        <li><a href="OdAppliedFacultyList.jsp">Onduty Form</a></li>
    </ul>

<body>
<form action="SearchByServlet" method="post">
<select name="searchby1">
<option value="pending" selected="selected">Pending</option>
<option value="accept">Accept</option>
<option value="reject">Reject</option>
</select>
<input type="submit" value="Go" >
</form>

<form action="SearchBy2Servlet" method="post">
<select name="searchby2">
<option value="name" selected="selected">Name</option>
<option value="regno">Regno</option>
<option value="dateofapply">Date of Apply</option>
<option value="leavefrom">Leave from Date</option>
<option value="leaveto">Leave to Date</option>
</select>
<input type="text" name="searchvalue">
<input type="submit" value="Search" >
</form>

<form action="UpdateFromFacultyServlet" method="post">

<input type="submit" value="Submit All" >


<table border="1" width="100%" style="text-align:center;">
  <tr bgcolor= #4CAF50;>
    <th>Serial Number</th>
    <th>Name</th>
    <th>Regno</th>
    <th>Year</th>
    <th>Section</th>
    <th>Date Of Apply</th>
    <th>Date of Leave From</th>
    <th>Date of Leave To</th>
   	<th>Reason</th>
   	<th>Accept/Reject</th>
  </tr>
  
  <% Iterator<StudentBean> studentListIterator = studentList.iterator();
  	Iterator<LeaveFormBean> listIterator = leaveFormList.iterator();
  	int i=1;
  	while(studentListIterator.hasNext() && listIterator.hasNext())
 	{
 		StudentBean studentBeanObj = studentListIterator.next();
  %>
  
  <tr>
  	<td> <% out.print(i++); %>
    <td> <% out.print(studentBeanObj.getName()); %></td>
  	<td> <% out.print(studentBeanObj.getRegno()); %></td>
 	<td> <% out.print(studentBeanObj.getYear()); %> </td>
 	<td> <% out.print(studentBeanObj.getSection()); %>
  
  <% 
 		LeaveFormBean  leaveFormBeanObj = listIterator.next();
 	   
  %>
  
    <td> <% out.print(leaveFormBeanObj.getDateOfApply()); %></td>
  	<td> <% out.print(leaveFormBeanObj.getDateOfLeaveFrom()); %></td>
 	<td> <% out.print(leaveFormBeanObj.getDateOfLeaveTo());%> </td>
 	<td> <% out.print(leaveFormBeanObj.getReason()); %>
 	<td > 
 	<select name="facultyselection" style="width:100%;">
  <option value="pending">Pending</option>
  <option value="accept">Accept</option>
  <option value="reject">Reject</option>
</select>
</td>
  </tr>
  <% } %>
  
</table>
</form>
</body>
</html>