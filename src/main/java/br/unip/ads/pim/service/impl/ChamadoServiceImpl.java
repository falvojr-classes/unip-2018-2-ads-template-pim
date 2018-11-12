package br.unip.ads.pim.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.unip.ads.pim.model.Chamado;
import br.unip.ads.pim.model.ChamadoStatus;
import br.unip.ads.pim.model.Cliente;
import br.unip.ads.pim.repository.ChamadoRepository;
import br.unip.ads.pim.repository.ClienteRepository;
import br.unip.ads.pim.service.ChamadoService;
import br.unip.ads.pim.utils.ExcecaoNegocial;

@Service
public class ChamadoServiceImpl implements ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void incluir(Chamado chamado) {
		if (chamado.getId() != null) {
			throw new ExcecaoNegocial("O ID não deve ser especificado.");
		}
		Long idCliente = chamado.getCliente().getId();
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		
		// Lanca a excecão caso o Cliente não exista (null)
		cliente.orElseThrow(() -> new ExcecaoNegocial("Apenas clientes podem abrir chamados."));

		chamado.setStatus(ChamadoStatus.ABERTO);
		chamado.setInicio(LocalDateTime.now());
		this.chamadoRepository.save(chamado);
	}

	@Override
	public Iterable<Chamado> buscarTodos(String idCliente, String idFuncionario) {
		if (!StringUtils.isEmpty(idCliente)) {
			// Retorna os Chamados de um Cliente específico:
			Long id = Long.valueOf(idCliente);
			return this.chamadoRepository.findByCliente_IdOrderByInicioDesc(id);
		} else if (!StringUtils.isEmpty(idFuncionario)) {
			// Retorna os Chamados ABERTOS ou EM_ANDAMENTO para qualquer Funcionario
			return this.chamadoRepository.findByStatusInOrderByInicioAsc(
					ChamadoStatus.ABERTO, ChamadoStatus.EM_ANDAMENTO);
		} else {
			// Retorna todos os Chamados
			return this.chamadoRepository.findAll();
		}
	}

	@Override
	public Chamado buscarUm(Long id) {
		Optional<Chamado> chamado = this.chamadoRepository.findById(id);
		
		return chamado.orElseThrow(() -> new ExcecaoNegocial("ID não localizado."));
	}

	@Override
	public void atualizar(Chamado chamado) {
		if (chamado.getId() == null) {
			throw new ExcecaoNegocial("O ID deve ser especificado.");
		}
		if(chamado.getFuncionario() != null) {
			if (ChamadoStatus.ABERTO.equals(chamado.getStatus())) {
				chamado.setStatus(ChamadoStatus.EM_ANDAMENTO);
			} else if (ChamadoStatus.EM_ANDAMENTO.equals(chamado.getStatus())) {
				chamado.setStatus(ChamadoStatus.FECHADO);
				chamado.setFim(LocalDateTime.now());
			}
		}
		
		this.chamadoRepository.save(chamado);
	}

	@Override
	public void remover(Long id) {
		this.chamadoRepository.deleteById(id);
	}

}
