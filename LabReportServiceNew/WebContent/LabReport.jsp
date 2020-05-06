<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="model.LabReport"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lab Report Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.0.min.js"></script>
<script src="Components/Hospitals.js"></script>
</head>
<body>
	<h1>Lab Report Management</h1>



	<form id="formHospital" name="formHospital" method="post" action="Hospital.jsp">
		Lab Report Number : <input id="lReportNum" name="lReportNum" type="text"
			class="form-control form-control-sm"> <br> Lab Report Name : <input
			id="lReportName" name="lReportName" type="text"
			class="form-control form-control-sm"> <br> Lab Report Details : <input
			id="lReportDet" name="lReportDet" type="text" class="form-control form-control-sm">
		
			 <input
			id="btnSave" name="btnSave" type="button" value="Save"
			class="btn btn-primary"> <input type="hidden"
			id="hidHospitalIDSave" name="hidHospitalIDSave" value="">
	</form>

	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divUsersGrid">
		<%
			LabReport UserObj = new LabReport();
				out.print(UserObj.readLabReports());
		%>
	</div>

</body>
</html>