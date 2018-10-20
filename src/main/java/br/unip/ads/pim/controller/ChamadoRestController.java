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
import br.unip.ads.pim.model.Chamado;
import br.unip.ads.pim.service.ChamadoService;
import io.swagger.annotations.Api;

@Api(tags = SwaggerConfig.TAG_CHAMADO)
@RestController
@RequestMapping("/api/chamados")
public class ChamadoRestController extends BaseRestController {

	@Autowired
	private ChamadoService chamadoService;

	@GetMapping
	public ResponseEntity<Iterable<Chamado>> buscarTodos() {
		Iterable<Chamado> chamados = chamadoService.buscarTodos();

		return ResponseEntity.ok().body(chamados);
	}

	@GetMapping("{id}")
	public ResponseEntity<Chamado> buscarUm(@PathVariable("id") Long id) {
		Chamado chamado = this.chamadoService.buscarUm(id);

		return ResponseEntity.ok().body(chamado);
	}

	@PostMapping
	public ResponseEntity<Void> incluir(@RequestBody Chamado chamado) {
		this.chamadoService.incluir(chamado);

		final URI uriChamado = super.criarUriPorId(chamado.getId());

		return ResponseEntity.created(uriChamado).build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody Chamado chamado) {
		chamado.setId(id);
		this.chamadoService.atualizar(chamado);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
		this.chamadoService.remover(id);

		return ResponseEntity.ok().build();
	}
}
