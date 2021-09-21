package com.estacionamento.api.model.dto;

public class EstacionamentoListDto {
	
	private Long id;
	private String nome;
	private Integer qtd_motos_max;
	private Integer qtd_motos_atual;
	private Integer qtd_carros_max;
	private Integer qtd_carros_atual;
	
	
	public EstacionamentoListDto(Long id, String nome, Integer qtd_motos_max, Integer qtd_motos_atual,
			Integer qtd_carros_max, Integer qtd_carros_atual) {
		this.id = id;
		this.nome = nome;
		this.qtd_motos_max = qtd_motos_max;
		this.qtd_motos_atual = qtd_motos_atual;
		this.qtd_carros_max = qtd_carros_max;
		this.qtd_carros_atual = qtd_carros_atual;
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


	public Integer getQtd_motos_max() {
		return qtd_motos_max;
	}


	public void setQtd_motos_max(Integer qtd_motos_max) {
		this.qtd_motos_max = qtd_motos_max;
	}


	public Integer getQtd_motos_atual() {
		return qtd_motos_atual;
	}


	public void setQtd_motos_atual(Integer qtd_motos_atual) {
		this.qtd_motos_atual = qtd_motos_atual;
	}


	public Integer getQtd_carros_max() {
		return qtd_carros_max;
	}


	public void setQtd_carros_max(Integer qtd_carros_max) {
		this.qtd_carros_max = qtd_carros_max;
	}


	public Integer getQtd_carros_atual() {
		return qtd_carros_atual;
	}


	public void setQtd_carros_atual(Integer qtd_carros_atual) {
		this.qtd_carros_atual = qtd_carros_atual;
	}
	
	
	
	
	
}
