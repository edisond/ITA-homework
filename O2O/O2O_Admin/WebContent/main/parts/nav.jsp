<%@page import="com.oocl.o2o.pojo.User"%>
<%@ page language="java" contentType="text/jsp; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-nav">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/O2O_Admin/main/index.jsp">ADMIN</a>
		</div>
		<div class="collapse navbar-collapse" id="main-nav">
			<ul class="nav navbar-nav">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
						User
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="user.jsp?role=seller">
								<i class="glyphicon glyphicon-usd"></i>
								Seller
							</a>
						</li>
						<li>
							<a href="user.jsp?role=customer">
								<i class="glyphicon glyphicon-user"></i>
								Customer
							</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
						Food
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<jstl:forEach items="${foodTypes}" var="ft">
							<li>
								<a href="/O2O_Admin/main/food.jsp?type=${ft.foodTypeId}">${ft.foodTypeName}</a>
							</li>
						</jstl:forEach>
					</ul>
				</li>
				<li>
					<a href="/O2O_Admin/main/package.jsp">Package</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
						${user.userName}
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="/O2O_Admin/LogoutServlet">
								<i class="glyphicon glyphicon-log-out"></i>
								&emsp;Logout
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>
