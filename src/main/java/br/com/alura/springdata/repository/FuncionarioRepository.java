package br.com.alura.springdata.repository;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.projecao.FuncionarioProjecao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {

    List<Funcionario> findByNome(String nome);

    //METODO COM PAGINAÇÃO
    List<Funcionario> findByNome(String nome, Pageable pageable);

    //PROJEÇÕES
    @Query(value="SELECT f.id, f.nome, f.salario FROM funcionario f", nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario();

    List<Funcionario> findByNomeLike(String nome);

    List<Funcionario> findByNomeStartingWith(String nome);

    List<Funcionario> findByNomeEndingWith(String nome);

    List<Funcionario> findByNomeOrderByNomeAsc(String nome);

    @Query("SELECT f FROM Funcionario f WHERE f.nome = :pNome AND f.salario >= :pSalario AND f.dataContratacao = :pDataContratacao")
    List<Funcionario> findNomeSalarioMaiorDataContratacao(String pNome, BigDecimal pSalario, LocalDate pDataContratacao);

    @Query(value = "SELECT * FROM funcionario f WHERE f.nome = :pNome AND f.salario >= :pSalario AND f.data_contratacao = :pDataContratacao"
    , nativeQuery = true)
    List<Funcionario> findNomeSalarioMaiorDataContratacaoNativo(String pNome, BigDecimal pSalario, LocalDate pDataContratacao);

    //CONSULTAS POR RELAÇÕES
    List<Funcionario> findByCargoDescricao(String desc);

    @Query("SELECT f FROM Funcionario f JOIN f.cargo c where c.descricao = :pDesc")
    List<Funcionario> buscarFuncionariosPorCargoDescricao(String pDesc);

    List<Funcionario> findByUnidadeTrabalho_Descricao(String desc);

    @Query("SELECT f FROM Funcionario f JOIN f.unidadeTrabalho u where u.descricao = :pDesc")
    List<Funcionario> buscarDescricaoUnidadeDeTrabalho(String pDesc);
}