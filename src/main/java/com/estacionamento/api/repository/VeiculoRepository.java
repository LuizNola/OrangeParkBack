package com.estacionamento.api.repository;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.model.VeiculoType;

@Repository
public interface VeiculoRepository  extends JpaRepository<Veiculo, Long>{

	@Query("select i from Veiculo i where i.estacionamento.id = :id")
	Page<Veiculo> findByEstacionamento(Long id, Pageable pageable);
	
	@Query("select i from Veiculo i where i.estacionamento.id = :idEst and i.id = :idVeic")
	Optional<Veiculo> findByEstacionamentoAndId(Long idEst, Long idVeic);
	
	@Query("SELECT count(*) FROM Veiculo i where  i.estacionamento.id = :id and i.tipo = :veiculoType")
	Long countVeiculosOfEstacionamento(Long id, VeiculoType veiculoType);
	

}
