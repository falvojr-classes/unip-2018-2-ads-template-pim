package br.unip.ads.pim.controller;

import java.net.URI;

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
import br.unip.ads.pim.service.ClienteService;
import io.swagger.annotations.Api;

@Api(tags = SwaggerConfig.TAG_CLIENTE)
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController extends BaseRestController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<Iterable<Cliente>> buscarTodos() {
		Iterable<Cliente> clientes = clienteService.buscarTodos();
		
		return ResponseEntity.ok().body(clientes);
	}

	@GetMapping("{id}")
	public ResponseEntity<Cliente> buscarUm(@PathVariable("id") Long id) {
		Cliente cliente = this.clienteService.buscarUm(id);
		
		return ResponseEntity.ok().body(cliente);
	}

	@PostMapping
	public ResponseEntity<Void> incluir(@RequestBody Cliente cliente) {
		this.clienteService.incluir(cliente);

		final URI uriCliente = super.criarUriPorId(cliente.getId());

		return ResponseEntity.created(uriCliente).build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		cliente.setId(id);
		this.clienteService.atualizar(cliente);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
		this.clienteService.remover(id);

		return ResponseEntity.ok().build();
	}
}
