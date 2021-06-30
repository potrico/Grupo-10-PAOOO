<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
</head>
<body>
	<jsp:include page="../fragmentos/Cabecalho.jsp"></jsp:include>
	<form action="LocomocaoCon" method="post">
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
			        <label>Descri��o</label>
			        <input class="form-control"  type="text" name="descricao" value="${obj.descricao}">			
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-5">
			        <label>Placa</label>
			        <input class="form-control"  type="text" name="placa" value="${obj.placa}">			
				</div>
			</div>
	        	
	        <button class="btn btn-primary" type="submit" name="gravar">Gravar</button>	
	        <button type="submit" name="cancelar">Cancelar</button>
		
		</fieldset>
	</form>

</body>
</html>