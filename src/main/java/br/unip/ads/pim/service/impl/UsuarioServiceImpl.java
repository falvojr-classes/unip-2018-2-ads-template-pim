package br.unip.ads.pim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ads.pim.model.Usuario;
import br.unip.ads.pim.repository.ClienteRepository;
import br.unip.ads.pim.repository.FuncionarioRepository;
import br.unip.ads.pim.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;
	
	@Override
	public Usuario login(String email) {
		Usuario usuarioLogado;
		usuarioLogado = funcionarioRepository.findByEmail(email);
		if (usuarioLogado == null) {
			usuarioLogado = clienteRepository.findByEmail(email);
		}
		return usuarioLogado;
	}

}
