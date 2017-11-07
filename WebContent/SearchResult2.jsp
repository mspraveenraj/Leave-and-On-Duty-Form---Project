<%@page import="com.project.leaveod.beans.StudentBean"%>
<%@page import="com.project.leaveod.beans.LeaveFormBean"%>
<%@page import="java.util.*"%>
<%@page import="com.project.leaveod.dao.LeaveAppliedFacultyDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
    <% 
    	//String regnoFromSession="1413075";
    	//(String)session.getAttribute("Regno");
	//String nameFromSession=(String)session.getAttribute("UserName");
		//if(nameFromSession==null)
		//	response.sendRedirect("/LeaveOndutyForm/form-login.html");
	//String searchBy1 = (String)request.getParameter("searchby1");
	String searchBy2 = (String)request.getParameter("searchby2");
	String searchValue = (String)request.getParameter("searchvalue");
    	LeaveAppliedFacultyDao obj= new LeaveAppliedFacultyDao();
    	
    	LinkedList<LeaveFormBean> leaveFormList= obj.leaveApplied("*");
    	LinkedList<StudentBean> studentList = obj.leaveAppliedStudents();
    	//LinkedList<Integer> leaveFormId = obj.getLeaveFormId();
    	
    	LinkedList<LeaveFormBean> leaveFormListNew =new LinkedList<LeaveFormBean>();
    	LinkedList<StudentBean> studentListNew = new LinkedList<StudentBean>();
    	LinkedList<Integer> leaveFormIdNew = new LinkedList<Integer>();
    	
    	Iterator<StudentBean> studentListIterator = studentList.iterator();
      	Iterator<LeaveFormBean> listIterator = leaveFormList.iterator();
      	//Iterator<Integer> leaveFormIdIterator = leaveFormId.iterator();
      	//int i=1;
      	while(studentListIterator.hasNext() && listIterator.hasNext()) //&& leaveFormIdIterator.hasNext())
     	{
    
     		StudentBean studentBeanObj = studentListIterator.next();
     		LeaveFormBean  leaveFormBeanObj = listIterator.next();
     		//Integer leaveFormIdObj = leaveFormIdIterator.next();
     		if(searchBy2.equals("name"))
     		{
     			if(studentBeanObj.getName().equalsIgnoreCase(searchValue))
     			{
     				studentListNew.add(studentBeanObj);
     				leaveFormListNew.add(leaveFormBeanObj);
     				//leaveFormIdNew.add(leaveFormIdObj);
     			}
     		}
     		else if(searchBy2.equals("regno"))
     		{
     			if(studentBeanObj.getRegno().equalsIgnoreCase(searchValue))
     			{
     				studentListNew.add(studentBeanObj);
     				leaveFormListNew.add(leaveFormBeanObj);
     			//	leaveFormIdNew.add(leaveFormIdObj);
     			}
     		}
     		else if(searchBy2.equals("dateofapply"))
     		{
     			if(leaveFormBeanObj.getDateOfApply().equalsIgnoreCase(searchValue))
     			{
     				studentListNew.add(studentBeanObj);
     				leaveFormListNew.add(leaveFormBeanObj);
     			//	leaveFormIdNew.add(leaveFormIdObj);
     			}
     		}
     		else if(searchBy2.equals("leavefrom"))
     		{
     			if(leaveFormBeanObj.getDateOfLeaveFrom().equalsIgnoreCase(searchValue))
     			{
     				studentListNew.add(studentBeanObj);
     				leaveFormListNew.add(leaveFormBeanObj);
     			//	leaveFormIdNew.add(leaveFormIdObj);
     			}
     		}
     		else if(searchBy2.equals("leaveto"))
     		{
     			if(leaveFormBeanObj.getDateOfLeaveTo().equalsIgnoreCase(searchValue))
     			{
     				studentListNew.add(studentBeanObj);
     				leaveFormListNew.add(leaveFormBeanObj);
     			//	leaveFormIdNew.add(leaveFormIdObj);
     			}
     		}
     		//LeaveAppliedFacultyDao laf =new LeaveAppliedFacultyDao();
     		//laf.setLeaveFormId(leaveFormIdNew);
     	}
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
<option value="name">Name</option>
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
  
  <% Iterator<StudentBean> studentListIteratorNew = studentListNew.iterator();
  	Iterator<LeaveFormBean> listIteratorNew = leaveFormListNew.iterator();
  	int i=1;
  	while(studentListIteratorNew.hasNext() && listIteratorNew.hasNext())
 	{
 		StudentBean studentBeanObj = studentListIteratorNew.next();
  %>
  
  <tr>
  	<td> <% out.print(i++); %>
    <td> <% out.print(studentBeanObj.getName()); %></td>
  	<td> <% out.print(studentBeanObj.getRegno()); %></td>
 	<td> <% out.print(studentBeanObj.getYear()); %> </td>
 	<td> <% out.print(studentBeanObj.getSection()); %>
  
  <% 
 		LeaveFormBean  leaveFormBeanObj = listIteratorNew.next();
 	   
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