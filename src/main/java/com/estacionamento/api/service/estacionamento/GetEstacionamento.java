package com.estacionamento.api.service.estacionamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.model.VeiculoType;
import com.estacionamento.api.model.dto.GetEstacionamentoDto;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.repository.VeiculoRepository;

@Service
public class GetEstacionamento {
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	public GetEstacionamentoDto execute(Long id) {
		Estacionamento estacionamento = estacionamentoRep.getById(id);
		
		Long qtdAtualCarros = veiculoRep.countVeiculosOfEstacionamento(estacionamento.getId(), VeiculoType.Carro);
		Long qtdAtualMotos = veiculoRep.countVeiculosOfEstacionamento(estacionamento.getId(), VeiculoType.Moto);
		
		return new GetEstacionamentoDto(estacionamento, qtdAtualCarros, qtdAtualMotos);
	}
}
