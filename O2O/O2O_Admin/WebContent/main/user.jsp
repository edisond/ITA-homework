<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>SMS - main course</title>
<link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<style>
nav.navbar i {
	width: 30px;
}
</style>
</head>

<body>
	<jsp:include page="parts/nav.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<ol class="breadcrumb">
					<li>
						<a href="index.jsp">Home</a>
					</li>
					<li class="active">${param.role }</li>
				</ol>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>ID Card</th>
							<th>Tel</th>
							<th>Status</th>
							<th style="width: 150px">Action</th>
						</tr>
					</thead>
					<tbody>
						<jstl:forEach items="${users}" var="user">
							<tr>
								<td>${user.userId}</td>
								<td>${user.userName}</td>
								<td>${user.idCard}</td>
								<td>${user.tel}</td>
								<td>${user.statusId}</td>
								<td>
									<a href="UpdateUserServlet?id=${user.userId}">update</a>
									&emsp;
									<a href="DeleteUserServlet?id=${user.userId}" onclick="return confirm('Delete this item?')">delete</a>
								</td>
							</tr>
						</jstl:forEach>
					</tbody>
				</table>
				<nav class="text-right">
					<ul class="pagination">
						<li>
							<a href="#" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
						<%
							for (int i = 0; i < Integer.parseInt(request.getAttribute("usersCount").toString()); i += 5) {
								String url = request.getQueryString() == null ? "" : request.getQueryString();
								Integer pg = request.getParameter("p") == null ? 1 : Integer.parseInt(request.getParameter("p").toString());
								Integer current = (i / 5 + 1);
								url = url.replaceAll("&*p=[1234567890]*", "");
								if (url.equals("")) {
									url += "p=" + current;
								} else {
									url += "&p=" + current;
								}
								url = request.getRequestURI() + "?" + url;
								if (current == pg) {
									out.write("<li class='active'><a href='javascript:;'>" + current + "</a></li>");
								} else {
									out.write("<li><a href='" + url + "'>" + current + "</a></li>");
								}
							}
						%>
						<li>
							<a href="#" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
