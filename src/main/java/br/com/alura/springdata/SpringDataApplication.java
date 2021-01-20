package br.com.alura.springdata;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.repository.CargoRepository;
import br.com.alura.springdata.service.CrudCargoService;
import br.com.alura.springdata.service.CrudFuncionarioService;
import br.com.alura.springdata.service.CrudUnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	@Autowired
	private CrudCargoService cargoService;

	@Autowired
	private CrudFuncionarioService funcionarioService;

	@Autowired
	private CrudUnidadeService unidadeService;

	private Boolean system = true;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while(system){
			System.out.println("Qual menu você quer acessar");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade");
			int action = scanner.nextInt();
			if(action == 1){
				cargoService.inicial(scanner);
			}else if(action == 2){
				funcionarioService.inicial(scanner);
			}else if(action == 3){
				unidadeService.inicial(scanner);
			}else{
				system =false;
			}
		}

	}
}
