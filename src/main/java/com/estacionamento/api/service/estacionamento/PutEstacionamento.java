package com.estacionamento.api.service.estacionamento;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.model.VeiculoType;
import com.estacionamento.api.model.dto.EstacionamentoPutDto;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.repository.VeiculoRepository;

@Service
public class PutEstacionamento {
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	public void execute(EstacionamentoPutDto Estacionamentoput, Long id) throws BussinesError {
		
		Optional<Estacionamento> estacionamentoAtual = Optional.of(estacionamentoRep.getById(id));
		if(!estacionamentoAtual.isPresent()) {
			return;
		}
		

		if(Estacionamentoput.getQtd_max_carros() != null ) {
		   if(veiculoRep.countVeiculosOfEstacionamento(estacionamentoAtual.get().getId(), VeiculoType.Carro) 
				   >= Estacionamentoput.getQtd_max_carros()) 
			   throw new BussinesError("Limite de vagas inferior aos carros estacionados");
		   
			estacionamentoAtual.get().setQtd_carros_max(Estacionamentoput.getQtd_max_carros());
		}
		if(Estacionamentoput.getQtd_max_motos() != null ) { 
			
		   if(veiculoRep.countVeiculosOfEstacionamento(estacionamentoAtual.get().getId(), VeiculoType.Carro)
				   >= Estacionamentoput.getQtd_max_motos())
			   throw new BussinesError("Limite de vagas inferior as motos estacionados");
			   
		   estacionamentoAtual.get().setQtd_motos_max(Estacionamentoput.getQtd_max_motos());
		}
		
		estacionamentoRep.save(estacionamentoAtual.get());
		
	}
}
