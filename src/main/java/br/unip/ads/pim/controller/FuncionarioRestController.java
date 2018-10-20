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
import br.unip.ads.pim.model.Funcionario;
import br.unip.ads.pim.service.FuncionarioService;
import io.swagger.annotations.Api;

@Api(tags = SwaggerConfig.TAG_FUNCIONARIO)
@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioRestController extends BaseRestController {

	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping
	public ResponseEntity<Iterable<Funcionario>> buscarTodos() {
		Iterable<Funcionario> funcionarios = funcionarioService.buscarTodos();

		return ResponseEntity.ok().body(funcionarios);
	}

	@GetMapping("{id}")
	public ResponseEntity<Funcionario> buscarUm(@PathVariable("id") Long id) {
		Funcionario funcionario = this.funcionarioService.buscarUm(id);

		return ResponseEntity.ok().body(funcionario);
	}

	@PostMapping
	public ResponseEntity<Void> incluir(@RequestBody Funcionario funcionario) {
		this.funcionarioService.incluir(funcionario);

		final URI uriFuncionario = super.criarUriPorId(funcionario.getId());

		return ResponseEntity.created(uriFuncionario).build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody Funcionario funcionario) {
		funcionario.setId(id);
		this.funcionarioService.atualizar(funcionario);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
		this.funcionarioService.remover(id);

		return ResponseEntity.ok().build();
	}
}
