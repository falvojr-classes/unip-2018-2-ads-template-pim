package br.unip.ads.pim.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.unip.ads.pim.model.Funcionario;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FuncionarioRepositoryTest {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Test
	public void incluirFuncionario() {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Venilton");
		funcionario.setEmail("falvojr@gmail.com");
		funcionario.setSenha("123456");
		funcionarioRepository.save(funcionario);
	}
}
