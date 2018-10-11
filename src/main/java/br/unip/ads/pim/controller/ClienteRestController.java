package br.unip.ads.pim.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unip.ads.pim.config.SwaggerConfig;
import br.unip.ads.pim.model.Cliente;
import br.unip.ads.pim.repository.ClienteRepository;
import io.swagger.annotations.Api;

@Api(tags = SwaggerConfig.TAG_CLIENTE)
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController extends BaseRestController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public ResponseEntity<Iterable<Cliente>> buscarTodos() {
		Iterable<Cliente> clientes = clienteRepository.findAll();
		
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("{id}")
	public ResponseEntity<Cliente> buscarUm(@PathVariable("id") Long id) {
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		
		return ResponseEntity.ok(cliente.get());
	}

	@PostMapping
	public ResponseEntity<Void> incluir(@RequestBody Cliente cliente) {
		this.clienteRepository.save(cliente);

		final URI uriCliente = super.criarUriPorId(cliente.getId());

		return ResponseEntity.created(uriCliente).build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		cliente.setId(id);
		this.clienteRepository.save(cliente);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
		this.clienteRepository.deleteById(id);

		return ResponseEntity.ok().build();
	}
}
