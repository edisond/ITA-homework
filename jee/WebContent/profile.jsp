<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h1 class="page-header">
					Welcome, ${user.name}.&emsp;
					<a href="Logout" class="btn btn-default">Logout</a>
					<small class="pull-right">
						Online:
						<%=session.getServletContext().getAttribute("onlineCount")%></small>
				</h1>

				<jstl:if test="${profile eq null}">
					<h2>User not exists</h2>
				</jstl:if>
				<jstl:if test="${!(profile eq null)}">
					<h2>User profile</h2>
					<h3>Id: ${profile.id}</h3>
					<h3>Name: ${profile.name}</h3>
					<h3>Nick name: ${profile.nickName}</h3>
					<h3>Sex: ${profile.sex}</h3>
					<h3>Email: ${profile.email}</h3>
					<h3>Id card: ${profile.idCard}</h3>
					<h3>Birth: ${profile.birth}</h3>
				</jstl:if>
			</div>
		</div>
	</div>
	<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>