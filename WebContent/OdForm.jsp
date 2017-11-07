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

<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="./jquery.datetimepicker.css"/>

<link rel="stylesheet" href="jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
  
  <script>
  $(document).ready(function() {
    $(".datepicker").datepicker();
  });
  
  function required()
  {
	var from = document.form2.permissionfrom;
	var to = document.form2.permissionto;
	
	var reason = document.form2.reason;
	var workby = document.form2.workby;
	if(from.value == "" )
	{
		alert("Please Select Permission From");
		from.focus();
		return false;
	}
	else if( to.value == "" )
	{
		alert("Please Select Permission To");
		to.focus();
		return false;
	}
	
	else if(reason.value == "")
	{
		alert("Please Enter Valid Reason");
		reason.focus();
		return false;
	}
	else if(workby.value == "")
	{
		alert("Please Enter Work Assigned By");
		workby.focus();
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

	<title>Onduty Form</title>

	<link rel="stylesheet" href="assets/demo.css">
	<link rel="stylesheet" href="assets/form-basic.css">
	
</head>


	<header>
		<h1>OnDuty Form</h1>
        <a href="LogoutServlet">Logout</a>
    </header>
	

    <ul>
		<li><a href="LeaveForm.jsp">Leave Form</a></li>
        <li><a href="ODForm.jsp" class="active">Onduty Form</a></li>
        <li><a href="LeaveAppliedDetails.jsp">Leave Details</a></li>
        <li><a href="OdAppliedDetails.jsp">Onduty Details</a></li>
        
    </ul>
	
	<body>
	<div class="main-content">

        <!-- You only need this form and the form-basic.css -->

        <form class="form-basic" method="post" action="OdFormServlet" name="form2" onsubmit="return required();">

            <div class="form-title-row">
                <h1>OnDuty Form</h1>
            </div>

            <div class="form-row">
                <label>
                    <span>Name</span>
                    <input type="text" name="name" placeholder = <% out.print(nameFromSession); %>>
                </label>
            </div>

            <div class="form-row">
                <label>
                    <span>Regno</span>
                    <input type="text" name="regno" placeholder = <% out.print(regnoFromSession); %>>
                </label>
            </div>
			
			<div class="form-row">
                <label>
                    <span>Permission From</span>
                    <input type="text" value="" class="datetimepicker" name="permissionfrom"><br><br>
                   <!--   <input type="fromdate" name="fromdate" class="datepicker"> -->
                </label>
            </div>
			
			<div class="form-row">
                <label>
                    <span>Permission To</span>
                    <input type="text" value="" class="datetimepicker" name="permissionto"><br><br>
                <!--     <input type="todate" name="todate" class="datepicker">   -->
                </label>
            </div>
			

            <div class="form-row">
                <label>
                    <span>Reason</span>
                    <textarea name="reason"></textarea>
                </label>
            </div>
			
			<div class="form-row">
                <label>
                    <span>Work Assigned By</span>
                    <input type="text" name="workby">
                </label>
            </div>

            <div class="form-row">
                <button type="submit">Submit Form</button>
            </div>

        </form>

    </div>

</body>


<script src="./jquery.js"></script>
<script src="build/jquery.datetimepicker.full.js"></script>
<script>

$.datetimepicker.setLocale('en');

$('#datetimepicker_format').datetimepicker({value:'2017/09/06 05:03', format: $("#datetimepicker_format_value").val()});
console.log($('#datetimepicker_format').datetimepicker('getValue'));

$("#datetimepicker_format_change").on("click", function(e){
	$("#datetimepicker_format").data('xdsoft_datetimepicker').setOptions({format: $("#datetimepicker_format_value").val()});
});
$("#datetimepicker_format_locale").on("change", function(e){
	$.datetimepicker.setLocale($(e.currentTarget).val());
});

$('.datetimepicker').datetimepicker({
dayOfWeekStart : 1,
lang:'en',
disabledDates:['1986/01/08','1986/01/09','1986/01/10'],
startDate:	'2017/09/06'
});
$('.datetimepicker').datetimepicker({value:'2017/09/15 05:03',step:10});

$('.some_class').datetimepicker();

</script>
</html>
