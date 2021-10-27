package com.estacionamento.api.unitarios.estacionamento;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.estacionamento.api.exceptions.AuthError;
import com.estacionamento.api.exceptions.BussinesError;
import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.service.estacionamento.DelEstacionamento;
import com.estacionamento.api.utils.DatabaseCleaner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@DisplayName("Deletando estacionamentos")
class EstacionamentoDeletarTeste {
	
	@Autowired
	private DatabaseCleaner dbClear;
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@Autowired
	private DelEstacionamento delEstacionamento;
	
	@BeforeEach
	public void setUp() {
		dbClear.clearTables();
	}

	@Test()
	@DisplayName("Deletando estacionamento com sucesso")
	public void DeletarComSucesso() throws BussinesError, AuthError {
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
		
		delEstacionamento.execute(1L);
	}
	
	@Test()
	@DisplayName("Falha ao deletar estacionamento que não existe")
	public void DeletarComFalha_NãoExisteOEstacionamento() throws BussinesError, AuthError {
		//sem estacionamento 
		
		BussinesError e = assertThrows( BussinesError.class ,
				()->{
					delEstacionamento.execute(1L);
					});
		System.out.println(e.getMessage());
		assertTrue(e.getMessage().contains("Erro ao deletar estacionamento"));
	}
	
	
}
