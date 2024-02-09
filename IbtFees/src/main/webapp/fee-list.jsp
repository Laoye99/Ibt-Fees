<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Ibt Fees</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand">Ibt Fees</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">List</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Fees</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Fee</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>FSN</th>
						<th>ACSN</th>
						<th>FEE AMOUNT</th>
						<th>FEE TYPE ID</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="fee" items="${listFee}">

						<tr>
							<td><c:out value="${fee.fsn}" /></td>
							<td><c:out value="${fee.acsn}" /></td>
							<td><c:out value="${fee.fee_amt}" /></td>
							<td><c:out value="${fee.fee_type_id}" /></td>
							<td>
    <a href="edit?fsn=<c:out value='${fee.fsn}' />">Edit</a>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <a href="delete?fsn=<c:out value='${fee.fsn}'/> " onclick="return confirm('Confirm delete?')">Delete</a>
</td>
</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
	
<script>
function myFunction() {
  confirm("Confirm delete ?");
</script>
</body>
</html>