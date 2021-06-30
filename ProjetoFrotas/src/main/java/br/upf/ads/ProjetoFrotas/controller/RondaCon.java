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
import br.upf.ads.ProjetoFrotas.model.Locomocao;
import br.upf.ads.ProjetoFrotas.model.Pessoa;
import br.upf.ads.ProjetoFrotas.model.Ronda;
import br.upf.ads.ProjetoFrotas.uteis.Upload;
import net.iamvegan.multipartrequest.HttpServletMultipartRequest;

/**
 * Servlet implementation class RondaCon
 */
@WebServlet("/Privada/Ronda/RondaCon")
public class RondaCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RondaCon() {
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
			
			
		} else if (request.getParameter("vigilantes") != null) {
			vigilantes(request, response);				
			
		} else if (request.getParameter("incluirVigilante") != null) {
			incluirVigilante(request, response);
			
		} else if (request.getParameter("excluirVigilante") != null) {
			excluirVigilante(request, response);
			
		} else {
			listar(request, response);
		}
		
	}
	
	
	private void vigilantes(HttpServletRequest request, HttpServletResponse response) {
		listarVigilantes(request, response, Long.parseLong(request.getParameter("vigilantes")));
	}
	
	
	private void listarVigilantes(HttpServletRequest request, HttpServletResponse response, Long idRonda) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			Ronda obj = em.find(Ronda.class, idRonda);
			request.setAttribute("obj", obj);
			List<Pessoa> pessoas = em.createQuery("from Pessoa order by nome").getResultList();
			request.setAttribute("pessoas", pessoas);
			em.close();
			request.getRequestDispatcher("RondaVigilantes.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}	
	
	
	private void incluirVigilante(HttpServletRequest request, HttpServletResponse response) {
		EntityManager em = JpaUtil.getEntityManager(); // pega a entitymanager para persistir
		em.getTransaction().begin(); 	// inicia a transação
		// pegar a ronda onde deve ser adicionado um vigilante
		Ronda r = em.find(Ronda.class, Long.parseLong(request.getParameter("idRonda")));
		// Pegar o vigilante escolhido
		Pessoa p = em.find(Pessoa.class, Long.parseLong(request.getParameter("vigilante")));
		// adiconar o vigilante na ronda
		r.getVigilantes().add(p);
		em.merge(r); // merge no objeto principal = ronda = vigilantes vão ser armazenados em cascata = Cascade na classe!!!
		em.getTransaction().commit(); 	// commit na transação
		em.close();
		
		// depois de armazenado, voltamos para a lista de vigilantes atualizada. Vamos aproveitar o método acima
		listarVigilantes(request, response, r.getId());
		
	}	
	
	private void excluirVigilante(HttpServletRequest request, HttpServletResponse response) {
		EntityManager em = JpaUtil.getEntityManager(); // pega a entitymanager para persistir
		em.getTransaction().begin(); 	// inicia a transação
		// pegar a ronda onde deve ser excluido um vigilante
		Ronda r = em.find(Ronda.class, Long.parseLong(request.getParameter("idRonda")));
		// Pegar o vigilante escolhido
		Pessoa p = em.find(Pessoa.class, Long.parseLong(request.getParameter("excluirVigilante")));
		// remover o vigilante na ronda
		r.getVigilantes().remove(p);
		em.merge(r); // merge no objeto principal = ronda = vigilantes vão ser armazenados em cascata = Cascade na classe!!!
		em.getTransaction().commit(); 	// commit na transação
		em.close();
		
		// depois de armazenado, voltamos para a lista de vigilantes atualizada. Vamos aproveitar o método acima
		listarVigilantes(request, response, r.getId());
		
	}		
	
	
	

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			List<Ronda> lista = em.createQuery("from Ronda").getResultList(); // recuperamos as pessoas do BD
			request.setAttribute("lista", lista);
			em.close();
			request.getRequestDispatcher("RondaList.jsp").forward(request, response);
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
			d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(request.getParameter("dataHoraInicio"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date d2 = null;
		try {
			d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(request.getParameter("dataHoraFim"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
	Date d3 = null;
	try {
		d3 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(request.getParameter("dataHoraUltima"));
	} catch (ParseException e) {
		e.printStackTrace();
	}
	
	
		Ronda p = new Ronda(
				    Long.parseLong(request.getParameter("id")), 
					d1, 
					d2, 
					Float.parseFloat(request.getParameter("latUltima")), 
					Float.parseFloat(request.getParameter("lonUltima")), 
					d3,
					new ArrayList(), 
					null);
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
		em.remove(em.find(Ronda.class, Long.parseLong(request.getParameter("excluir"))));	// excluir o objeto no BD
		em.getTransaction().commit(); 	// commit na transação
		em.close();
		listar(request, response);
	}

	private void alterar(HttpServletRequest request, HttpServletResponse response) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			Ronda obj = em.find(Ronda.class, Long.parseLong(request.getParameter("alterar")));
			request.setAttribute("obj", obj);
			em.close();
			request.getRequestDispatcher("RondaForm.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private void incluir(HttpServletRequest request, HttpServletResponse response) {
		try {
			Ronda obj = new Ronda();
			request.setAttribute("obj", obj);
			request.getRequestDispatcher("RondaForm.jsp").forward(request, response);
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
