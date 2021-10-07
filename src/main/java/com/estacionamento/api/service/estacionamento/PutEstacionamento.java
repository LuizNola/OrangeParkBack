package com.estacionamento.api.service.estacionamento;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.AuthError;
import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.model.PerfilType;
import com.estacionamento.api.model.VeiculoType;
import com.estacionamento.api.model.dto.EstacionamentoPutDto;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.repository.VeiculoRepository;
import com.estacionamento.api.security.UserSS;
import com.estacionamento.api.service.ultils.UserLoginService;

@Service
public class PutEstacionamento {
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@Autowired
	private UserLoginService userLoginServ;	
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	public void execute(EstacionamentoPutDto Estacionamentoput, Long id) throws BussinesError, AuthError {
		
		UserSS user = userLoginServ.authenticated();
		
		if(user == null || !user.hasHole(PerfilType.ADMIN) && !id.equals(user.getId())) {
			throw new AuthError("Não autorizado");
		}
		
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
