<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	${msg}</h1>
	
	<div>
		<a href="${pageContext.request.contextPath}/exemples/exemple1">Mon Exemple 1 : injection d'un message dans la jsp</a>
	</div>
	
	<div>
		<a href="${pageContext.request.contextPath}/exemples/exemple2/coucou/4">Mon Exemple 2 : avec passage de paramètres dans l'URL</a>
	</div>
	
	<div>
		<a href="${pageContext.request.contextPath}/exemples/exemple3/coucou/cheminQuelconque/1/2">Mon Exemple 3 : récupération des params dans une map</a>
	</div>
	
	<form action="${pageContext.request.contextPath}/exemples/exemple4" method="post">
		<input type="text" name="description">
		<input type="submit" value="Envoyer">
	</form>
	
	<form:form action="${pageContext.request.contextPath}/exemples/exemple4" 
											method="post" modelAttribute="monBean">
		<input type="text" name="description">
		<input type="text" name="prix">
		<input type="submit" value="Envoyer">
	</form:form>


	<form:form action="${pageContext.request.contextPath}/exemples/exemple6" 
											method="post" modelAttribute="monBean">
		<form:input path="description"/>
		<form:input path="prix"/>
		<input type="submit" value="Envoyer">
	</form:form>





















<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
