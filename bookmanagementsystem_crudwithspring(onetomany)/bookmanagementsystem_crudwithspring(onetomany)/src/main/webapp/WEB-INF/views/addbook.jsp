<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<div class="text-danger">${error}</div>
			<form:form action="/bookmanagementsystem/books/add" method="post" modelAttribute="book">
			<form:errors path="*" cssClass="text-danger" element="div"></form:errors>
				<div class="mb-3">
					<form:label path="code" class="form-label">Code</form:label>
					<form:input path="code" class="form-control"/>
					<form:errors path="code" cssClass="text-danger"></form:errors>
				</div>
				<div class="mb-3">
					<form:label path="name" class="form-label">Book Name</form:label>
					<form:input path="name" class="form-control"/>
					<form:errors path="name" cssClass="text-danger"></form:errors>
				</div>
				<div class="mb-3">		
					<form:label path="author_id" class="form-label">Author</form:label>						
					<form:select  path="author_id"  class="form-select">
						<form:option value="0">Select Author</form:option>		 
						<c:forEach var="author" items="${authors}">
     						<form:option value="${author.id}">${author.name}</form:option>
 				 		</c:forEach>
					</form:select>
					<form:errors path="author_id" cssClass="text-danger"></form:errors>
				</div>
				<div class="mb-3">
					<form:label path="price" class="form-label">Price ($)-</form:label>
					<form:input path="price" class="form-control"/>
					<form:errors path="price" cssClass="text-danger"></form:errors>
				</div>
				<div class="mb-3">			
					<input type="submit" value="Add Book" class="btn btn-primary">
				</div>
			</form:form>
		</div>
	</div>
	

	
</body>
</html>