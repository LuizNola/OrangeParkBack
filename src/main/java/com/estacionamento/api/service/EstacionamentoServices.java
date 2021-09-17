package com.estacionamento.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.service.estacionamento.CadEstacionamentoService;
import com.estacionamento.api.service.estacionamento.DelEstacionamento;
import com.estacionamento.api.service.estacionamento.PutEstacionamento;

@Service
public class EstacionamentoServices {
	
	@Autowired
	public CadEstacionamentoService cadEstacionamento;
	
	@Autowired
	public PutEstacionamento attEstacionamento;
	
	@Autowired
	public DelEstacionamento deleteEstacionamento;
}
