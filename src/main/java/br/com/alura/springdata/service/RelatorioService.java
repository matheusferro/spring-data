package br.com.alura.springdata.service;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.projecao.FuncionarioProjecao;
import br.com.alura.springdata.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioService {

    private boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final FuncionarioRepository funcionarioRepository;

    public RelatorioService (FuncionarioRepository _funcionarioRepository){
        this.funcionarioRepository = _funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        while (system){
            System.out.println("Qual função você quer executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Buscar funcionario por nome");
            System.out.println("2 - Buscar por nome, salario e data de contratação");
            System.out.println("3 - Pesquisa funcionario salario");
            int action = scanner.nextInt();
            switch (action){
                case 1:
                    buscaPorNome(scanner);
                    break;
                case 2:
                    buscaPorNomeSalarioData(scanner);
                    break;
                case 3:
                    //buscaPorNomeSalarioDataNativo(scanner);
                    pesquisaFuncionarioSalario();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscaPorNome(Scanner scanner){
        System.out.println("Digite o nome do funcionario:");
        String nome = scanner.next();
        List<Funcionario> listaFuncionario = funcionarioRepository.findByNome(nome);
        listaFuncionario.forEach(System.out::println);
    }

    private void buscaPorNomeSalarioData(Scanner scanner){
        System.out.println("Digite o nome do funcionario:");
        String nome = scanner.next();

        System.out.println("Digite o salario do funcionario:");
        BigDecimal salario = scanner.nextBigDecimal();

        System.out.println("Digite a data de contratação do funcionario:");
        String data = scanner.next();
        LocalDate dataContratacao = LocalDate.parse(data, formatter);

        List<Funcionario> listaFuncionario = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, dataContratacao);
        listaFuncionario.forEach(System.out::println);
    }

    private void buscaPorNomeSalarioDataNativo(Scanner scanner){
        System.out.println("Digite o nome do funcionario:");
        String nome = scanner.next();

        System.out.println("Digite o salario do funcionario:");
        BigDecimal salario = scanner.nextBigDecimal();

        System.out.println("Digite a data de contratação do funcionario:");
        String data = scanner.next();
        LocalDate dataContratacao = LocalDate.parse(data, formatter);

        List<Funcionario> listaFuncionario = funcionarioRepository.findNomeSalarioMaiorDataContratacaoNativo(nome, salario, dataContratacao);
        listaFuncionario.forEach(System.out::println);
    }

    private void pesquisaFuncionarioSalario(){
        List<FuncionarioProjecao> funcionarioList = funcionarioRepository.findFuncionarioSalario();
        funcionarioList.forEach(f -> System.out.println("Funcionario:  id:" + f.getId() + " | nome:"+ f.getNome()+" | salario:"+f.getSalario()));
    }
}
