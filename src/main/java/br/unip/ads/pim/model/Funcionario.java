package br.unip.ads.pim.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Funcionario extends Usuario {

	@Column
	private String matricula;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
