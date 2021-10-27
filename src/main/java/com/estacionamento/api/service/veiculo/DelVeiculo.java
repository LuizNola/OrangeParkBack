package com.estacionamento.api.service.veiculo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.repository.VeiculoRepository;

@Service
public class DelVeiculo {
	
	@Autowired
	private VeiculoRepository veiculoRep;

	public void execute(Long IdVeiculo, Long idEst) throws BussinesError {
		
		Optional<Veiculo> veiculo = veiculoRep.findByEstacionamentoAndId(idEst, IdVeiculo);
		
		if(veiculo.isEmpty()) throw new BussinesError("Veiculo não encontrado");
		
		veiculoRep.delete(veiculo.get());
	}
}
