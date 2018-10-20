package br.unip.ads.pim.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.unip.ads.pim.model.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

	// SELECT * FROM funcionario WHERE email = :email AND senha = :senha
	Funcionario findByEmailAndSenha(String email, String senha);
	
	// SELECT * FROM funcionario WHERE email = :email
	Funcionario findByEmail(String email);
}
