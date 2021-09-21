package com.estacionamento.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.service.estacionamento.CadEstacionamento;
import com.estacionamento.api.service.estacionamento.DelEstacionamento;
import com.estacionamento.api.service.estacionamento.GetEstacionamento;
import com.estacionamento.api.service.estacionamento.PutEstacionamento;

@Service
public class EstacionamentoServices {
	
	@Autowired
	public GetEstacionamento getEstacionamento;
	
	@Autowired
	public CadEstacionamento cadEstacionamento;
	
	@Autowired
	public PutEstacionamento attEstacionamento;
	
	@Autowired
	public DelEstacionamento deleteEstacionamento;
}
