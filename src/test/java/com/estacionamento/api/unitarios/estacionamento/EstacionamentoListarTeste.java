package com.estacionamento.api.unitarios.estacionamento;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.estacionamento.api.model.Estacionamento;
import com.estacionamento.api.model.dto.GetEstacionamentoDto;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.service.estacionamento.GetEstacionamento;
import com.estacionamento.api.utils.DatabaseCleaner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@DisplayName("Listar Estacionamento")
class EstacionamentoListarTeste {

	@Autowired
	private DatabaseCleaner dbClear;
	
	@Autowired 
	private GetEstacionamento getEstacionamento;
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@BeforeEach
	public void setUp() {
		dbClear.clearTables();
	}
	
	@Test
	@Transactional
	@DisplayName("Pegar estacionamento com sucesso")
	public void PegarEstacionamentoComSucesso(){
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
		
		GetEstacionamentoDto gettedEstacionamento = getEstacionamento.execute(1L);
		
		assertThat(gettedEstacionamento.getId(), is(equalTo(1L)));
		assertThat(gettedEstacionamento.getNome(), is(equalTo("Nome de teste")));
		assertThat(gettedEstacionamento.getQtd_carros_max(), is(equalTo(23)));
		assertThat(gettedEstacionamento.getQtd_motos_max(), is(equalTo(42)));
		
	}
	
	
}
