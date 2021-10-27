package com.estacionamento.api.service.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.repository.VeiculoRepository;

@Service
public class ListVeiculo {
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	public Page<Veiculo> execute(Long id, Pageable pageable){
		return veiculoRep.findByEstacionamento(id, pageable);
	}
}
