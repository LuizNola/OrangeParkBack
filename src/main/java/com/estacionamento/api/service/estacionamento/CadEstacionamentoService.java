package com.estacionamento.api.service.estacionamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.repository.EstacionamentoRepository;

@Service
public class CadEstacionamentoService {
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	public void execute(Estacionamento estacionamento) {
		
		
		estacionamento.setQtd_carros_atual(0);
		estacionamento.setQtd_motos_atual(0);
		
		estacionamentoRep.save(estacionamento);
	}
}
