package com.estacionamento.api.service.veiculo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.exceptions.AuthError;
import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.PerfilType;
import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.model.dto.VeiculoPutDto;
import com.estacionamento.api.repository.VeiculoRepository;
import com.estacionamento.api.security.UserSS;
import com.estacionamento.api.service.ultils.UserLoginService;

@Service
public class PutVeiculo {
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	@Autowired
	private UserLoginService userLoginServ;	

	public void execute(Long IdEstacionamento,Long idVeic,VeiculoPutDto veiculoPut) throws BussinesError, AuthError {
		
		UserSS user = userLoginServ.authenticated();	
		if(user == null || !user.hasHole(PerfilType.ADMIN) && !IdEstacionamento.equals(user.getId())) {
			throw new AuthError("Não autorizado");
		}
		
		
		Optional<Veiculo> veiculo = veiculoRep.findById(idVeic);
		
		if(veiculo.isEmpty()) throw new BussinesError("Veiculo Não encontrado");
			
		if(veiculoPut.getModelo() != null) veiculo.get().setModelo(veiculoPut.getModelo());
		if(veiculoPut.getMarca() != null) veiculo.get().setMarca(veiculoPut.getMarca());
		if(veiculoPut.getCor() != null) veiculo.get().setCor(veiculoPut.getCor());
		if(veiculoPut.getPlaca() != null) veiculo.get().setPlaca(veiculoPut.getPlaca());
		
		veiculoRep.save(veiculo.get());
	}
}
