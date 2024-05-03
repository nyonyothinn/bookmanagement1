<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Book</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="text-danger">${error}</div>
	<form:form action="/bookmanagementsystem/books/edit" method="post" modelAttribute="book">
	<form:errors path="*" cssClass="text-danger" element="div"></form:errors>
		<div>			
			<form:input type="hidden" path="code" />			
		</div>
		<div>
			<form:label path="name">Book Name</form:label>
			<form:input path="name" />
			<form:errors path="name" cssClass="text-danger"></form:errors>
		</div>
		<div>
			<form:label path="author_id">Author</form:label>						
			<form:select  path="author_id"  >
				<form:option value="0">Select Author</form:option>		 
				<c:forEach var="author" items="${authors}">
     				<form:option value="${author.id}">${author.name}</form:option>
 				 </c:forEach>
			</form:select>
			<form:errors path="author_id" cssClass="text-danger"></form:errors>
		</div>
		<div>
			<form:label path="price">Price ($)-</form:label>
			<form:input path="price" />
			<form:errors path="price" cssClass="text-danger"></form:errors>
		</div>
		<div>			
			<input type="submit" value="Update Book">
		</div>
	</form:form>

</body>
</html>