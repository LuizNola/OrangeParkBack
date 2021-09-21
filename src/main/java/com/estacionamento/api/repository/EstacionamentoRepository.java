package com.estacionamento.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estacionamento.api.model.Estacionamento;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {

	@Query("select i from Estacionamento i where i.cnpj = :cnpj")
	public  Optional<Estacionamento> FindByCnpj(String cnpj);


}
