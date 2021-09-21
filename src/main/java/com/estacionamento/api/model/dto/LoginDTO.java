package com.estacionamento.api.model.dto;


public class LoginDTO {
	private String cnpj;
	private String senha;
	public String getCnpj() {
		return cnpj;
	}
	
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
