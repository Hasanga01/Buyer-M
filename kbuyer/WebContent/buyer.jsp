


<%@page import="model.Buyer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Projects Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/projects.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 

<h1>Gadgetbadget Buyer Management</h1>

<form id="formBuyer" name="formBuyer">

 First Name: <input id="buyerFname" name="buyerFname" type="text" class="form-control form-control-sm"> <br> 
 
 Last name:<input id="buyerLname" name="buyerLname" type="text" class="form-control form-control-sm"> <br> 
 
 Address:<input id="buyerAddress" name="buyerAddress" type="text" class="form-control form-control-sm"> <br> 		
				
				Phone:<input id="buyerPhone" name="buyerPhone" type="text" class="form-control form-control-sm"> <br> 		
				
				Nic:<input id="buyerNic" name="buyerNic" type="text" class="form-control form-control-sm"> <br> 		
								
				Birthday:<input id="buyerBirthday" name="buyerBirthday" type="date" class="form-control form-control-sm"> <br> 		
				
				Email:<input id="buyerEmail" name="buyerEmail" type="email" class="form-control form-control-sm"> <br> 		
				
				 Password:<input id="buyerPassword" name="buyerPassword" type="password" class="form-control form-control-sm"> <br> 		
				
				 <br>
 
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-secondary"> <input type="hidden" id="hidBuyerIDSave" name="hidBuyerIDSave" value="">

</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divProjectsGrid">
 <%
 Buyer buyerObj = new Buyer();
 out.print(buyerObj.readBuyer());
 %>
</div>
</div> </div> </div> 
</body>
</html>