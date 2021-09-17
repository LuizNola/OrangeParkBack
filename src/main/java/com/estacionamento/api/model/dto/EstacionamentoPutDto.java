package com.estacionamento.api.model.dto;

import javax.validation.constraints.PositiveOrZero;

public class EstacionamentoPutDto {
	
	@PositiveOrZero
	private Integer qtd_max_motos;
	
	@PositiveOrZero
	private Integer qtd_max_carros;
	
	
	public Integer getQtd_max_motos() {
		return qtd_max_motos;
	}
	public void setQtd_max_motos(Integer qtd_max_motos) {
		this.qtd_max_motos = qtd_max_motos;
	}
	public Integer getQtd_max_carros() {
		return qtd_max_carros;
	}
	public void setQtd_max_carros(Integer qtd_max_carros) {
		this.qtd_max_carros = qtd_max_carros;
	}
}
