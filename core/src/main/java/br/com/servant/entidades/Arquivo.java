package br.com.servant.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import br.gov.anvisa.base.comum.entidade.EntidadeBasica;

@Entity
@Table(name="TB_ARQUIVO")
public class Arquivo extends EntidadeBasica{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CO_ARQUIVO")
	private Long id;
	
	@Column(name="NO_ARQUIVO")
	private String nome;

	@Basic(fetch=FetchType.LAZY)
	@Lob
	@Column(name="FILE")
	private Long[] file;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long[] getFile() {
		return file;
	}

	public void setFile(Long[] file) {
		this.file = file;
	}
	
}
