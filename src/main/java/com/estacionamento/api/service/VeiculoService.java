package com.estacionamento.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.api.service.veiculo.CadVeiculo;
import com.estacionamento.api.service.veiculo.DelVeiculo;
import com.estacionamento.api.service.veiculo.ListVeiculo;
import com.estacionamento.api.service.veiculo.PutVeiculo;

@Service
public class VeiculoService {
	
	@Autowired
	public ListVeiculo listVeiculo;
	
	@Autowired
	public CadVeiculo cadVeiculo;
	
	@Autowired
	public PutVeiculo putVeiculo;
	
	@Autowired 
	public DelVeiculo delVeiculo;
}
