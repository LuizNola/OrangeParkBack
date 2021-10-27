package com.estacionamento.api.unitarios.estacionamento;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

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
import com.estacionamento.api.model.dto.EstacionamentoPutDto;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.service.estacionamento.PutEstacionamento;
import com.estacionamento.api.utils.DatabaseCleaner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@DisplayName("Editando estacionamento")
class EstacionamentoEditarTeste {
	
	@Autowired
	private DatabaseCleaner dbClear;
	
	@Autowired
	private PutEstacionamento putEstacionamento;
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@BeforeEach
	public void setUp() {
		dbClear.clearTables();
		
		Estacionamento estacionamento = new Estacionamento();
		estacionamento.setCidade("Praia Grande");
		estacionamento.setCnpj("31473271000198");
		estacionamento.setEndereco("Endereço de teste 6022");
		estacionamento.setId(1L);
		estacionamento.setNome("Nome de teste");
		estacionamento.setQtd_carros_max(23);
		estacionamento.setQtd_motos_max(42);
		estacionamento.setSenha("TesteDeSenha");
		estacionamento.setTelefone("13996170869");
		estacionamentoRep.save(estacionamento);
		
		Veiculo veiculoCarro = new Veiculo();
		veiculoCarro.setCor("Cor Teste");
		veiculoCarro.setEstacionamento(estacionamento);
		veiculoCarro.setId(1L);
		veiculoCarro.setMarca("Marca teste");
		veiculoCarro.setModelo("Modelo de teste");
		veiculoCarro.setPlaca("AAA1111");
		veiculoCarro.setTipo(VeiculoType.Carro);
		
		Veiculo veiculoMoto = new Veiculo();
		veiculoMoto.setCor("Cor Teste");
		veiculoMoto.setEstacionamento(estacionamento);
		veiculoMoto.setId(1L);
		veiculoMoto.setMarca("Marca teste");
		veiculoMoto.setModelo("Modelo de teste");
		veiculoMoto.setPlaca("AAA1111");
		veiculoMoto.setTipo(VeiculoType.Carro);
	}
	
	@Test()
	@Transactional
	@DisplayName("Editar Limite de carros com sucesso")
	public void editarLimiteDeCarrosComSucesso() throws BussinesError {
		EstacionamentoPutDto alteracao = new EstacionamentoPutDto();
		alteracao.setQtd_max_carros(10);
		
		putEstacionamento.execute(alteracao, 1L);
		
		Estacionamento estacionamentoAlterado = estacionamentoRep.getById(1L);
		
		assertEquals(alteracao.getQtd_max_carros(), estacionamentoAlterado.getQtd_carros_max());
	}
	
	@Test()
	@Transactional
	@DisplayName("Editar limite de carros com falha, numero de carros maior que o de vagas")
	public void editarLimiteDeCarrosComFalha_numeroDeCarrosMaiorQueVagas() throws BussinesError {
		EstacionamentoPutDto alteracao = new EstacionamentoPutDto();
		alteracao.setQtd_max_carros(0);
			
		BussinesError e = assertThrows( BussinesError.class ,
				()->{
						putEstacionamento.execute(alteracao, 1L);
					});
		assertTrue(e.getMessage().contains("Limite de vagas inferior aos carros estacionados"));
	}
	
	@Test()
	@Transactional
	@DisplayName("editar limite de motos com sucesso")
	public void editarLimiteDeMotosComSucesso() throws BussinesError {
		EstacionamentoPutDto alteracao = new EstacionamentoPutDto();
		alteracao.setQtd_max_motos(10);
		
		putEstacionamento.execute(alteracao, 1L);
		
		Estacionamento estacionamentoAlterado = estacionamentoRep.getById(1L);
		
		assertEquals(alteracao.getQtd_max_motos(), estacionamentoAlterado.getQtd_motos_max());
	}
	
	@Test()
	@Transactional
	@DisplayName("Editar limite de motos com falha, numero de Motos maior que o de vagas")
	public void editarLimiteDeMotosComFalha_numeroDeMotosMaiorQueVagas() throws BussinesError {
		EstacionamentoPutDto alteracao = new EstacionamentoPutDto();
		alteracao.setQtd_max_motos(0);
		
		BussinesError e = assertThrows( BussinesError.class ,
				()->{
						putEstacionamento.execute(alteracao, 1L);
					});
		assertTrue(e.getMessage().contains("Limite de vagas inferior as motos estacionados"));
	}
	
	@Test()
	@Transactional
	@DisplayName("Editar Limite de motos com falha, numero de vagas negativo")
	public void editarLimiteDeMotosComFalha_NumeroNegativo() throws BussinesError {
		EstacionamentoPutDto alteracao = new EstacionamentoPutDto();
		alteracao.setQtd_max_motos(-10);
		
		BussinesError e = assertThrows( BussinesError.class ,
				()->{
						putEstacionamento.execute(alteracao, 1L);
					});
		assertTrue(e.getMessage().contains("Limite de vagas inferior as motos estacionados"));
	}
	
}
