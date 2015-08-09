<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>SMS - update seller</title>
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
					<li>
						<a href="user.jsp?role=${user.role }">${user.role }</a>
					</li>
					<li class="active">Update seller</li>
				</ol>

				<form action="UpdateUserServlet" method="post" class="center-block" style="max-width: 500px" enctype="multipart/form-data">
					<h1 class="page-header">Update seller</h1>
					<div class="form-group">
						<label>Name</label>
						<input type="text" name="name" class="form-control" value="${user.userId}" required />
					</div>
					<div class="form-group">
						<label>ID Card</label>
						<input type="text" name="idCard" class="form-control" value="${user.idCard}" required />
					</div>
					<div class="form-group">
						<label>Tel</label>
						<input type="tel" name="tel" class="form-control" value="${user.tel}" required />
					</div>
					<div class="form-group">
						<label>Lisence</label>
						<input type="file" name="license" class="form-control" required />
					</div>
					<img src="license.jpg" style="width: 100%">
					<div class="form-group">
						<label>Status</label>
						<select class="form-control" name="type" required>
							<jstl:forEach items="${status }" var="s">
								<option value="${s.statusId }" ${user.statusId == s.statusId?"selected='selected'":""}>${s.statusName}</option>
							</jstl:forEach>
						</select>
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
