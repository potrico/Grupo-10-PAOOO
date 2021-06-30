<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
</head>
<body>
	<jsp:include page="../fragmentos/Cabecalho.jsp"></jsp:include>
	<form action="PessoaCon" method="post">
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
			        <label>Nome</label>
			        <input class="form-control"  type="text" name="nome" value="${obj.nome}">			
				</div>
			</div>
	
			<div class="row">
				<div class="col-md-2">
			        <label>Login App</label>
			        <input class="form-control"  type="text" name="loginApp" value="${obj.loginApp}">			
				</div>
			</div>
				<div class="row">
				<div class="col-md-2">
			        <label>Senha</label>
			        <input class="form-control"  type="text" name="senha" value="${obj.senha}">			
				</div>
			</div>
	
	
	
	        	
	        <button class="btn btn-primary" type="submit" name="gravar">Gravar</button>	
	        <button type="submit" name="cancelar">Cancelar</button>
		
		</fieldset>
	</form>

</body>
</html>