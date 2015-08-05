<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%
		Map<String, String> errorMap = (Map<String, String>) request.getAttribute("errorMap");
	%>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<form action="Register" method="post" class="center-block" style="max-width: 450px; margin-top: 100px">
					<h1 class="text-center">Register</h1>
					<div class="form-group">
						<label>Name</label>
						<input type="text" name="name" class="form-control"
							value="<%=request.getParameter("name") == null ? "" : request.getParameter("name")%>" />
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
						<input type="password" name="psw" class="form-control"
							value="<%=request.getParameter("psw") == null ? "" : request.getParameter("psw")%>" />
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
						<input type="password" name="psw2" class="form-control"
							value="<%=request.getParameter("psw2") == null ? "" : request.getParameter("psw2")%>" />
						<span class="text-danger">
							<%
								if (errorMap != null && errorMap.containsKey("psw2")) {
									out.print(errorMap.get("psw2"));
								}
							%>
						</span>
					</div>
					<div class="form-group">
						<label>Birth</label>
						<input type="date" name="birth" class="form-control"
							value="<%=request.getParameter("birth") == null ? "" : request.getParameter("birth")%>" />
						<span class="text-danger">
							<%
								if (errorMap != null && errorMap.containsKey("birth")) {
									out.print(errorMap.get("birth"));
								}
							%>
						</span>
					</div>
					<div class="form-group">
						<label>Nickname</label>
						<input type="text" name="nickName" class="form-control"
							value="<%=request.getParameter("nickName") == null ? "" : request.getParameter("nickName")%>" />
						<span class="text-danger">
							<%
								if (errorMap != null && errorMap.containsKey("nickName")) {
									out.print(errorMap.get("nickName"));
								}
							%>
						</span>
					</div>
					<div class="form-group">
						<label>Email</label>
						<input type="email" name="email" class="form-control"
							value="<%=request.getParameter("email") == null ? "" : request.getParameter("email")%>" />
						<span class="text-danger">
							<%
								if (errorMap != null && errorMap.containsKey("email")) {
									out.print(errorMap.get("email"));
								}
							%>
						</span>
					</div>
					<div class="form-group">
						<label>Id Card</label>
						<input type="text" name="idCard" class="form-control"
							value="<%=request.getParameter("idCard") == null ? "" : request.getParameter("idCard")%>" />
						<span class="text-danger">
							<%
								if (errorMap != null && errorMap.containsKey("idCard")) {
									out.print(errorMap.get("idCard"));
								}
							%>
						</span>
					</div>
					<div class="form-group">
						<label class="radio-inline">
							<input type="radio" name="sex" value="true" checked="checked">
							Male
						</label>
						<label class="radio-inline">
							<input type="radio" name="sex" value="false">
							Female
						</label>
					</div>
					<button type="submit" class="btn btn-block btn-success">Register</button>
				</form>	
			</div>
		</div>
	</div>
	<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>