package com.estacionamento.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estacionamento.api.model.Estacionamento;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {

}
