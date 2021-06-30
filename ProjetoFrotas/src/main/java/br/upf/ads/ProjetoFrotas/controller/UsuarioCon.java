package br.upf.ads.ProjetoFrotas.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.upf.ads.ProjetoFrotas.jpa.JpaUtil;
import br.upf.ads.ProjetoFrotas.model.Usuario;
import net.iamvegan.multipartrequest.HttpServletMultipartRequest;

/**
 * Servlet implementation class UsuarioCon
 */
@WebServlet("/Privada/Usuario/UsuarioCon")
public class UsuarioCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UsuarioCon() {
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
		}	else {
				listar(request, response);
			}
	
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			List<Usuario> lista = em.createQuery("from Usuario").getResultList(); // recuperamos as pessoas do BD
			request.setAttribute("lista", lista);
			em.close();
			request.getRequestDispatcher("UsuarioList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private void cancelar(HttpServletRequest request, HttpServletResponse response) {
		listar(request, response);		
	}

	private void gravar(HttpServletRequest request, HttpServletResponse response) {
		EntityManager em = JpaUtil.getEntityManager(); // pega a entitymanager para persistir
		Usuario p = new Usuario(
					Long.parseLong(request.getParameter("id")), 
					request.getParameter("nome"),
					request.getParameter("email"),
					request.getParameter("senha"));
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
		em.remove(em.find(Usuario.class, Long.parseLong(request.getParameter("excluir"))));	// excluir o objeto no BD
		em.getTransaction().commit(); 	// commit na transação
		em.close();
		listar(request, response);
	}

	private void alterar(HttpServletRequest request, HttpServletResponse response) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			Usuario obj = em.find(Usuario.class, Long.parseLong(request.getParameter("alterar")));
			request.setAttribute("obj", obj);
			em.close();
			request.getRequestDispatcher("UsuarioForm.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private void incluir(HttpServletRequest request, HttpServletResponse response) {
		try {
			Usuario obj = new Usuario();
			request.setAttribute("obj", obj);
			request.getRequestDispatcher("UsuarioForm.jsp").forward(request, response);
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