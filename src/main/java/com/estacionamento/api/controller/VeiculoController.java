package com.estacionamento.api.controller;



import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamento.api.exceptions.AuthError;
import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.model.dto.VeiculoPutDto;
import com.estacionamento.api.service.VeiculoService;

@RestController
@RequestMapping("/veiculo")
@CrossOrigin(origins = "*")
public class VeiculoController  {
	
	@Autowired
	private VeiculoService VeiculoServ;
	
	@Transactional
	@PostMapping("/{id}") 
	@ResponseStatus(code = HttpStatus.CREATED)
	public void CreateVeiculo(@Valid @RequestBody Veiculo veiculo, @PathVariable Long id) throws BussinesError, AuthError {
		VeiculoServ.cadVeiculo.execute(veiculo, id);
	}
	
	@GetMapping("/{id}")
	public Page<Veiculo> ListVeiculo(@PathVariable Long id, @PageableDefault(
			sort = "placa",
            direction = Sort.Direction.ASC,
            page = 0, 
            size = 10) Pageable pageable) throws AuthError {
		return VeiculoServ.listVeiculo.execute(id, pageable);
	}
	
	@DeleteMapping("/{EstId}/{CarId}")
	public void DelVeiculo(@Valid @PathVariable Long EstId, @PathVariable Long CarId) throws BussinesError, AuthError {
		VeiculoServ.delVeiculo.execute(CarId, EstId);
	}
	
	@Transactional
	@PutMapping("/{EstId}/{CarId}")
	public void PutVeiculo(@Valid @PathVariable Long EstId, 
			@PathVariable Long CarId,
			@Valid @RequestBody VeiculoPutDto veiculoPut) throws BussinesError, AuthError {
		VeiculoServ.putVeiculo.execute(EstId ,CarId, veiculoPut);
	}
	
}
