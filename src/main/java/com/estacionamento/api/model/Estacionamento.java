package com.estacionamento.api.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Estacionamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	@JsonIgnore	
	private Set<Integer> perfis = new HashSet<>();

	@Column(nullable = false)
	@NotNull(message = "Campo nome não pode ser vazio")
	private String nome;

	@Column(nullable = false)
	@CNPJ
	@NotNull(message = "Campo CNPJ não pode ser vazio")
	private String cnpj;
	
	@Column(nullable = false)
	@Size(min = 2, message = "A sua senha e curta de mais")
	@NotNull(message = "Campo senha não pode ser vazio")
	private String senha;
 
	@Column(nullable = false)
	@NotNull(message = "Campo cidade não pode ser vazio")
	private String cidade;

	@Column(nullable = false)
	@NotNull(message = "Campo endereço não pode ser vazio")
	private String endereco;
	
	@Column(nullable = false)
	@NotNull(message = "Campo telefone não pode ser vazio")
	private String telefone;

	@Column(nullable = false)
	@PositiveOrZero(message = "Quantidade de vagas de moto invalida")
	private Integer qtd_motos_max;
	
	@Column(nullable = false)
	@PositiveOrZero(message = "Quantidade de vagas de carros invalida")
	private Integer qtd_carros_max;


	public Set<PerfilType> getPerfis() {
		return perfis.stream().map(x -> PerfilType.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(PerfilType perfil) {
		perfis.add(perfil.getCod());
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getQtd_motos_max() {
		return qtd_motos_max;
	}

	public void setQtd_motos_max(Integer qtd_motos_max) {
		this.qtd_motos_max = qtd_motos_max;
	}

	public Integer getQtd_carros_max() {
		return qtd_carros_max;
	}

	public void setQtd_carros_max(Integer qtd_carros_max) {
		this.qtd_carros_max = qtd_carros_max;
	}


}
