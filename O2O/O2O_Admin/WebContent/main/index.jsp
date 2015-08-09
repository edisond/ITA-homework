<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Admin - home</title>
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
                <h1 class="page-header">Welcome to administrative system.</h1>
                <h3><b class="text-primary">66</b><small> unread applications from JMS</small></h3>
            </div>
        </div>
    </div>
    <script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
