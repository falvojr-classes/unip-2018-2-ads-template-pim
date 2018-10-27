package br.unip.ads.pim.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ads.pim.model.Cliente;
import br.unip.ads.pim.repository.ClienteRepository;
import br.unip.ads.pim.service.ClienteService;
import br.unip.ads.pim.utils.ExcecaoNegocial;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void incluir(Cliente Cliente) {
		if (Cliente.getId() != null) {
			throw new ExcecaoNegocial("O ID não deve ser especificado.");
		}
		this.clienteRepository.save(Cliente);
	}

	@Override
	public Iterable<Cliente> buscarTodos() {
		return this.clienteRepository.findAll();
	}

	@Override
	public Cliente buscarUm(Long id) {
		Optional<Cliente> Cliente = this.clienteRepository.findById(id);
		return Cliente.orElseThrow(() -> new ExcecaoNegocial("ID não localizado."));
	}

	@Override
	public void atualizar(Cliente Cliente) {
		if (Cliente.getId() == null) {
			throw new ExcecaoNegocial("O ID deve ser especificado.");
		}
		this.clienteRepository.save(Cliente);
	}

	@Override
	public void remover(Long id) {
		this.clienteRepository.deleteById(id);
	}

}
