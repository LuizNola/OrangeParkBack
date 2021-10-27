package com.estacionamento.api.unitarios.veiculo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import com.estacionamento.api.model.dto.VeiculoPutDto;
import com.estacionamento.api.repository.EstacionamentoRepository;
import com.estacionamento.api.repository.VeiculoRepository;
import com.estacionamento.api.service.veiculo.PutVeiculo;
import com.estacionamento.api.utils.DatabaseCleaner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@DisplayName("Editar Veiculo")
class VeiculoEditarTeste {
	
	@Autowired
	private DatabaseCleaner dbClear;
	
	@Autowired
	private VeiculoRepository veiculoRep;
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@Autowired
	private PutVeiculo putVeiculo;;
	
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
	@Transactional
	@DisplayName("Editar veiculo com sucesso, todos os campos")
	void editarVeiculoComSucesso_todosOsCampos() throws BussinesError {
		VeiculoPutDto veiculoPut = new VeiculoPutDto();
		veiculoPut.setCor("Cor");
		veiculoPut.setMarca("Marca");
		veiculoPut.setModelo("Modelo");
		veiculoPut.setPlaca("AAA1111");
		
		putVeiculo.execute(1L, 1L, veiculoPut);
		
		assertEquals(veiculoPut.getCor(), veiculoRep.getById(1L).getCor());
		assertEquals(veiculoPut.getMarca(), veiculoRep.getById(1L).getMarca());
		assertEquals(veiculoPut.getModelo(), veiculoRep.getById(1L).getModelo());
		assertEquals(veiculoPut.getPlaca(), veiculoRep.getById(1L).getPlaca());
	}
	
	@Test
	@Transactional
	@DisplayName("Editar veiculo com sucesso, apenas a placa")
	void editarVeiculoComSucesso_apenasPlaca() throws BussinesError {
		VeiculoPutDto veiculoPut = new VeiculoPutDto();
		veiculoPut.setPlaca("AAA1111");
		
		putVeiculo.execute(1L, 1L, veiculoPut);
		
		assertNotEquals(veiculoPut.getCor(), veiculoRep.getById(1L).getCor());
		assertNotEquals(veiculoPut.getMarca(), veiculoRep.getById(1L).getMarca());
		assertNotEquals(veiculoPut.getModelo(), veiculoRep.getById(1L).getModelo());
		
		assertEquals(veiculoPut.getPlaca(), veiculoRep.getById(1L).getPlaca());
		
	}
	
	@Test
	@Transactional
	@DisplayName("Editar veiculo com falha, veiculo não existe")
	void editarVeiculoComFalha_VeiculoNaoExiste() throws BussinesError {
		VeiculoPutDto veiculoPut = new VeiculoPutDto();
		veiculoPut.setPlaca("AAA1111");
		
		BussinesError e = assertThrows(BussinesError.class, 
				()-> {
					putVeiculo.execute(1L, 2L, veiculoPut);
				});
		
		assertTrue(e.getMessage().contains("Veiculo Não encontrado"));
	}

}
