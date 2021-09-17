package com.estacionamento.api.service.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.repository.VeiculoRepository;

@Service
public class DelVeiculo {
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	public void execute(Long IdVeiculo, Long IdEstacionamento) throws BussinesError {
		
		Estacionamento estacionamento = estacionamentoRep.getById(IdEstacionamento);
		Veiculo veiculo = veiculoRep.getById(IdVeiculo);
		
		switch (veiculo.getTipo()) {
		case Carro:
			estacionamento.setQtd_carros_atual(estacionamento.getQtd_carros_atual() - 1);
			break;
			
		case Moto:
			estacionamento.setQtd_motos_atual(estacionamento.getQtd_motos_atual() - 1);
			break;

		default:
			throw new BussinesError("Tipo de veiculo invalido");
		}
		veiculoRep.delete(veiculo);
			
		
	}
}
