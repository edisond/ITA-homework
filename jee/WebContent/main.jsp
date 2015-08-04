<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
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
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Nick name</th>
							<th>Online</th>
							<th style="width: 150px">Action</th>
						</tr>
					</thead>
					<tbody>
						<jstl:forEach items="${users}" var="u">
							<tr>
								<td>${u.id}</td>
								<td>${u.name}</td>
								<td>${u.nickName}</td>
								<td>
									<jstl:if test="${u.online}">
										<i class="glyphicon glyphicon-ok text-success"></i>
									</jstl:if>
									<jstl:if test="${!u.online}">
										<i class="glyphicon glyphicon-minus text-muted"></i>
									</jstl:if>
								</td>
								<td>
									<jstl:if test="${!(u.id eq user.id)}">
										<a href="DeleteUser?id=${u.id}" onclick="return confirm('Delete this user?')">delete</a>
									</jstl:if>
									<jstl:if test="${u.id eq user.id}">
										<span class="text-muted">delete</span>
									</jstl:if>
									&emsp;
									<a href="profile.jsp?id=${u.id}">view</a>
								</td>
							</tr>
						</jstl:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>