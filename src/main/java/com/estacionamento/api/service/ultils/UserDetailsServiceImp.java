package com.estacionamento.api.service.ultils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.security.UserSS;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

	@Autowired
	private EstacionamentoRepository estacionamentoRep;

	@Override 
	public UserDetails loadUserByUsername(String cnpj) throws UsernameNotFoundException {
	
		Estacionamento estacionamento = estacionamentoRep.FindByCnpj(cnpj);

		if(estacionamento == null) {
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		System.out.println("UserDetailsService: " + estacionamento.getId());
		return new UserSS(estacionamento.getId(), estacionamento.getCnpj(), estacionamento.getSenha(), estacionamento.getPerfis());
	}
	
}
