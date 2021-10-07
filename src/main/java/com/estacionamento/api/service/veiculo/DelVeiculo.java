package com.estacionamento.api.service.veiculo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.AuthError;
import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.PerfilType;
import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.repository.VeiculoRepository;
import com.estacionamento.api.security.UserSS;
import com.estacionamento.api.service.ultils.UserLoginService;

@Service
public class DelVeiculo {
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	@Autowired
	private UserLoginService userLoginServ;	
	
	public void execute(Long IdVeiculo, Long IdEstacionamento) throws BussinesError, AuthError {
		
		UserSS user = userLoginServ.authenticated();	
		if(user == null || !user.hasHole(PerfilType.ADMIN) && !IdEstacionamento.equals(user.getId())) {
			throw new AuthError("Não autorizado");
		}
		
		Optional<Veiculo> veiculo = veiculoRep.findById(IdVeiculo);
		
		if(veiculo.isEmpty()) {
			throw new BussinesError("Veiculo não encontrado");
		}
		
		veiculoRep.delete(veiculo.get());
			
		
	}
}
