package br.com.alura.springdata.repository;

import br.com.alura.springdata.orm.Funcionario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {

    List<Funcionario> findByNome(String nome);

    List<Funcionario> findByNomeLike(String nome);

    List<Funcionario> findByNomeStartingWith(String nome);

    List<Funcionario> findByNomeEndingWith(String nome);

    List<Funcionario> findByNomeOrderByNomeAsc(String nome);
}