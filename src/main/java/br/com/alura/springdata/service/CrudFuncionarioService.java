package br.com.alura.springdata.service;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.orm.UnidadeTrabalho;
import br.com.alura.springdata.repository.CargoRepository;
import br.com.alura.springdata.repository.FuncionarioRepository;
import br.com.alura.springdata.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class CrudFuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    private boolean system = true;

    public void inicial(Scanner scanner){
        while (system){
            System.out.println("Qual função você quer executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Visualizar");
            System.out.println("2 - Cadastro");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");

            int action = scanner.nextInt();
            switch (action){
                case 1:
                    visualizar();
                    break;
                case 2:
                    cadastrar(scanner);
                    break;
                case 3:
                    alterar(scanner);
                    break;
                case 4:
                    excluir(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void visualizar() {
        Iterable<Funcionario> it = repository.findAll();
        it.forEach(System.out::println);
    }

    private void cadastrar(Scanner scanner) {
        Funcionario funcionario = new Funcionario();

        System.out.println("Digite o nome: ");
        String nome = scanner.next();

        System.out.println("Digite o cpf: ");
        String cpf = scanner.next();

        System.out.println("Digite o salario: ");
        BigDecimal salario = scanner.nextBigDecimal();

        System.out.println("Digite o id do cargo: ");
        Integer idCargo = scanner.nextInt();

        Optional<Cargo> cargo  = cargoRepository.findById(idCargo);
        funcionario.setNome(nome);
        funcionario.setCargo(cargo.get());
        funcionario.setDataContratacao(LocalDate.now());
        funcionario.setSalario(salario);
        funcionario.setCpf(cpf);
        List<UnidadeTrabalho> unidades = escolherUnidades(scanner);
        funcionario.setUnidade(unidades);
        repository.save(funcionario);
        System.out.println("CADASTRADO!");
    }

    private List<UnidadeTrabalho> escolherUnidades(Scanner scanner){
        Boolean isTrue = true;
        List<UnidadeTrabalho> listUnidade = new ArrayList<>();

        while (isTrue){
            System.out.println("Digite a identificação da unidade para adicionar a esse funcionario (digite 0 quando terminar): ");
            Integer unidadeId = scanner.nextInt();
            if(unidadeId != 0){
                Optional<UnidadeTrabalho> unidade  = unidadeRepository.findById(unidadeId);
                listUnidade.add(unidade.get());
            }else{
                isTrue = false;
            }
        }
        return listUnidade;
    }

    private void alterar(Scanner scanner) {
        visualizar();
        System.out.println("Digite o id do funcionario: ");
        Integer idFuncionario = scanner.nextInt();


        System.out.println("Digite o novo nome: ");
        String nome = scanner.next();

        System.out.println("Digite o novo cpf: ");
        String cpf = scanner.next();

        System.out.println("Digite o novo salario: ");
        BigDecimal salario = scanner.nextBigDecimal();

        Funcionario funcionario = repository.findById(idFuncionario).get();
        funcionario.setNome(nome);
        funcionario.setId(idFuncionario);
        funcionario.setSalario(salario);
        funcionario.setCpf(cpf);

        repository.save(funcionario);

        System.out.println("ALTERADO!");
    }

    private void excluir(Scanner scanner) {
        visualizar();
        System.out.println("Digite o id do funcionario: ");
        Integer idFuncionario = scanner.nextInt();
        repository.deleteById(idFuncionario);
        System.out.println("DELETADO!");
    }
}
