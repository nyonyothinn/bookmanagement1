<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="text-danger">${error}</div>
<a href="add">Create Book</a>
	<div class="row">
		<div class="col">Code</div>
		<div class="col">Name</div>
		<div class="col">Author</div>
		<div class="col">Price</div>
		<div class="col"></div>
	</div>
	
	<c:forEach var="b" items="${books}">
		<div class="row">
			<div class="col">${b.code}</div>
			<div class="col">${b.name}</div>
			<div class="col">${b.author_name}</div>
			<div class="col">${b.price}</div>
			<div class="col">
				<a href="edit/${b.code}">Update</a>|
				<a href="delete/${b.code}">Delete</a>
			</div>
	   </div>
	</c:forEach> 
	

</body>
</html>