package br.unip.ads.pim.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.unip.ads.pim.model.Chamado;

@Repository
public interface ChamadoRepository extends CrudRepository<Chamado, Long> {

}
