<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	
	<div class="text-danger">${error}</div>
	
	<form:form action="add" method="post" modelAttribute="author">
	<form:errors path="*" cssClass="errorblock" element="div"></form:errors>
		
		<div>
			<form:label path="name">Author Name</form:label>
			<form:input path="name" />
			<form:errors path="name" cssClass="text-danger"></form:errors>
		</div>
		<div>
			<form:label path="email">Email</form:label>
			<form:input path="email" />
			<form:errors path="email" cssClass="text-danger"></form:errors>
		</div>
		<div>
			<form:label path="phno">Phone -</form:label>
			<form:input path="phno" />
			<form:errors path="phno" cssClass="text-danger"></form:errors>
		</div>
		<div>			
			<input type="submit" value="Add Author">
		</div>
	</form:form>
</body>
</html>