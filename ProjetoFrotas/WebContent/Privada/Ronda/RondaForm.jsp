<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
</head>
<body>
	<jsp:include page="../fragmentos/Cabecalho.jsp"></jsp:include>
	<form action="RondaCon" method="post">
		<fieldset>
		    <legend>Formulário:</legend>
	
			<div class="row">
				<div class="col-md-2">
					<label>Id</label>
	        		<input class="form-control" type="number" name="id" value="${obj.id}">
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">
					<label>Data de Inicio</label>
	        		<input class="form-control" type="date" name="dataHoraInicio" value="${obj.dataHoraInicio}">
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">
					<label>Data de Inicio</label>
	        		<input class="form-control" type="date" name="dataHoraFim" value="${obj.dataHoraFim}">
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">
					<label>Ultima Latitude</label>
	        		<input class="form-control" type="number" name="latUltima" value="${obj.latUltima}">
				</div>
			</div>
				<div class="row">
				<div class="col-md-2">
					<label>Ultima Longitude</label>
	        		<input class="form-control" type="number" name="lonUltima" value="${obj.lonUltima}">
				</div>
			</div>		
				<div class="row">
				<div class="col-md-2">
					<label>Ultima Data</label>
	        		<input class="form-control" type="date" name="dataHoraUltima" value="${obj.dataHoraUltima}">
				</div>
			</div>
			
	
	
	
	        	
	        <button class="btn btn-primary" type="submit" name="gravar">Gravar</button>	
	        <button type="submit" name="cancelar">Cancelar</button>
		
		</fieldset>
	</form>

</body>
</html>