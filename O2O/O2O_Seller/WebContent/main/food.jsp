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
					<li class="active">
						<jstl:forEach items="${foodTypes}" var="ft">
							<jstl:if test="${ft.foodTypeId eq param.type}">${ft.foodTypeName }</jstl:if>
						</jstl:forEach>
					</li>
				</ol>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Price</th>
							<th>Picture</th>
							<th>Status</th>
							<th style="width: 150px">Action</th>
						</tr>
					</thead>
					<tbody>
						<jstl:forEach items="${foods}" var="food">
							<tr>
								<td>${food.foodId}</td>
								<td>${food.foodName}</td>
								<td>${food.price}</td>
								<td>
									<img src="data:image/jpg;base64,${images[food.pictureUrl]}" width="100" />
								</td>
								<td>${food.statusId}</td>
								<td>
									<a href="UpdateFoodServlet?id=${food.foodId}">update</a>
									&emsp;
									<a href="DeleteFoodServlet?id=${food.foodId}" onclick="return confirm('Delete this item?')">delete</a>
								</td>
							</tr>
						</jstl:forEach>
					</tbody>
				</table>
				<nav class="text-right">
					<ul class="pagination">
						<li>
							<a href="#">
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
						<%
							for (int i = 0; i < Integer.parseInt(request.getAttribute("foodsCount").toString()); i += 5) {
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
							<a href="#">
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
