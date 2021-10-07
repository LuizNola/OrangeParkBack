package com.estacionamento.api.model.dto;

import com.estacionamento.api.model.Estacionamento;

public class GetEstacionamentoDto {
	
	private Long id;
	private String nome;
	private Integer qtd_motos_max;
	private Long qtd_motos_atual;
	private Integer qtd_carros_max;
	private Long qtd_carros_atual;

	
	public GetEstacionamentoDto(Estacionamento estacionamento, Long atualCarro, Long atualMoto) {
		this.id = estacionamento.getId();
		this.nome = estacionamento.getNome();
		this.qtd_motos_max = estacionamento.getQtd_motos_max();
		this.qtd_motos_atual = atualMoto;
		this.qtd_carros_max = estacionamento.getQtd_carros_max();
		this.qtd_carros_atual = atualCarro;
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


	public Long getQtd_motos_atual() {
		return qtd_motos_atual;
	}


	public void setQtd_motos_atual(Long qtd_motos_atual) {
		this.qtd_motos_atual = qtd_motos_atual;
	}


	public Integer getQtd_carros_max() {
		return qtd_carros_max;
	}


	public void setQtd_carros_max(Integer qtd_carros_max) {
		this.qtd_carros_max = qtd_carros_max;
	}


	public Long getQtd_carros_atual() {
		return qtd_carros_atual;
	}


	public void setQtd_carros_atual(Long qtd_carros_atual) {
		this.qtd_carros_atual = qtd_carros_atual;
	}
	
	
	
	
	
}
