package br.unip.ads.pim.config;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.unip.ads.pim.model.Usuario;
import br.unip.ads.pim.repository.ClienteRepository;
import br.unip.ads.pim.repository.FuncionarioRepository;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String login = auth.getName();
        String senha = auth.getCredentials().toString();

        // Regras de Autenticação (usaremos via Basic Authentication)
        
        Usuario usuario = clienteRepository.findByEmailAndSenha(login, senha);
        if (usuario != null) {
			return new UsernamePasswordAuthenticationToken(login, senha, emptyList());
        }        
        usuario = funcionarioRepository.findByEmailAndSenha(login, senha);
        if (usuario != null) {
			return new UsernamePasswordAuthenticationToken(login, senha, emptyList());
        }

        throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}