package com.estacionamento.api.service.ultils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.AuthError;
import com.estacionamento.api.model.PerfilType;
import com.estacionamento.api.security.UserSS;

@Service
public class ValidUser {

	@Autowired
	private UserLoginService userLoginServ;	
	
	public void valid(Long id) throws AuthError {
		
		UserSS user = userLoginServ.authenticated();
		
		if(user == null || !user.hasHole(PerfilType.ADMIN) && !id.equals(user.getId())) {
			throw new AuthError("Não autorizado");
		}
	}
}
