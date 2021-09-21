package com.estacionamento.api.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamento.api.exceptions.AuthError;
import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.service.VeiculoService;

@RestController
@RequestMapping("/veiculo")
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
	public List<Veiculo> ListVeiculo(@PathVariable Long id) throws AuthError {
		return VeiculoServ.listVeiculo.execute(id);
	}
	
	@DeleteMapping("/{EstId}/{CarId}")
	public void DelVeiculo(@Valid @PathVariable Long EstId, @PathVariable Long CarId) throws BussinesError, AuthError {
		VeiculoServ.delVeiculo.execute(CarId, EstId);
	}
	
}
