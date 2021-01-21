package br.com.alura.springdata.orm;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String cpf;
    private BigDecimal salario;
    private LocalDate dataContratacao;
//    private LocalDateTime dataContratacao;
    @ManyToOne
    @JoinColumn(name="cargo_id", nullable = false)
    private Cargo cargo;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name="funcionarios_unidades", joinColumns = {
            @JoinColumn(name = "fk_funcionarios")},
            inverseJoinColumns = { @JoinColumn(name="fk_unidade")})
    private List<UnidadeTrabalho> unidadeTrabalho = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<UnidadeTrabalho> getUnidade() {
        return unidadeTrabalho;
    }

    public void setUnidade(List<UnidadeTrabalho> unidadeTrabalho) {
        this.unidadeTrabalho = unidadeTrabalho;
    }

    @Override
    public String toString() {
        StringBuilder unidades = new StringBuilder();
        for (UnidadeTrabalho un : unidadeTrabalho) {
            unidades.append(un.getDescricao()).append(" - ").append(un.getEndereco());
            unidades.append("  ");
        }
        return "ID:" + id + "  NOME:" + nome + "  CPF:" + cpf + " SALARIO:"+salario+" CARGO:" + cargo.getDescricao()
                + " DATA DE CONTRATAÇÃO:"+dataContratacao+" UNIDADES:" + unidades;
    }
}
