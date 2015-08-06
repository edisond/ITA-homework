<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Register</title>
<link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	<%
		Map<String, String> errorMap = (Map<String, String>) request.getAttribute("errorMap");
	%>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<form action="/O2O_Seller/RegisterServlet" method="post" class="center-block well"
					style="max-width: 500px; margin-top: 50px" enctype="multipart/form-data">
					<h1 class="text-center">Seller Register</h1>
					<div class="form-group">
						<label>Name</label>
						<input type="text" name="name" class="form-control" required
							value="<%=request.getAttribute("name") == null ? "" : request.getAttribute("name")%>" />
						<span class="text-danger">
							<%
								if (errorMap != null && errorMap.containsKey("name")) {
									out.print(errorMap.get("name"));
								}
							%>
						</span>
					</div>
					<div class="form-group">
						<label>Password</label>
						<input type="password" name="psw" class="form-control" required />
						<span class="text-danger">
							<%
								if (errorMap != null && errorMap.containsKey("psw")) {
									out.print(errorMap.get("psw"));
								}
							%>
						</span>
					</div>
					<div class="form-group">
						<label>Confirm Password</label>
						<input type="password" name="confirmPsw" class="form-control" required />
						<span class="text-danger">
							<%
								if (errorMap != null && errorMap.containsKey("confirmPsw")) {
									out.print(errorMap.get("confirmPsw"));
								}
							%>
						</span>
					</div>
					<div class="form-group">
						<label>Tel</label>
						<input type="tel" name="tel" class="form-control" required
							value="<%=request.getAttribute("tel") == null ? "" : request.getAttribute("tel")%>" />
						<span class="text-danger">
							<%
								if (errorMap != null && errorMap.containsKey("tel")) {
									out.print(errorMap.get("tel"));
								}
							%>
						</span>
					</div>
					<div class="form-group">
						<label>Id Card</label>
						<input type="text" name="idCard" class="form-control" required
							value="<%=request.getAttribute("idCard") == null ? "" : request.getAttribute("idCard")%>" />
						<span class="text-danger">
							<%
								if (errorMap != null && errorMap.containsKey("idCard")) {
									out.print(errorMap.get("idCard"));
								}
							%>
						</span>
					</div>
					<div class="form-group">
						<label>License</label>
						<input type="file" name="license" class="form-control" required />
					</div>
					<br />
					<button type="submit" class="btn btn-block btn-success">Register</button>
					<br />
					<div class="text-right">
						<a href="login.jsp" class="btn btn-link">Canel</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>
