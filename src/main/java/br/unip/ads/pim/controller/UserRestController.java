package br.unip.ads.pim.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unip.ads.pim.config.SwaggerConfig;
import io.swagger.annotations.Api;

@Api(tags = SwaggerConfig.TAG_USUARIO)
@RestController
@RequestMapping("/api/usuarios")
public class UserRestController extends BaseRestController {

	@GetMapping("eu")
	public ResponseEntity<Void> eu() {
		return ResponseEntity.ok().build();
	}
}
