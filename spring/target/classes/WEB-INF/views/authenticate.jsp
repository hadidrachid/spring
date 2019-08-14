<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<title>Authentication</title>
</head>
<body>
	<h2>${msg}</h2>
	
	<a href="${pageContext.request.contextPath}/georgette/display?lang=fr">FR</a>
	<a href="${pageContext.request.contextPath}/georgette/display?lang=en">EN</a>
	
	
	<a href="${pageContext.request.contextPath}/georgette/load"><spring:message code="load.data.user" /></a>
	
	<form:form method="post" action="${pageContext.request.contextPath}/georgette/authentication/avec/validation" modelAttribute="userBean">
		<form:errors path="email" />
		<form:input path="email"/>
		
		<form:errors path="password" />
		<form:input path="password"/>
		<input type="submit" value="Login">
	</form:form>
</body>
</html>
