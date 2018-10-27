package br.unip.ads.pim.model;

public class ApiError {
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
