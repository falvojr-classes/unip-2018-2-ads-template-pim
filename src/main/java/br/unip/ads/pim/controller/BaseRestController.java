package br.unip.ads.pim.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unip.ads.pim.model.ApiError;
import br.unip.ads.pim.utils.ExcecaoNegocial;

@Component
public abstract class BaseRestController {
	
	@ExceptionHandler(ExcecaoNegocial.class)
	public ResponseEntity<ApiError> handleExcecaoNegocial(ExcecaoNegocial ex) {
		String message = ex.getMessage();
		return ResponseEntity.unprocessableEntity().body(new ApiError(message));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleUnknowException(Exception ex) {
		String message = ex.getMessage();
		return ResponseEntity.badRequest().body(new ApiError(message));
	}

	protected URI criarUriPorId(Object id) {
		return ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(id)
				.toUri();
	}
}
