package com.estacionamento.api.model;


public enum PerfilType {
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");

	private int cod;
	private String descricao;
	
	PerfilType(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static PerfilType toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for (PerfilType x: PerfilType.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		return null;
		
	}
	
	
}
