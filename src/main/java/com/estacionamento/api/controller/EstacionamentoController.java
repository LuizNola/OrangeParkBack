package com.estacionamento.api.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.model.dto.EstacionamentoPutDto;
import com.estacionamento.api.service.EstacionamentoServices;

@RestController
@RequestMapping("/estacionameto")
public class EstacionamentoController {
	
	@Autowired
	private EstacionamentoServices estacionamentoServ;
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public void CreateEstacionamento(@Valid @RequestBody Estacionamento estacionamento) {
		estacionamentoServ.cadEstacionamento.execute(estacionamento);
	}
	
	@GetMapping("/{id}")
	public Estacionamento ListEstacionamento(@PathVariable Long id) throws AuthError {
		return estacionamentoServ.getEstacionamento.execute(id);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public void UpdateEstacionamento(@Valid @RequestBody EstacionamentoPutDto estacionamentoPut, @PathVariable Long id ) throws BussinesError, AuthError {
		estacionamentoServ.attEstacionamento.execute(estacionamentoPut, id);
	}
	
	@DeleteMapping("/{id}")
	public void DelEstacionamento(@Valid @PathVariable Long id) throws BussinesError, AuthError {
		estacionamentoServ.deleteEstacionamento.execute(id);
	}
	
	
}
