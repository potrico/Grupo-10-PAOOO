<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>App Aula</title>

	<script src="../../resources/jquery/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="../../resources/jquery-confirm/jquery-confirm.min.css">
    <script src="../../resources/jquery-confirm/jquery-confirm.min.js"></script> 	

	<link rel="stylesheet" href="../../resources/bootstrap/css/bootstrap.min.css">
	<script src="../../resources/bootstrap/js/bootstrap.min.js"></script>

	<link href="../../resources/fontawesome/css/all.css" rel="stylesheet">

	<link href="../../resources/smartmenus/css/sm-core-css.css" rel="stylesheet" type="text/css"/>
	<link href="../../resources/smartmenus/css/sm-mint/sm-mint.css" rel="stylesheet" type="text/css" />	
	    

	
	<link href="../../resources/estilos.css" rel="stylesheet">
	
</head>
<body>
    <h3>PROJETO FROTAS</h3>
    <h2>${sessionScope.usuarioLogado.nome}</h2>
    
	<nav class="main-nav">
	  <!-- Sample menu definition -->
	  <ul id="main-menu" class="sm sm-mint">
	    <li><a href="../Home/Home.jsp">Home</a></li>
	    <li><a href="#">Cadastros</a>
	      <ul>
	        <li><a href="../Ronda/RondaCon">Rondas</a></li>
	        <li><a href="../Pessoa/PessoaCon">Pessoas</a></li>
	        <li><a href="../Locomocao/LocomocaoCon">Locomo��o</a></li>
	        <li><a href="../Usuario/UsuarioCon">Usuario</a></li>
	        <li><a href="../Ocorrencia/OcorrenciaCon">Ocorrencia</a></li>
	        <li><a href="../Localizacao/LocalizacaoCon">Localizac�o</a></li>
	      </ul>
	    </li>
	    <li><a href="#">Relat�rios</a>
	      <ul>
	        <li><a href="../Relatorios/RelOcorrenciaServlet">Ocorrencia</a></li>
	        <li><a href="../Relatorios/RelPessoasServlet">Pessoas</a></li>
	        <li><a href="../Relatorios/RelRondaServlet">Ronda</a></li>
	      </ul>
	    </li>
	    <li><a href="../../index.html">Sair</a></li>
	  </ul>
	</nav>


	<!-- SmartMenus jQuery plugin -->
	<script type="text/javascript"	src="../../resources/smartmenus/jquery.smartmenus.js"></script>
	<!-- SmartMenus jQuery init -->
	<script type="text/javascript">
		$(function() {
			$('#main-menu').smartmenus({
				subMenusSubOffsetX : 1,
				subMenusSubOffsetY : -8
			});
		});
	</script>


</body>
</html>