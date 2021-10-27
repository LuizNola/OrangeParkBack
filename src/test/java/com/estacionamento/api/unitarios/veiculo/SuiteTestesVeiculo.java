package com.estacionamento.api.unitarios.veiculo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ VeiculoCadastroTeste.class, VeiculoDeletarTeste.class, VeiculoEditarTeste.class,
		VeiculoListarTeste.class })
public class SuiteTestesVeiculo {

}
