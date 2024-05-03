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
<a href="add">Create Author</a>
	<div class="row">		
		<div class="col">Name</div>
		<div class="col">Email</div>
		<div class="col">Phone</div>
		<div class="col"></div>
	</div>
	
	<c:forEach var="author" items="${authors}">
		<div class="row">			
			<div class="col">${author.name}</div>
			<div class="col">${author.email}</div>
			<div class="col">${author.phno}</div>
			<div class="col">
				<a href="edit/${author.id}">Update</a>|
				<a href="delete/${author.id}">Delete</a>
			</div>
	   </div>
	</c:forEach> 
	
</body>
</html>