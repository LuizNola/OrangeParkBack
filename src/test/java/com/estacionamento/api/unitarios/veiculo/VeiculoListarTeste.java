package com.estacionamento.api.unitarios.veiculo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.model.VeiculoType;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.repository.VeiculoRepository;
import com.estacionamento.api.service.veiculo.ListVeiculo;
import com.estacionamento.api.utils.DatabaseCleaner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@DisplayName("Listar Veiculos")
class VeiculoListarTeste {
	
	@Autowired
	private DatabaseCleaner dbClear;
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@Autowired
	private ListVeiculo listVeiculo;
	

	@BeforeEach
	public void setUp() {
		dbClear.clearTables();
		
		Estacionamento estacionamento = new Estacionamento();
		estacionamento.setCidade("Praia Grande");
		estacionamento.setCnpj("31473271000198");
		estacionamento.setEndereco("Endereço de teste 6022");
		estacionamento.setId(1L);
		estacionamento.setNome("Nome de teste");
		estacionamento.setQtd_carros_max(10);
		estacionamento.setQtd_motos_max(1);
		estacionamento.setSenha("TesteDeSenha");
		estacionamento.setTelefone("13996170869");
		estacionamentoRep.save(estacionamento);
		
		Veiculo veiculo = new Veiculo();
		veiculo.setCor("Azul");
		veiculo.setId(1L);
		veiculo.setEstacionamento(estacionamentoRep.getById(1L));
		veiculo.setMarca("Marca Primaria");
		veiculo.setModelo("Modelo Primario");
		veiculo.setPlaca("BBB2222");
		veiculo.setTipo(VeiculoType.Carro);
		veiculoRep.save(veiculo);
		
	}

	@Test
	@DisplayName("Listar Veiculos com sucesso")
	void ListarVeiculosComSucesso() {
		Pageable pageable = null;
		Page<Veiculo> result = listVeiculo.execute(1L,  pageable);
		
		assertEquals(1, result.getSize()); 
	}
	
	@Test
	@DisplayName("Listar Veiculos com falha, Estacionamento não existe")
	void ListarVeiculosComFalha_estacionamentoNãoExiste() {
		Pageable pageable = null;
		Page<Veiculo> result = listVeiculo.execute(2L,  pageable);
		
		assertEquals(0, result.getSize()); 
	}


}
