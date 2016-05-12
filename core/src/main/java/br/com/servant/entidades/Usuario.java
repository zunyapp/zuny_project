package br.com.servant.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.anvisa.base.comum.entidade.EntidadeBasica;

@Entity
@Table(name="TB_USUARIO")
public class Usuario  extends EntidadeBasica{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CO_USUARIO")
	private String id;
	
	@Column(name="NO_USUARIO")
	private String nome;
	
	@Column(name="DS_EMAIL")
	private String email;
	
	@ManyToOne
	@JoinColumn(name="CO_ARQUIVO")
	private Arquivo fotoPerfil;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Arquivo getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(Arquivo fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

}
	
