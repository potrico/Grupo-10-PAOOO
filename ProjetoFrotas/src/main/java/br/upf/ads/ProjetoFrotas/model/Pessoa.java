package br.upf.ads.ProjetoFrotas.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pessoa
 *
 */
@Entity

public class Pessoa implements Serializable {

	   
	@Id
	private Long id;
	private String nome;
	private String loginApp;
	private String senha;
	private static final long serialVersionUID = 1L;

	public Pessoa() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getLoginApp() {
		return this.loginApp;
	}

	public void setLoginApp(String loginApp) {
		this.loginApp = loginApp;
	}   
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Pessoa(Long id, String nome, String loginApp, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.loginApp = loginApp;
		this.senha = senha;
	}
   
}
