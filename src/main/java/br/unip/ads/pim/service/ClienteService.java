package br.unip.ads.pim.service;

import br.unip.ads.pim.model.Cliente;

public interface ClienteService {

	Iterable<Cliente> buscarTodos();

	Cliente buscarUm(Long id);

	void incluir(Cliente Cliente);

	void atualizar(Cliente Cliente);

	void remover(Long id);
}
