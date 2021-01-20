 package br.com.alura.springdata.service;

import br.com.alura.springdata.orm.UnidadeTrabalho;
import br.com.alura.springdata.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeService {

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
        Iterable<UnidadeTrabalho> it = unidadeRepository.findAll();
        it.forEach(System.out::println);
    }

    private void cadastrar(Scanner scanner) {
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();

        System.out.println("Digite a descrição: ");
        String desc = scanner.next();

        System.out.println("Digite o endereço: ");
        String end = scanner.next();

        unidadeTrabalho.setDescricao(desc);
        unidadeTrabalho.setEndereco(end);

        unidadeRepository.save(unidadeTrabalho);
        System.out.println("Cadastrado");
    }

    private void alterar(Scanner scanner) {
        visualizar();
        System.out.println("Digite a identificação da unidade que deseja alterar: ");
        Integer idUnidade = scanner.nextInt();
        UnidadeTrabalho unidade = unidadeRepository.findById(idUnidade).get();
        System.out.println("Digite a nova descrição: ");
        String desc = scanner.next();

        System.out.println("Digite o novo endereço: ");
        String end = scanner.next();

        unidade.setDescricao(desc);
        unidade.setEndereco(end);

        unidadeRepository.save(unidade);
        System.out.println("Alterado");
    }

    private void excluir(Scanner scanner) {
        visualizar();
        System.out.println("Digite a identificação da unidade que deseja excluir: ");
        Integer idUnidade = scanner.nextInt();
        unidadeRepository.deleteById(idUnidade);
        System.out.println("Excluido");
    }
}
