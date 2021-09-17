package com.estacionamento.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.estacionamento.api.model.Veiculo;

public interface VeiculoRepository  extends JpaRepository<Veiculo, Long>{

		@Query("select i from Veiculo i where i.estacionamento.id = :id")
	public List<Veiculo> findByEstacionamento(Long id);
	
}
