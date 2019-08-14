<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<title>CRUD utilisateurs</title>
</head>
<body>

	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Version</th>
				<th>Nom</th>
				<th>Email</th>
				<th>Mot de passe</th>
				<th>Actions</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${usersList}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.version}</td>
					<td>${user.nom}</td>
					<td>${user.email}</td>
					<td>${user.password}</td>
					<td><a
						href="${pageContext.request.contextPath}/userManagement/modify/${user.id}">Modifier</a>
						| <a
						href="${pageContext.request.contextPath}/userManagement/delete/${user.id}">Supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	
	<c:if test="${sessionScope.user.role != 'ADMIN' }">
		<a href="">test if</a>
	</c:if>
	
	
	<form:form method="post" action="${pageContext.request.contextPath}/userManagement/create" modelAttribute="userManagementForm">
		<form:label path="id">Id :</form:label>
		<form:input path="id" readonly="true" value="${userToModify.id}"/>
		
		<form:label path="version">Version :</form:label>
		<form:input path="version" readonly="true" value="${userToModify.version}"/>
		
		<form:errors path="nom"/>
		<form:label path="nom">Nom :</form:label>
		<form:input path="nom" value="${userToModify.nom}"/>
		
		<form:errors path="email"/>
		<form:label path="email">Email :</form:label>
		<form:input path="email" value="${userToModify.email}"/>
		
		<form:errors path="password"/>
		<form:label path="password">Password :</form:label>
		<form:input path="password" value="${userToModify.password}"/>
	
		<input type="submit" value="Creer / Modifier User"/>
	</form:form>
	<br>
	
	<div>
		<a href="${pageContext.request.contextPath}/userManagement/exporter">Exporter CSV</a>
	</div>
	
	<br>
	
	<div>
		<form action="${pageContext.request.contextPath}/userManagement/importer" enctype="multipart/form-data" method="post">
			
			<label for="user-file">Importer CSV : </label> 
			<input type="file" name="user-file"> 
			<input type="submit" value="Importer" />
		</form>
	</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>
