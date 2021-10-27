package com.estacionamento.api.unitarios.veiculo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.model.Veiculo;
import com.estacionamento.api.model.VeiculoType;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.repository.VeiculoRepository;
import com.estacionamento.api.service.veiculo.DelVeiculo;
import com.estacionamento.api.utils.DatabaseCleaner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@DisplayName("Deletar veiculo")
class VeiculoDeletarTeste {
	
	@Autowired
	private DatabaseCleaner dbClear;
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@Autowired
	private DelVeiculo delVeiculo;
	
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
		veiculo.setMarca("Marca");
		veiculo.setModelo("Modelo");
		veiculo.setPlaca("AAA1111");
		veiculo.setTipo(VeiculoType.Carro);
		veiculoRep.save(veiculo);
	}

	@Test
	@DisplayName("Deletar veiculo com sucesso")
	void DeletarEstacionamentoComSucesso() throws BussinesError {
		delVeiculo.execute(1L, 1L);
	}
	
	@Test
	@DisplayName("Deletar veiculo com falha, veiculo não existe")
	void DeletarEstacionamentoComFalha_VeiculoNãoExiste() throws BussinesError {
		BussinesError e = assertThrows(BussinesError.class, 
				()-> {
					delVeiculo.execute(2L, 1L);
				});
		
		assertTrue(e.getMessage().contains("Veiculo não encontrado"));
	}
	
	@Test
	@DisplayName("Deletar veiculo com falha, estacionamento não existe")
	void DeletarEstacionamentoComFalha_EstacionamentoNãoExiste() throws BussinesError {
		BussinesError e = assertThrows(BussinesError.class, 
				()-> {
					delVeiculo.execute(1L, 2L);
				});
		
		assertTrue(e.getMessage().contains("Veiculo não encontrado"));
	}

}
