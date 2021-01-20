package br.com.alura.springdata.repository;

import br.com.alura.springdata.orm.UnidadeTrabalho;
import org.springframework.data.repository.CrudRepository;

public interface UnidadeRepository extends CrudRepository<UnidadeTrabalho, Integer> {
}
