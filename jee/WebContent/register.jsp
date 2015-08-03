<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<form action="Register" method="post" class="center-block" style="max-width: 450px; margin-top: 100px">
					<h1 class="text-center">Register</h1>
					<div class="form-group">
						<label>Name</label> <input type="text" name="name" class="form-control" />
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password" name="psw" class="form-control" />
					</div>
					<div class="form-group">
						<label>Confirm Password</label> <input type="password" name="psw2" class="form-control" />
					</div>
					<div class="form-group">
						<label>Birth</label> <input type="date" name="birth" class="form-control" />
					</div>
					<div class="form-group">
						<label>Nickname</label> <input type="text" name="nickname" class="form-control" />
					</div>
					<div class="form-group">
						<label>Email</label> <input type="email" name="email" class="form-control" />
					</div>
					<div class="form-group">
						<label>Id Card</label> <input type="text" name="idcard" class="form-control" />
					</div>
					<div class="form-group">
						<label class="radio-inline"> <input type="radio" name="sex" value="true" checked="checked"> Male
						</label> <label class="radio-inline"> <input type="radio" name="sex" value="false"> Female
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