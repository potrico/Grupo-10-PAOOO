package br.upf.ads.ProjetoFrotas.model;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Long;
import java.lang.String;
import java.util.Base64;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ocorrencia
 *
 */
@Entity

public class Ocorrencia implements Serializable {

	   
	@Id
	private Long id;
	private Date dataHora;
	private String titulo;
	private String descricao;
	private Float lat;
	private Float lon;
	@Lob
	private byte[] foto;
	@ManyToOne
	private Ronda Ronda;
	private static final long serialVersionUID = 1L;

	public Ocorrencia() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public Date getDataHora() {
		return this.dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}   
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}   
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}   
	public Float getLat() {
		return this.lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}   
	public Float getLon() {
		return this.lon;
	}

	public void setLon(Float lon) {
		this.lon = lon;
	}   

	public Ronda getRonda() {
		return this.Ronda;
	}
	public void setRonda(Ronda ronda) {
		this.Ronda = ronda;
	}
	public Ocorrencia(Long id, Date dataHora, String titulo, String descricao, Float lat, Float lon, String foto,
			br.upf.ads.ProjetoFrotas.model.Ronda ronda) {
		super();
		this.id = id;
		this.dataHora = dataHora;
		this.titulo = titulo;
		this.descricao = descricao;
		this.lat = lat;
		this.lon = lon;
		this.Ronda = ronda;
	}
	public Ocorrencia(Long id, Date dataHora, String titulo, String descricao, Float lat, Float lon) {
		super();
		this.id = id;
		this.dataHora = dataHora;
		this.titulo = titulo;
		this.descricao = descricao;
		this.lat = lat;
		this.lon = lon;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public String getFotoBase64() {
		if (foto != null)
		   return new String(Base64.getEncoder().encode(foto));
		else
		   return "";
	}
   
}
