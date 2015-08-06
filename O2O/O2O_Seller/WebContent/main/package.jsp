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
					<li class="active">Package</li>
				</ol>
				<div>
					<a href="newPackage.jsp" class="btn btn-success">
						<i class="glyphicon glyphicon-plus"></i>
						&nbsp;New
					</a>
				</div>
				<br />
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Price</th>
							<th>Status</th>
							<th style="width: 150px">Action</th>
						</tr>
					</thead>
					<tbody>
						<jstl:forEach items="${packages}" var="p">
							<tr>
								<td>${p.packageId}</td>
								<td>${p.packageName}</td>
								<td>${p.price}</td>
								<td>${p.statusId}</td>
								<td>
									<a href="UpdatePackageServlet?id=${p.packageId }">update</a>
									&emsp;
									<a href="DeletePackageServlet?id=${p.packageId }" onclick="return confirm('Delete this item?')">delete</a>
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
						<li>
							<a href="#">1</a>
						</li>
						<li>
							<a href="#">2</a>
						</li>
						<li>
							<a href="#">3</a>
						</li>
						<li>
							<a href="#">4</a>
						</li>
						<li>
							<a href="#">5</a>
						</li>
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
