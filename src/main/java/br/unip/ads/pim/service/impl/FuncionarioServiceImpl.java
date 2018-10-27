package br.unip.ads.pim.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ads.pim.model.Funcionario;
import br.unip.ads.pim.repository.FuncionarioRepository;
import br.unip.ads.pim.service.FuncionarioService;
import br.unip.ads.pim.utils.ExcecaoNegocial;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Override
	public void incluir(Funcionario Funcionario) {
		if (Funcionario.getId() != null) {
			throw new ExcecaoNegocial("O ID não deve ser especificado.");
		}
		this.funcionarioRepository.save(Funcionario);
	}

	@Override
	public Iterable<Funcionario> buscarTodos() {
		return this.funcionarioRepository.findAll();
	}

	@Override
	public Funcionario buscarUm(Long id) {
		Optional<Funcionario> Funcionario = this.funcionarioRepository.findById(id);
		return Funcionario.orElseThrow(() -> new ExcecaoNegocial("ID não localizado."));
	}

	@Override
	public void atualizar(Funcionario Funcionario) {
		if (Funcionario.getId() == null) {
			throw new ExcecaoNegocial("O ID deve ser especificado.");
		}
		this.funcionarioRepository.save(Funcionario);
	}

	@Override
	public void remover(Long id) {
		this.funcionarioRepository.deleteById(id);
	}

}
