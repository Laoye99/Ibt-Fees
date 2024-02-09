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
				<a href="https://www.xadmin.net" class="navbar-brand"> Ibt Fees </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">List</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${fee != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${fee == null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${fee != null}">
            			Edit Fee
            		</c:if>
						<c:if test="${fee == null}">
            			Add New Fee
            		</c:if>
					</h2>
				</caption>

				<c:if test="${fee != null}">
					<input type="hidden" name="fsn" value="<c:out value='${fee.fsn}' />" />
				</c:if>
				
				<fieldset class="form-group">
					<label>ACSN</label> <input type="number"
						value="<c:out value='${fee.acsn}' />" class="form-control"
						name="acsn" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>FEE AMOUNT</label> <input type="number" step="any"
						value="<c:out value='${fee.fee_amt}' />" class="form-control"
						name="fee_amt" id="feeAmt" required="required">
				</fieldset>

<fieldset class="form-group">
    <label for="feeTypeId">CHOOSE A FEE TYPE ID</label> <br>
    <select name="fee_type_id" id="feeTypeId" style=" width: 250px;">
        <option  disabled selected>Select a Fee Type ID</option>
        <option value="B">B</option>
        <option value="V">V</option>
        <option value="F">F</option>
    </select>
</fieldset>



				<button type="submit" class="btn btn-success" >Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>