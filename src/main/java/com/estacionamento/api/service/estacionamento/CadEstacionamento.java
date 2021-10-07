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
		System.out.println("OLA1");
		Optional<Estacionamento> estacionamentoExiste = estacionamentoRep.FindByCnpj(estacionamento.getCnpj());
		System.out.println("OLA2");
		if(estacionamentoExiste.isPresent()) {
			System.out.println("OLA3");
			throw new BussinesError("Esse cnpj já esta cadastrado");
		} 	
		System.out.println("OLA4");
		estacionamento.setSenha(BCrypt.encode(estacionamento.getSenha()));
		System.out.println("OLA5");
		estacionamento.addPerfil(PerfilType.CLIENTE);
		
		estacionamentoRep.save(estacionamento);
	}
}
