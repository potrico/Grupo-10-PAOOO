<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
</head>
<body>
	<jsp:include page="../fragmentos/Cabecalho.jsp"></jsp:include>
	<form action="OcorrenciaCon" method="post">
		<fieldset>
		    <legend>Formul�rio:</legend>
	
			<div class="row">
				<div class="col-md-2">
					<label>Id</label>
	        		<input class="form-control" type="number" name="id" value="${obj.id}">
				</div>
			</div>
	
			<div class="row">
				<div class="col-md-5">
			        <label>Data</label>
			        <input class="form-control"  type="date" name="dataHora" value="${obj.dataHora}">			
				</div>
			</div>
	
			<div class="row">
				<div class="col-md-2">
			        <label>Titulo</label>
			        <input class="form-control"  type="text" name="titulo" value="${obj.titulo}">			
				</div>
			</div>
			<div class="row">
				<div class="col-md-5">
			        <label>Descricao</label>
			        <input class="form-control"  type="text" name="descricao" value="${obj.descricao}">			
				</div>
			</div>
			<div class="row">
				<div class="col-md-5">
			        <label>Latitude</label>
			        <input class="form-control"  type="number" name="lat" value="${obj.lat}">			
				</div>
			</div>
	
			<div class="row">
				<div class="col-md-5">
			        <label>Longitude</label>
			        <input class="form-control"  type="number" name="lon" value="${obj.lon}">			
				</div>
			</div>
	
	
	
	        	
	        <button class="btn btn-primary" type="submit" name="gravar">Gravar</button>	
	        <button type="submit" name="cancelar">Cancelar</button>
		
		</fieldset>
	</form>

</body>
</html>