package com.estacionamento.api.unitarios.veiculo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

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
import com.estacionamento.api.service.veiculo.CadVeiculo;
import com.estacionamento.api.utils.DatabaseCleaner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@DisplayName("Cadastrar Veiculos")
class VeiculoCadastroTeste {
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@Autowired
	private CadVeiculo CadVeiculo;
	
	@Autowired
	private DatabaseCleaner dbClear;
	
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
	}

	@Test
	@Transactional
	@DisplayName("Cadastrar Veiculo com sucesso")
	void CadastrarVeiculoComSucesso() throws BussinesError {
		Veiculo veiculo = new Veiculo();
		veiculo.setCor("Azul");
		veiculo.setEstacionamento(estacionamentoRep.getById(1L));
		veiculo.setMarca("Marca");
		veiculo.setModelo("Modelo");
		veiculo.setPlaca("AAA1111");
		veiculo.setTipo(VeiculoType.Carro);
		
		CadVeiculo.execute(veiculo, 1L);
	}
	
	@Test
	@Transactional
	@DisplayName("Cadastrar veiculo com falha, Placa incorreta")
	void CadastrarVeiculoComFalha_placaComFormatoErrado() throws BussinesError {
		Veiculo veiculo = new Veiculo();
		veiculo.setCor("Azul");
		veiculo.setEstacionamento(estacionamentoRep.getById(1L));
		veiculo.setMarca("Marca");
		veiculo.setModelo("Modelo");
		veiculo.setPlaca("Ta errado pó");
		veiculo.setTipo(VeiculoType.Carro);
		
		ConstraintViolationException e = assertThrows(ConstraintViolationException.class, 
				()-> {
					CadVeiculo.execute(veiculo, 1L);
				});
		
		assertTrue(e.getMessage().contains("Placa necessita do padrão mercosul"));
	}
	
	@Test
	@Transactional
	@DisplayName("Cadastrar veiculo com falha, Campos nulos")
	void CadastrarVeiculoComFalha_CampoMarcaFaltando() throws BussinesError {
		Veiculo veiculo = new Veiculo();
		veiculo.setCor("Azul");
		veiculo.setEstacionamento(estacionamentoRep.getById(1L));
		veiculo.setMarca(null);
		veiculo.setModelo("Modelo");
		veiculo.setPlaca("AAA1111");
		veiculo.setTipo(VeiculoType.Carro);
		
		
		ConstraintViolationException e = assertThrows(ConstraintViolationException.class, 
				()-> {
					CadVeiculo.execute(veiculo, 1L);
				});
		
		assertTrue(e.getMessage().contains("Marca não pode ser nulo"));
	
	}
	
	@Test
	@Transactional
	@DisplayName("Cadastrar veiculos com falha, limite maximo atingido")
	void CadastrarVeiculoComFalha_LimiteAtingido() throws BussinesError {
		Veiculo veiculo = new Veiculo();
		veiculo.setCor("Azul");
		veiculo.setEstacionamento(estacionamentoRep.getById(1L));
		veiculo.setMarca("Marca");
		veiculo.setModelo("Modelo");
		veiculo.setPlaca("AAA1111");
		veiculo.setTipo(VeiculoType.Moto);
		
		CadVeiculo.execute(veiculo, 1L);
		BussinesError e = assertThrows(BussinesError.class, 
				()-> {
					CadVeiculo.execute(veiculo, 1L);
				});
		
		assertTrue(e.getMessage().contains("Limite atingido"));
	
	}

}
