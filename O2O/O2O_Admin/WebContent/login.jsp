<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <form action="LoginServlet" method="post" class="center-block well" style="max-width: 500px; margin-top: 50px">
                    <h1 class="text-center">Admin Login</h1>
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" name="name" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="psw" class="form-control" required />
                    </div>
                    <br />
                    <button type="submit" class="btn btn-block btn-primary">Login</button>
                </form>
            </div>
        </div>
    </div>
</body>

</html>
