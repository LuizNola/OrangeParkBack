package com.estacionamento.api.service.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.AuthError;
import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.model.PerfilType;
import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.model.VeiculoType;
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
			if(veiculoRep.countVeiculosOfEstacionamento(estacionamento.getId(), VeiculoType.Moto) >= estacionamento.getQtd_motos_max()) {
				throw new BussinesError("Limite atingido");
			}
		break;
		
		case Carro:
			if(veiculoRep.countVeiculosOfEstacionamento(estacionamento.getId(), VeiculoType.Carro) >= estacionamento.getQtd_carros_max()) {
				throw new BussinesError("Limite atingido");
			}
		break;
		}
		
		veiculo.setEstacionamento(estacionamento);
		veiculoRep.save(veiculo);
	}
}
