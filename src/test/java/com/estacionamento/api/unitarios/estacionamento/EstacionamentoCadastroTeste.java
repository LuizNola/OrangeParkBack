package com.estacionamento.api.unitarios.estacionamento;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

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
import com.estacionamento.api.service.estacionamento.CadEstacionamento;
import com.estacionamento.api.utils.DatabaseCleaner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@DisplayName("Cadastro de estacionamentos")
class EstacionamentoCadastroTeste {
	
	@Autowired
	private CadEstacionamento cadEstacionamento;
	
	@Autowired
	private DatabaseCleaner dbClear;
	
	@BeforeEach
	public void setUp() {
		dbClear.clearTables();
	}

	@Test()
	@DisplayName("Cadastrando estacionamento com sucesso")
	public void cadastrarEstacionamentoComSucesso() throws BussinesError {
	
		Estacionamento estacionamento = new Estacionamento();
		estacionamento.setCidade("Testelandia");
		estacionamento.setCnpj("00690247000187");
		estacionamento.setEndereco("Rua dos testes, 001");
		estacionamento.setNome("Testacionamento");
		estacionamento.setQtd_carros_max(40);
		estacionamento.setQtd_motos_max(40);
		estacionamento.setSenha("SenhaDeTesteSeguraEComplexa");
		estacionamento.setTelefone("73573");
		
	
		cadEstacionamento.execute(estacionamento);
		
	}
	
	@Test()
	@DisplayName("Falha ao cadastrar estacionamento com CNPJ Duplicado")
	public void cadastrarEstacionamentoFalha_CnpjDuplicado() throws BussinesError {
		
		Estacionamento estacionamento = new Estacionamento();	
		estacionamento.setCidade("Testelandia");
		estacionamento.setCnpj("00690247000187");
		estacionamento.setEndereco("Rua dos testes, 001");
		estacionamento.setNome("Testacionamento");
		estacionamento.setQtd_carros_max(40);
		estacionamento.setQtd_motos_max(40);
		estacionamento.setSenha("SenhaDeTesteSeguraEComplexa");
		estacionamento.setTelefone("73573");
		

		cadEstacionamento.execute(estacionamento);
		BussinesError e = assertThrows( BussinesError.class ,
				()->{
						cadEstacionamento.execute(estacionamento);
					});
		
		assertTrue(e.getMessage().contains("CNPJ já esta cadastrado"));
		
	}
	
	@Test()
	@DisplayName("Falha ao cadastrar estacionamento com CNPJ invalido")
	public void cadastrarEstacionamentoFalha_CnpjIvalido() throws BussinesError {

		Estacionamento estacionamento = new Estacionamento();
		estacionamento.setCidade("Testelandia");
		estacionamento.setCnpj("0069024700018");
		estacionamento.setEndereco("Rua dos testes, 001");
		estacionamento.setNome("Testacionamento");
		estacionamento.setQtd_carros_max(40);
		estacionamento.setQtd_motos_max(40);
		estacionamento.setSenha("SenhaDeTesteSeguraEComplexa");
		estacionamento.setTelefone("73573");
		
		ConstraintViolationException e = assertThrows( ConstraintViolationException.class ,
				()->{
						cadEstacionamento.execute(estacionamento);
					});
		
		assertTrue(e.getMessage().contains("número do registro de contribuinte corporativo brasileiro (CNPJ) inválido"));
		
		
	}

	
	

	
}


