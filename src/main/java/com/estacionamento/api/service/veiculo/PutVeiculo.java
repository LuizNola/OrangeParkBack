package com.estacionamento.api.service.veiculo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.model.dto.VeiculoPutDto;
import com.estacionamento.api.repository.VeiculoRepository;

@Service
public class PutVeiculo {
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	public void execute(Long idEst,Long idVeic,VeiculoPutDto veiculoPut) throws BussinesError {
		
		Optional<Veiculo> veiculo = veiculoRep.findByEstacionamentoAndId(idEst, idVeic);
		
		if(veiculo.isEmpty()) throw new BussinesError("Veiculo Não encontrado");
			
		if(veiculoPut.getModelo() != null) veiculo.get().setModelo(veiculoPut.getModelo());
		if(veiculoPut.getMarca() != null) veiculo.get().setMarca(veiculoPut.getMarca());
		if(veiculoPut.getCor() != null) veiculo.get().setCor(veiculoPut.getCor());
		if(veiculoPut.getPlaca() != null) veiculo.get().setPlaca(veiculoPut.getPlaca());
		
		veiculoRep.save(veiculo.get());
	}
}
