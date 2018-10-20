package br.unip.ads.pim.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unip.ads.pim.util.ExcecaoNegocial;

@RestControllerAdvice
public abstract class BaseRestController {
	
	@ExceptionHandler(ExcecaoNegocial.class)
	public ResponseEntity<String> handleExcecaoNegocial(ExcecaoNegocial ex) {
		return ResponseEntity.unprocessableEntity().body(ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleUnknowException(Exception ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	protected URI criarUriPorId(Object id) {
		return ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(id)
				.toUri();
	}
}
