<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<div>
	<a href="${pageContext.request.contextPath}/exemples/display">Afficher page exemple.jsp</a>
</div>
<div>
	<a href="${pageContext.request.contextPath}/georgette/display">S'authentifier</a>
</div>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
