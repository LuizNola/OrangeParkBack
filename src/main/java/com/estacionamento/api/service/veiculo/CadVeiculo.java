package com.estacionamento.api.service.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.model.VeiculoType;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.repository.VeiculoRepository;

@Service
public class CadVeiculo {
	 
	@Autowired
	private VeiculoRepository veiculoRep;
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	public void execute(Veiculo veiculo, Long id) throws BussinesError {
		
		Estacionamento estacionamento = estacionamentoRep.getById(id);
		switch (veiculo.getTipo()) {
		case Moto:
			if(veiculoRep.countVeiculosOfEstacionamento(estacionamento.getId(), VeiculoType.Moto) >= estacionamento.getQtd_motos_max()) {
				throw new BussinesError("Limite atingido");
			}
		break;
		
		case Carro:
			if(veiculoRep.countVeiculosOfEstacionamento(estacionamento.getId(), VeiculoType.Carro) >= estacionamento.getQtd_carros_max()) {
				throw new BussinesError("Limite atingido");
			}
		break;
		}
		
		veiculo.setEstacionamento(estacionamento);
		veiculoRep.save(veiculo);
	}
}
