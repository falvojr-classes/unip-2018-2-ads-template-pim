package br.unip.ads.pim.service;

import br.unip.ads.pim.model.Chamado;

public interface ChamadoService {

	Iterable<Chamado> buscarTodos();

	Chamado buscarUm(Long id);

	void incluir(Chamado chamado);

	void atualizar(Chamado chamado);

	void remover(Long id);
}
