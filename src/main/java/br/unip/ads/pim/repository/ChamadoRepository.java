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
}
