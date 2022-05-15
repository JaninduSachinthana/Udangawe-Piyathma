<%@page import="com.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<% Class.forName("com.mysql.cj.jdbc.Driver"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

	<title>ElectroGrid-Complaints</title>
	<link rel="stylesheet" href="View/css/bootstrap.css">
	<!-- <link rel="stylesheet" href="css/Complain.css"> -->
	<script src="Components/jquery.min.js"></script>
	<script src="Components/User.js"></script>
	

</head>
<body>
	<form id = "Complaint"  method='post' action='User.jsp'>
		<div class="container" style="background-color:white">
			<h2>Customer Register</h2>		    
			 <!--  <div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">User ID</label>
			  <input type="text" class="form-control form-control-lg" id="Userid" name="Userid" readonly>
			</div>  -->
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">User Name</label>
			  <input type="text" class="form-control form-control-lg" id="Username" name="Username" placeholder="">
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">User Password</label>
			  <input type="text" class="form-control form-control-lg" id="Userpwd" name="Userpwd" placeholder="">
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">Tel No :</label>
			  <input type="text" class="form-control form-control-lg" id="Userphn" name="Userphn" placeholder="">
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">User Email</label>
			  <input type="text" class="form-control form-control-lg" id="Useremail" name="Useremail" placeholder="">
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">User Address</label>
			  <input type="text" class="form-control form-control-lg" id="Useradrs" name="Useradrs" placeholder="">
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">User Account No</label>
			  <input type="text" class="form-control form-control-lg" id="Useracc" name="Useracc" placeholder="">
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlTextarea1" class="form-label">Date</label>
			  <input type="date" class="form-control" id="Date" name="Date" ></input>
			</div>
			
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			
			
		 	<input id="btnSave" name="btnSave" type="button" value="Send" class="button">
		 	
		 	
		 	<input type="hidden" id="hidcomIDSave" name="hidcomIDSave" value="">
		</div>

	</form>
	
		<%
		 //out.print(session.getAttribute("statusMsg")); 
		%>
		<br>
	<div id= "comGrid">
		<%
		UserService comObj = new UserService(); 
		out.print(comObj.readUser()); 
		%>
	</div>
</body>
</html>