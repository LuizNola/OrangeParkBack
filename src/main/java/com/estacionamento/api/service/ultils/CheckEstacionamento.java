package com.estacionamento.api.service.ultils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.repository.EstacionamentoRepository;

@Service	
public class CheckEstacionamento {
	
	@Autowired
	EstacionamentoRepository estacionamentoRep;
	
	public void execute(Long id) throws BussinesError {
		Optional<Estacionamento> estacionamento = estacionamentoRep.findById(id);
		
		if(!estacionamento.isPresent()) {
			throw new BussinesError("Estacionamento não encontrado");
		}
	}
}
