package br.upf.ads.ProjetoFrotas.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.upf.ads.ProjetoFrotas.jpa.JpaUtil;
import br.upf.ads.ProjetoFrotas.model.Ocorrencia;
import br.upf.ads.ProjetoFrotas.uteis.Upload;
import net.iamvegan.multipartrequest.HttpServletMultipartRequest;

/**
 * Servlet implementation class OcorrenciaCon
 */
@WebServlet("/Privada/Ocorrencia/OcorrenciaCon")
public class OcorrenciaCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OcorrenciaCon() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request = new HttpServletMultipartRequest(
				request,
				HttpServletMultipartRequest.MAX_CONTENT_LENGTH,
				HttpServletMultipartRequest.SAVE_TO_TMPDIR,
				HttpServletMultipartRequest.IGNORE_ON_MAX_LENGTH,
				HttpServletMultipartRequest.DEFAULT_ENCODING);		
		
		
		if (request.getParameter("incluir") != null) {
			incluir(request, response);
		} else if (request.getParameter("alterar") != null) {
			alterar(request, response);
		} else if (request.getParameter("excluir") != null) {
            excluir(request, response);			
		} else if (request.getParameter("gravar") != null) {
			gravar(request, response);			
		} else if (request.getParameter("cancelar") != null) {
			cancelar(request, response);			
		} else if (request.getParameter("alterarFoto") != null) {
			alterarFoto(request, response);
		} else if (request.getParameter("gravarFoto") != null) {
			gravarFoto(request, response);	
		} else {
			listar(request, response);
		}
		
	}
	
	private void alterarFoto(HttpServletRequest request, HttpServletResponse response) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			Ocorrencia obj = em.find(Ocorrencia.class, Long.parseLong(request.getParameter("alterarFoto")));
			request.setAttribute("obj", obj);
			em.close();
			request.getRequestDispatcher("OcorrenciaFoto.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private void gravarFoto(HttpServletRequest request, HttpServletResponse response) {
		EntityManager em = JpaUtil.getEntityManager(); // pega a entitymanager para persistir
		// ----------------------------------------------------------------------------------
		em.getTransaction().begin(); 	// inicia a transação
		Ocorrencia obj = em.find(Ocorrencia.class, Long.parseLong(request.getParameter("id")));

		// Vamos ver se veio o arquivo do form
		if (request.getParameter("foto") != null) {
			String nomeArquivo = "Foto"+request.getParameter("id")+".jpg";
			// pegar o caminho de contexto de execução da aplicação para a pasta uploads
			String caminho = getServletConfig().getServletContext().getRealPath("/") + "Privada/Ocorrencia/uploads";
			// copiar arquivo de upload para a pasta
			Upload.copiarArquivo((HttpServletMultipartRequest) request, "foto", caminho, nomeArquivo);
			
			
			// colocar no banco de dados
			obj.setFoto( Upload.getBytesArquivo((HttpServletMultipartRequest) request, "foto") );
			
		}		
		
		em.merge(obj); 	
		em.getTransaction().commit(); 	// commit na transação
		em.close();
		listar(request, response);
	}	
	
	
	

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			List<Ocorrencia> lista = em.createQuery("from Ocorrencia").getResultList(); // recuperamos as pessoas do BD
			request.setAttribute("lista", lista);
			em.close();
			request.getRequestDispatcher("OcorrenciaList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private void cancelar(HttpServletRequest request, HttpServletResponse response) {
		listar(request, response);		
	}

	private void gravar(HttpServletRequest request, HttpServletResponse response) {
		EntityManager em = JpaUtil.getEntityManager(); // pega a entitymanager para persistir
		Date d1 = null;
		try {
			d1 = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataHora"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Ocorrencia p = new Ocorrencia(
					Long.parseLong(request.getParameter("id")), 
					d1, 
					request.getParameter("titulo"),
					request.getParameter("descricao"),
					Float.parseFloat(request.getParameter("lat")),
					Float.parseFloat(request.getParameter("lon"))
					);
		// ----------------------------------------------------------------------------------
		em.getTransaction().begin(); 	// inicia a transação
		em.merge(p); 					// incluir ou alterar o objeto no BD
		em.getTransaction().commit(); 	// commit na transação
		em.close();
		listar(request, response);
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response) {
		EntityManager em = JpaUtil.getEntityManager(); // pega a entitymanager para persistir
		em.getTransaction().begin(); 	// inicia a transação
		em.remove(em.find(Ocorrencia.class, Long.parseLong(request.getParameter("excluir"))));	// excluir o objeto no BD
		em.getTransaction().commit(); 	// commit na transação
		em.close();
		listar(request, response);
	}

	private void alterar(HttpServletRequest request, HttpServletResponse response) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			Ocorrencia obj = em.find(Ocorrencia.class, Long.parseLong(request.getParameter("alterar")));
			request.setAttribute("obj", obj);
			em.close();
			request.getRequestDispatcher("OcorrenciaForm.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private void incluir(HttpServletRequest request, HttpServletResponse response) {
		try {
			Ocorrencia obj = new Ocorrencia();
			request.setAttribute("obj", obj);
			request.getRequestDispatcher("OcorrenciaForm.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
