package com.estacionamento.api.service.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.repository.VeiculoRepository;
import com.estacionamento.api.service.ultils.CheckEstacionamento;

@Service
public class CadVeiculo {
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@Autowired
	private CheckEstacionamento CEstacionamento;
	
	public void execute(Veiculo veiculo, Long id) throws BussinesError {
		
		CEstacionamento.execute(id);
		
		Estacionamento estacionamento = estacionamentoRep.getById(id);

		switch (veiculo.getTipo()) {
		case Moto:
			if(estacionamento.getQtd_motos_atual() >= estacionamento.getQtd_motos_max()) {
				throw new BussinesError("Limite atingido");
			}
			estacionamento.setQtd_motos_atual(estacionamento.getQtd_motos_atual() + 1);
		break;
		
		case Carro:
			if(estacionamento.getQtd_carros_atual() >= estacionamento.getQtd_carros_max()) {
				throw new BussinesError("Limite atingido");
			}
			estacionamento.setQtd_carros_atual(estacionamento.getQtd_carros_atual() + 1);
		break;
		}
		
		
		veiculo.setEstacionamento(estacionamento);	
		
		estacionamentoRep.save(estacionamento);
		veiculoRep.save(veiculo);
	}
}
