package br.com.caio.apicarros.domain.dto;

import java.io.Serializable;

import br.com.caio.apicarros.domain.Carro;

public class CarroDTO implements Serializable {
	
	private Long id;
	
	private String nome;

	private String tipo;
	
	public CarroDTO(Carro c) {
		this.id = c.getId();
		this.nome = c.getNome();
		this.tipo = c.getTipo();
	}

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
