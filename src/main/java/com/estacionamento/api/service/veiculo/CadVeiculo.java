package com.estacionamento.api.service.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.AuthError;
import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.model.PerfilType;
import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.repository.VeiculoRepository;
import com.estacionamento.api.security.UserSS;
import com.estacionamento.api.service.ultils.UserLoginService;

@Service
public class CadVeiculo {
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@Autowired
	private UserLoginService userLoginServ;	
	
	public void execute(Veiculo veiculo, Long id) throws BussinesError, AuthError {
		
		UserSS user = userLoginServ.authenticated();
		
		if(user == null || !user.hasHole(PerfilType.ADMIN) && !id.equals(user.getId())) {
			throw new AuthError("Não autorizado");
		}
		
		Estacionamento estacionamento = estacionamentoRep.getById(id);
		
		switch (veiculo.getTipo()) {
		case Moto:
			if(estacionamento.getQtd_motos_atual() >= estacionamento.getQtd_motos_max()) {
				throw new BussinesError("Limite atingido");
			}
			estacionamento.setQtd_motos_atual(estacionamento.getQtd_motos_atual() + 1);
		break;
		
		case Carro:
			if(estacionamento.getQtd_carros_atual() >= estacionamento.getQtd_carros_max()) {
				throw new BussinesError("Limite atingido");
			}
			estacionamento.setQtd_carros_atual(estacionamento.getQtd_carros_atual() + 1);
		break;
		}
		
		
		veiculo.setEstacionamento(estacionamento);	
		
		estacionamentoRep.save(estacionamento);
		veiculoRep.save(veiculo);
	}
}