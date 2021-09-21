package com.estacionamento.api.service.estacionamento;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.model.PerfilType;
import com.estacionamento.api.repository.EstacionamentoRepository;

@Service
public class CadEstacionamento {
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@Autowired
	private BCryptPasswordEncoder BCrypt;
	
	public void execute(Estacionamento estacionamento) throws BussinesError {
		
		Optional<Estacionamento> estacionamentoExiste = estacionamentoRep.FindByCnpj(estacionamento.getCnpj());
		
		if(estacionamentoExiste.isPresent()) {
			throw new BussinesError("Esse cnpj já esta cadastrado");
		}
		
		estacionamento.setSenha(BCrypt.encode(estacionamento.getSenha()));
		
		estacionamento.addPerfil(PerfilType.CLIENTE);
		
		estacionamento.setQtd_carros_atual(0);
		estacionamento.setQtd_motos_atual(0);
		
		estacionamentoRep.save(estacionamento);
	}
}
