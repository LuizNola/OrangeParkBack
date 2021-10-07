package com.estacionamento.api.service.estacionamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.AuthError;
import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.model.PerfilType;
import com.estacionamento.api.model.VeiculoType;
import com.estacionamento.api.model.dto.GetEstacionamentoDto;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.repository.VeiculoRepository;
import com.estacionamento.api.security.UserSS;
import com.estacionamento.api.service.ultils.UserLoginService;

@Service
public class GetEstacionamento {
	
	@Autowired
	private UserLoginService userLoginServ;
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	
	public GetEstacionamentoDto execute(Long id) throws AuthError {
		UserSS user = userLoginServ.authenticated();
		
		if(user == null || !user.hasHole(PerfilType.ADMIN) && !id.equals(user.getId())) {
			throw new AuthError("Não autorizado");
		}
		Estacionamento estacionamento = estacionamentoRep.getById(id);
		
		Long qtdAtualCarros = veiculoRep.countVeiculosOfEstacionamento(estacionamento.getId(), VeiculoType.Carro);
		Long qtdAtualMotos = veiculoRep.countVeiculosOfEstacionamento(estacionamento.getId(), VeiculoType.Moto);
		
		return new GetEstacionamentoDto(estacionamento, qtdAtualCarros, qtdAtualMotos);
	}
}
