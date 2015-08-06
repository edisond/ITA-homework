<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>SMS - new package</title>
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
						<a href="package.jsp">Package</a>
					</li>
					<li class="active">New package</li>
				</ol>

				<form action="NewPackageServlet" method="post">
					<h1 class="page-header">New Package</h1>
					<div class="form-group">
						<label>Name</label>
						<input type="text" name="name" class="form-control" required />
					</div>
					<div class="form-group">
						<label>Price</label>
						<input type="number" name="price" class="form-control" required step="0.01" />
					</div>
					<div class="form-group">
						<label>Foods</label>
						<input type="text" name="foods" class="form-control" required />
					</div>
					<div class="row">
						<jstl:forEach items="${foodTypes }" var="ft">
							<div class="col-xs-4">
								<h4>${ft.foodTypeName}</h4>
								<div class="form-group">
									<select class="form-control" required>
										<jstl:forEach items="${foods}" var="f">
											<jstl:if test="${f.foodTypeId==ft.foodTypeId }">
												<option value="${f.foodId }">${f.foodName }</option>
											</jstl:if>
										</jstl:forEach>
									</select>
								</div>
								<button type="button" class="btn btn-success" data-role="add-food">
									<i class="glyphicon glyphicon-plus"></i>
									&emsp;Add
								</button>
							</div>
						</jstl:forEach>
					</div>
					<br />
					<button type="submit" class="btn btn-block btn-primary">Save</button>
				</form>
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(
				function() {
					$('button[data-role=add-food]').click(
							function() {
								$this = $(this);
								inputFoods = $('input[name=foods]').focus();
								if (inputFoods.val() == '') {
									inputFoods.val($this.prev().find('select')
											.val());
								} else {
									inputFoods
											.val(inputFoods.val()
													+ ','
													+ $this.prev().find(
															'select').val());
								}

							});
				})
	</script>
</body>

</html>
