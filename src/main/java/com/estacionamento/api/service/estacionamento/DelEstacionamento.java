package com.estacionamento.api.service.estacionamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.AuthError;
import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.PerfilType;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.security.UserSS;
import com.estacionamento.api.service.ultils.UserLoginService;

@Service
public class DelEstacionamento {
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@Autowired
	private UserLoginService userLoginServ;	
	
	public void execute(Long id) throws BussinesError, AuthError {
		
		UserSS user = userLoginServ.authenticated();
		
		if(user == null || !user.hasHole(PerfilType.ADMIN) && !id.equals(user.getId())) {
			throw new AuthError("Não autorizado");
		}
		
		try {
		estacionamentoRep.deleteById(id);
		}
		catch(Exception e) {
			throw new BussinesError("Erro ao deletar estacionamento");
		}
	}
}
