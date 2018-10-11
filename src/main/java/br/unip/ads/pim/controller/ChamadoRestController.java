package br.unip.ads.pim.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import br.unip.ads.pim.repository.ChamadoRepository;
import io.swagger.annotations.Api;

@Api(tags = SwaggerConfig.TAG_CHAMADO)
@RestController
@RequestMapping("/api/chamados")
public class ChamadoRestController extends BaseRestController {

	@Autowired
	private ChamadoRepository chamadoRepository;

	@GetMapping
	public ResponseEntity<Iterable<Chamado>> buscarTodos() {
		Iterable<Chamado> chamados = chamadoRepository.findAll();

		return ResponseEntity.ok(chamados);
	}

	@GetMapping("{id}")
	public ResponseEntity<Chamado> buscarUm(@PathVariable("id") Long id) {
		Optional<Chamado> chamado = this.chamadoRepository.findById(id);

		return ResponseEntity.status(HttpStatus.OK).body(chamado.get());
	}

	@PostMapping
	public ResponseEntity<Void> incluir(@RequestBody Chamado chamado) {
		this.chamadoRepository.save(chamado);

		final URI uriChamado = super.criarUriPorId(chamado.getId());

		return ResponseEntity.created(uriChamado).build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody Chamado chamado) {
		chamado.setId(id);
		this.chamadoRepository.save(chamado);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
		this.chamadoRepository.deleteById(id);

		return ResponseEntity.ok().build();
	}
}
