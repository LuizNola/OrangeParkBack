package com.estacionamento.api.service.veiculo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.AuthError;
import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.repository.VeiculoRepository;
import com.estacionamento.api.security.UserSS;
import com.estacionamento.api.service.ultils.UserLoginService;

@Service
public class DelVeiculo {
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@Autowired
	private UserLoginService userLoginServ;	
	
	public void execute(Long IdVeiculo, Long IdEstacionamento) throws BussinesError, AuthError {
		
		UserSS user = userLoginServ.authenticated();	
		if(user == null) {
			throw new AuthError("Não autorizado");
		}
		
		Estacionamento estacionamento = estacionamentoRep.getById(IdEstacionamento);
		Optional<Veiculo> veiculo = veiculoRep.findById(IdVeiculo);
		
		if(veiculo.isEmpty()) {
			throw new BussinesError("Veiculo não encontrado");
		}
		
		switch (veiculo.get().getTipo()) {
		case Carro:
			estacionamento.setQtd_carros_atual(estacionamento.getQtd_carros_atual() - 1);
			break;
			
		case Moto:
			estacionamento.setQtd_motos_atual(estacionamento.getQtd_motos_atual() - 1);
			break;

		default:
			throw new BussinesError("Tipo de veiculo invalido");
		}
		veiculoRep.delete(veiculo.get());
			
		
	}
}
