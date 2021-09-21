package com.estacionamento.api.service.ultils;

import java.util.Optional;

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
	
		Optional<Estacionamento> estacionamento = estacionamentoRep.FindByCnpj(cnpj);

		if(estacionamento.get() == null) {
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		System.out.println("UserDetailsService: " + estacionamento.get().getId());
		return new UserSS(estacionamento.get().getId(), estacionamento.get().getCnpj(), estacionamento.get().getSenha(), estacionamento.get().getPerfis());
	}
	
}
