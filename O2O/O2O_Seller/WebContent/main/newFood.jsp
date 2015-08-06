<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>SMS - new food</title>
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
					<li class="active">New food</li>
				</ol>

				<form action="NewFoodServlet" method="post" class="center-block" style="max-width: 500px"
					enctype="multipart/form-data">
					<h1 class="page-header">New food</h1>
					<div class="form-group">
						<label>Name</label>
						<input type="text" name="name" class="form-control" required />
					</div>
					<div class="form-group">
						<label>Price</label>
						<input type="number" name="price" class="form-control" required step="0.01" />
					</div>
					<div class="form-group">
						<label>Type</label>
						<select class="form-control" name="type" required>
							<jstl:forEach items="${foodTypes }" var="ft">
								<option value="${ft.foodTypeId }">${ft.foodTypeName }</option>
							</jstl:forEach>
						</select>
					</div>
					<div class="form-group">
						<label>Picture</label>
						<input type="file" name="picture" class="form-control" required />
					</div>
					<br />
					<button type="submit" class="btn btn-block btn-primary">Save</button>
				</form>
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
