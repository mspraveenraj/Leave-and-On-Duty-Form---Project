<%@page import="com.project.leaveod.beans.SearchByOdBean"%>
<%@page import="com.project.leaveod.beans.StudentBean"%>
<%@page import="com.project.leaveod.beans.OdAppliedDetailsBean"%>
<%@page import="java.util.*"%>
<%@page import="com.project.leaveod.dao.OdAppliedFacultyDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
    <% 
    	//String regnoFromSession="1413075";
    	//(String)session.getAttribute("Regno");
		String nameFromSession=(String)session.getAttribute("UserName");
		//if(nameFromSession==null)
		//	response.sendRedirect("/LeaveOndutyForm/form-login.html");
	String searchBy = (String)request.getParameter("searchby1");
    	OdAppliedFacultyDao obj= new OdAppliedFacultyDao();
    	//SearchByBean obj1=new SearchByBean();
    	LinkedList<OdAppliedDetailsBean> odFormList= obj.odApplied(searchBy);
    	LinkedList<StudentBean> studentList = obj.odAppliedStudents();
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

	<title>Onduty Form</title>

	<link rel="stylesheet" href="assets/demo.css">
	<link rel="stylesheet" href="assets/form-basic.css">
	

</head>

	<header>
		<h1>Onduty Form</h1>
        <a href="LogoutServlet">Logout</a>
    </header>
     <ul>
		<li><a href="LeaveAppliedFacultyList.jsp" >Leave Form</a></li>
        <li><a href="OdAppliedFacultyList.jsp" class="active">Onduty Form</a></li>
    </ul>

<body>
<form action="SearchByOdServlet" method="post">
<select name="searchby1">
<option value="pending" selected="selected">Pending</option>
<option value="accept">Accept</option>
<option value="reject">Reject</option>
</select>
<input type="submit" value="Go" >
</form>

<form action="SearchByOd2Servlet" method="post">
<select name="searchby2">
<option value="name" selected="selected">Name</option>
<option value="regno">Regno</option>
<option value="dateofapply">Date of Apply</option>
<option value="permissionfrom">Permission From</option>
<option value="permissionto">Permission To</option>
<option value="workassignedby">Work Assigned By</option>
</select>
<input type="text" name="searchvalue">
<input type="submit" value="Search" >
</form>

<form action="UpdateFromFacultyOdServlet" method="post">

<input type="submit" value="Submit All" >


<table border="1" width="100%" style="text-align:center;">
  <tr bgcolor= #4CAF50;>
    <th>Serial Number</th>
    <th>Name</th>
    <th>Regno</th>
    <th>Year</th>
    <th>Section</th>
    <th>Date Of Apply</th>
    <th>Permission From</th>
    <th>Permission To</th>
   	<th>Reason</th>
   	<th>Work Assigned By</th>
   	<th>Accept/Reject</th>
  </tr>
  
  <% Iterator<StudentBean> studentListIterator = studentList.iterator();
  	Iterator<OdAppliedDetailsBean> listIterator = odFormList.iterator();
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
 		OdAppliedDetailsBean  odFormBeanObj = listIterator.next();
 	   
  %>
  
    <td> <% out.print(odFormBeanObj.getDateOfApply()); %></td>
  	<td> <% out.print(odFormBeanObj.getDateOfLeaveFrom()); %></td>
 	<td> <% out.print(odFormBeanObj.getDateOfLeaveTo());%> </td>
 	<td> <% out.print(odFormBeanObj.getReason()); %>
 	<td> <% out.print(odFormBeanObj.getWorkAssignedBy()); %>
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