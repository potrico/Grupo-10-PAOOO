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
	
		<script type="text/javascript">
	    function confirmar(){
	    	$.confirm({
	    	    title: 'Confirmar',
	    	    content: 'Confirmar a exclus�o?',
	    	    type: 'orange',
	    	    typeAnimated: true,
	    	    buttons: {
	    	        Sim: { 
	    	        	text: 'Sim',
	    	            btnClass: 'btn-orange',
	    	            action: function(){ 
	    	            	$("#excluir").attr("type", "submit");
	    	            	$("#excluir").click();
	    	        	}
	    	        },
	    	        Nao: { 
	    	        	text: 'N�o',
	    	            btnClass: 'btn-dark',
	    	            action: function(){ 
	    	            	// programar algo se clicou n�o
	    	        	}
	    	        },
	    	    }
	    	});
	    	
	    	
	    }
	</script>
	
	
	<h1>LISTAGEM DE OCORRENCIAS</h1>
	
	<form action="OcorrenciaCon">
		<button type="submit" name="incluir"><i class="fas fa-plus-circle"></i> Incluir</button>
		
		<table border="1" class="table table-hover table-condensed">
		    <thead>
		       <tr>
		           <td>Id</td>
		           <td>Data</td>
		           <td>Titulo</td>
		           <td>Descri��o</td>
		           <td>Lat</td>
		           <td>Lon</td>
		           <td></td>
		       </tr>
		    </thead>
			<c:forEach items="${lista}" var="p" varStatus="cont">
			   <tr>
			      <td>${p.id}</td>
			      <td>${p.dataHora}</td>    
			      <td>${p.titulo}</td>
			      <td>${p.descricao}</td>
			      <td>${p.lat}</td>
			      <td>${p.lon}</td>
			      <td><button type="submit" name="alterarFoto" value="${p.id}">Foto</button></td>
			      <td><button type="submit" name="alterar" value="${p.id}">Alterar</button></td>
			      <td><button type="button" onclick="confirmar()" id="excluir" name="excluir" value="${p.id}">Excluir</button></td>
			   </tr>
		    </c:forEach>
		</table>
	</form>

</body>
</html>