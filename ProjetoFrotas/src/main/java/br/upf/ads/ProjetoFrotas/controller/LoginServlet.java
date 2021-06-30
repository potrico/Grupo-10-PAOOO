package br.upf.ads.ProjetoFrotas.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.upf.ads.ProjetoFrotas.jpa.JpaUtil;
import br.upf.ads.ProjetoFrotas.model.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		EntityManager em = JpaUtil.getEntityManager();  
		Query qry = em.createQuery("from Usuario where email = :email and senha = :senha"); 
		qry.setParameter("email", request.getParameter("email")); 
		qry.setParameter("senha", request.getParameter("senha")); 
		Usuario usuarioLogado = null; 
		try { 
		   usuarioLogado = (Usuario) qry.getSingleResult(); 
		} catch (Exception e) { } 
		em.close();  
		if (usuarioLogado == null){ 
		   request.setAttribute("mensagemLogin", "email ou Senha inválida!");   
		   request.getRequestDispatcher("Login.jsp").forward(request, response);   
		}else{ 
		   request.getSession().setAttribute("usuarioLogado", usuarioLogado); 
		   response.sendRedirect("Privada/Home/Home.jsp");  
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
