<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">

	
</head>
<body>
	<jsp:include page="../fragmentos/Cabecalho.jsp"></jsp:include>
	
	<h1>LISTAGEM DE LOCALIZAÇÕES</h1>
	
	<form action="LocalizacaoCon">
		<button type="submit" name="incluir"><i class="fas fa-plus-circle"></i> Incluir</button>
		
		<table border="1" class="table table-hover table-condensed">
		    <thead>
		       <tr>
		           <td>Id</td>
		           <td>Data</td>
		           <td>Lat</td>
		           <td>Lon</td>
		       </tr>
		    </thead>
			<c:forEach items="${lista}" var="p" varStatus="cont">
			   <tr>
			      <td>${p.id}</td>
			      <td>${p.dataHora}</td>    
			      <td>${p.lat}</td>
			      <td>${p.lon}</td>
			      <td><button type="submit" name="alterar" value="${p.id}">Alterar</button></td>
			      <td><button type="button" onclick="confirmar()" id="excluir" name="excluir" value="${p.id}">Excluir</button></td>
			   </tr>
		    </c:forEach>
		</table>
	</form>

</body>
</html>