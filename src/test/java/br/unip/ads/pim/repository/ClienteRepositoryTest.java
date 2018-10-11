package br.unip.ads.pim.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.unip.ads.pim.model.Cliente;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Test
	public void incluirCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Venilton");
		cliente.setEmail("falvojr@gmail.com");
		cliente.setSenha("123456");
		clienteRepository.save(cliente );
	}
}
