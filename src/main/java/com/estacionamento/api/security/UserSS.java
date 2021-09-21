package com.estacionamento.api.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.estacionamento.api.model.PerfilType;

public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cnpj;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSS() {
		
	}
	
	public UserSS(Long id, String cnpj, String senha, Set<PerfilType> perfis) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
		System.out.println(this.id +" " +" "+ " "+ this.cnpj +" "+ " "+ this.senha+ " "+ this.authorities);
		
	}

	public Long getId() {
		return id; 
	} 
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return cnpj;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean hasHole(PerfilType perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}

}
