package br.com.alura.springdata.service;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CrudCargoService {

    @Autowired
    private CargoRepository cargoRepository;

    private boolean system=true;

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

    private void cadastrar(Scanner scanner){
        System.out.println("Descrição do cargo: ");
        String desc = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(desc);
        cargoRepository.save(cargo);
        System.out.println("Salvo!");
    }

    private void visualizar(Iterable<Cargo> it){
        it.forEach(System.out::println);
    }

    private void visualizar(){
        Iterable<Cargo> it = cargoRepository.findAll();
        it.forEach(System.out::println);
    }

    private void alterar(Scanner scanner) {
        Iterable<Cargo> it = cargoRepository.findAll();
        if(!it.iterator().hasNext()){
            System.out.println("Sem dados para alterar!!!");
            return;
        }
        System.out.println("Lista de possiveis cargos para alterar: ");
        visualizar(it);

        System.out.println("Digite o numero de identificação do cargo que deseja alterar: ");
        Integer id = scanner.nextInt();
        System.out.println("Digite o novo nome do cargo de id "+id+": ");
        String desc = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(desc);
        cargoRepository.save(cargo);
        System.out.println("Alterado");
    }

    private void excluir(Scanner scanner) {
        Iterable<Cargo> it = cargoRepository.findAll();
        if(!it.iterator().hasNext()){
            System.out.println("Sem dados para excluir!!!");
            return;
        }
        System.out.println("Lista de possiveis cargos para deletar: ");
        visualizar(it);

        System.out.println("Digite o numero de identificação do cargo que deseja deletar: ");
        Integer id = scanner.nextInt();
        Iterator<Cargo> cargoIt = cargoRepository.findAllById(Collections.singleton(id)).iterator();
        if(!cargoIt.hasNext()){
            System.out.println("NENHUM DADO ENCONTRADO PARA DELETAR.");
            return;
        }
        System.out.println("2");
        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargoRepository.delete(cargo);
        System.out.println("Deletado");
    }
}
