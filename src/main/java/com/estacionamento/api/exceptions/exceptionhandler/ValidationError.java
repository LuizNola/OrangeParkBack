package com.estacionamento.api.exceptions.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> ListaDeErros = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getListaDeErros() {
		return ListaDeErros;
	}

	public void adicionaErro(String nome, String msg) {
		ListaDeErros.add(new FieldMessage(nome, msg));
	}
	
}
