package com.estacionamento.api.service.veiculo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.AuthError;
import com.estacionamento.api.model.PerfilType;
import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.repository.VeiculoRepository;
import com.estacionamento.api.security.UserSS;
import com.estacionamento.api.service.ultils.UserLoginService;

@Service
public class ListVeiculo {
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	@Autowired
	private UserLoginService userLoginServ;	
	
	public Page<Veiculo> execute(Long id, Pageable pageable) throws AuthError{
		UserSS user = userLoginServ.authenticated();
		if(user == null || !user.hasHole(PerfilType.ADMIN) && !id.equals(user.getId())) {
			throw new AuthError("Não autorizado");
		}
		
		return veiculoRep.findByEstacionamento(id, pageable);
	}
}
