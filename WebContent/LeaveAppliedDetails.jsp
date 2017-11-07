<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.project.leaveod.beans.LeaveAppliedDetailsBean"%>
<%@page import="java.util.*"%>
<%@page import="com.project.leaveod.dao.LeaveAppliedDetailsDao"%>
    <% 
    	String regnoFromSession=(String)session.getAttribute("Regno");
    	
		if(regnoFromSession==null)
			response.sendRedirect("/LeaveOndutyForm/form-login.html");
		LeaveAppliedDetailsDao obj= new LeaveAppliedDetailsDao();
    	
    	LinkedList<LeaveAppliedDetailsBean> leaveFormList=obj.leaveAppliedDetails(regnoFromSession);
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Applied Details</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="jquery-ui.css">
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="assets/demo.css">
	<link rel="stylesheet" href="assets/form-basic.css">

</head>

<header>
		<h1>Leave Form</h1>
        <a href="LogoutServlet">Logout</a>
    </header>

    <ul>
    
        <li><a href="LeaveForm.jsp">Leave Form</a></li>
        <li><a href="OdForm.jsp">Onduty Form</a></li>
        <li><a href="LeaveAppliedDetails.jsp" class="active">Leave Details</a></li>
        <li><a href="OdAppliedDetails.jsp">Onduty Details</a></li>
    </ul>
    
<body>

<table border="1" width="100%" style="text-align:center;">
  <tr bgcolor= #4CAF50;>
    <th>Serial Number</th>
    <th>Date Of Apply</th>
    <th>Date of Leave From</th>
    <th>Date of Leave To</th>
   	<th>Reason</th>
   	<th>Accept/Reject</th>
  </tr>
  <tr>
  <%
 	 Iterator<LeaveAppliedDetailsBean> listIterator = leaveFormList.iterator();
  	int i=1;
  	while(listIterator.hasNext())
 	{
	
			LeaveAppliedDetailsBean  leaveFormBeanObj = listIterator.next();
 	   
  %>
  	<td> <% out.print(i++); %> </td>
    <td> <% out.print(leaveFormBeanObj.getDateOfApply()); %></td>
  	<td> <% out.print(leaveFormBeanObj.getDateOfLeaveFrom()); %></td>
 	<td> <% out.print(leaveFormBeanObj.getDateOfLeaveTo());%> </td>
 	<td> <% out.print(leaveFormBeanObj.getReason()); %></td>
 	<td ><% out.print(leaveFormBeanObj.getStatus()); %></td>
  </tr>
  <% } %>
  
</table>
</body>
</html>