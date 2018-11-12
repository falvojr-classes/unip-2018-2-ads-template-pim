package br.unip.ads.pim.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.unip.ads.pim.model.Chamado;
import br.unip.ads.pim.model.ChamadoStatus;

@Repository
public interface ChamadoRepository extends CrudRepository<Chamado, Long> {

	// SELECT * FROM chamado 
	// WHERE status IN (parametro status)
	// ORDER BY inicio ASC
	List<Chamado> findByStatusInOrderByInicioAsc(ChamadoStatus... status);
	
	// SELECT * FROM chamado cha
	// INNER JOIN cliente cli
	// WHERE cli.id = :idCliente
	// ORDER BY cha.inicio ASC
	List<Chamado> findByCliente_IdOrderByInicioDesc(Long idCliente);
}
