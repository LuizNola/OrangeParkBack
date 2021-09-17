package com.estacionamento.api.service.veiculo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.repository.VeiculoRepository;

@Service
public class ListVeiculo {
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	public List<Veiculo> execute(Long id){
		return veiculoRep.findByEstacionamento(id);
	}
}
