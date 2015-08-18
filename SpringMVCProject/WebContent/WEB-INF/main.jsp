<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome, ${user.name }...</h1>
	<div>
		<a href="newBook">New book</a>
	</div>
	<table>
		<tbody>
			<c:forEach items="${books }" var="book">
				<tr>
					<td>${book.id }</td>
					<td>${book.name }</td>
					<td>
						<a href="book/${book.id}">view</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>