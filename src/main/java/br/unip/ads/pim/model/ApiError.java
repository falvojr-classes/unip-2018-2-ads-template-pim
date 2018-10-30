package br.unip.ads.pim.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe que encapsula os erros enviados da API para o frontend.
 * 
 */
public class ApiError {
	
	@JsonProperty("message")
	private String mensagem;

	public ApiError(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
