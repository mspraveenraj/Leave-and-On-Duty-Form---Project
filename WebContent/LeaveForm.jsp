<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
    	String regnoFromSession=(String)session.getAttribute("Regno");
    	String nameFromSession=(String)session.getAttribute("Name");
		if(regnoFromSession==null)
			response.sendRedirect("/LeaveOndutyForm/form-login.html");
   %>
<!DOCTYPE html>
<html>

<head>

	<link rel="stylesheet" href="jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
  
  <script>
  $(document).ready(function() {
    $(".datepicker").datepicker();
  });
  
  function required()
  {
	var from = document.form1.fromdate;
	var to = document.form1.todate;
	var reason = document.form1.reason;
	if(from.value == "" )
	{
		alert("Please Select Leave From Date");
		from.focus();
		return false;
	}
	else if( to.value == "" )
	{
		alert("Please Select Leave To Date");
		to.focus();
		return false;
	}
	else if(reason.value == "")
	{
		alert("Please Enter Reason");
		reason.focus();
		return false;
	}
	else
	{
		return true;
	}
  }

  </script>
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
    
        <li><a href="LeaveForm.jsp" class="active">Leave Form</a></li>
        <li><a href="OdForm.jsp">Onduty Form</a></li>
        <li><a href="LeaveAppliedDetails.jsp">Leave Details</a></li>
        <li><a href="OdAppliedDetails.jsp">Onduty Details</a></li>
    </ul>
    
	<body>
	<div class="main-content">

        <!-- You only need this form and the form-basic.css -->

        <form class="form-basic" method="post" action="LeaveFormServlet" name="form1" onsubmit="return required();">

            <div class="form-title-row">
                <h1>Leave Form</h1>
            </div>

            <div class="form-row">
                <label>
                    <span>Name</span>
                    <!--   <input type="text" name="name" placeholder = -->
                   <span> <% out.print(nameFromSession); %> </span>
                </label>
            </div>

            <div class="form-row">
                <label>
                    <span>Regno</span>
                 <!--   <input type="text" name="regno" placeholder= -->
                  <span>    <% out.print(regnoFromSession); %> </span>
                </label>
            </div>
			
			<div class="form-row">
                <label>
                    <span>Leave From Date</span>
                    <input type="fromdate" name="fromdate" class="datepicker">
                </label>
            </div>
			
			<div class="form-row">
                <label>
                    <span>Leave To Date</span>
                    <input type="todate" name="todate" class="datepicker">
                </label>
            </div>

            <div class="form-row">
                <label>
                    <span>Reason</span>
                    <textarea name="reason"></textarea>
                </label>
            </div>


            <div class="form-row">
                <button type="submit">Submit Form</button>
            </div>

        </form>

    </div>

</body>

</html>
