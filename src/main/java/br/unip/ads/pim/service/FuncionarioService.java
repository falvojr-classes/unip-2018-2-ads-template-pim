package br.unip.ads.pim.service;

import br.unip.ads.pim.model.Funcionario;

public interface FuncionarioService {

	Iterable<Funcionario> buscarTodos();

	Funcionario buscarUm(Long id);

	void incluir(Funcionario Funcionario);

	void atualizar(Funcionario Funcionario);

	void remover(Long id);
}
