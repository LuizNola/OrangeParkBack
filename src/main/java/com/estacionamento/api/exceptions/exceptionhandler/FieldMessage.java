package com.estacionamento.api.exceptions.exceptionhandler;

import java.io.Serializable;

public class FieldMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String NomeDoCampo;
	private String msg;
	
	public FieldMessage() {
		
	}
	
	public FieldMessage(String nomeDoCampo, String msg) {
		NomeDoCampo = nomeDoCampo;
		this.msg = msg;
	}

	public String getNomeDoCampo() {
		return NomeDoCampo;
	}

	public void setNomeDoCampo(String nomeDoCampo) {
		NomeDoCampo = nomeDoCampo;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
