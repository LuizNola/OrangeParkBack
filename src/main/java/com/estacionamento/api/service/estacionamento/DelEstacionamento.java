package com.estacionamento.api.service.estacionamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.repository.EstacionamentoRepository;

@Service
public class DelEstacionamento {
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	public void execute(Long id) throws BussinesError {
		try {
		estacionamentoRep.deleteById(id);
		}
		catch(Exception e) {
			throw new BussinesError("Erro ao deletar estacionamento");
		}
	}
}
