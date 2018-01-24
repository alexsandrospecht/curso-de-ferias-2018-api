package com.matera.cursoferias.domain;

import java.util.UUID;

public class Carro {

	private UUID id;
	private String cor;
	private String modelo;
	private String ano;
	private Double valor;
	
	public Carro() {
		super();
	}
	
	public Carro(UUID id, String cor, String modelo, String ano, Double valor) {
		super();
		this.id = id;
		this.cor = cor;
		this.modelo = modelo;
		this.ano = ano;
		this.valor = valor;
	}
	
	public UUID getId() {
		return id;
	}
	public String getCor() {
		return cor;
	}
	public String getModelo() {
		return modelo;
	}
	public String getAno() {
		return ano;
	}
	public Double getValor() {
		return valor;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
