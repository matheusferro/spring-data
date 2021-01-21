package br.com.alura.springdata.service;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.repository.FuncionarioRepository;
import br.com.alura.springdata.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final FuncionarioRepository funcionarioRepository;

    public RelatorioFuncionarioDinamico(FuncionarioRepository _funcionarioRepository){
        this.funcionarioRepository = _funcionarioRepository;
    }


    public void inicial(Scanner scanner){
        System.out.println("Digite o nome do funcionário:");
        String nome = scanner.next();

        if(nome.equalsIgnoreCase("NULL")){
            nome= null;
        }

        System.out.println("Digite o cpf do funcionário:");
        String cpf = scanner.next();

        if(cpf.equalsIgnoreCase("NULL")){
            cpf= null;
        }

        System.out.println("Digite o salario do funcionário:");
        BigDecimal salario = scanner.nextBigDecimal();

        if(salario.intValue() == 0){
            salario= null;
        }

        System.out.println("Digite a data de contratação do funcionário:");
        String data = scanner.next();
        LocalDate dtContratacao;
        if(data.equalsIgnoreCase("NULL")){
            dtContratacao= null;
        }else{
            dtContratacao=LocalDate.parse(data,formatter);
        }

        List<Funcionario> listFuncionario = funcionarioRepository.findAll(
                Specification.where(
                        SpecificationFuncionario.nome(nome)
                                .or(SpecificationFuncionario.cpf(cpf))
                                .or(SpecificationFuncionario.dataContratacao(dtContratacao))
                                .or(SpecificationFuncionario.salario(salario))
                ));
        listFuncionario.forEach(System.out::println);
    }
}
