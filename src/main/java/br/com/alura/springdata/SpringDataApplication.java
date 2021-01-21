package br.com.alura.springdata;

import br.com.alura.springdata.service.CrudCargoService;
import br.com.alura.springdata.service.CrudFuncionarioService;
import br.com.alura.springdata.service.CrudUnidadeService;
import br.com.alura.springdata.service.RelatorioFuncionarioDinamico;
import br.com.alura.springdata.service.RelatorioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService cargoService;

	private final CrudFuncionarioService funcionarioService;

	private final CrudUnidadeService unidadeService;

	private final RelatorioService relatorioService;
	private final RelatorioFuncionarioDinamico relatorioDinamicoService;

	public SpringDataApplication(CrudCargoService crudCargoService,
								 CrudFuncionarioService crudFuncionarioService,
								 CrudUnidadeService crudUnidadeService,
								 RelatorioService _relatorioService,
								 RelatorioFuncionarioDinamico relatorioDinamicoService) {
		this.cargoService = crudCargoService;
		this.funcionarioService = crudFuncionarioService;
		this.unidadeService = crudUnidadeService;
		this.relatorioService = _relatorioService;
		this.relatorioDinamicoService = relatorioDinamicoService;
	}


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
			System.out.println("4 - Relatorios");
			System.out.println("5 - Relatorio dinamico");
			int action = scanner.nextInt();
			switch (action){
				case 1:
					cargoService.inicial(scanner);
					break;
				case 2:
					funcionarioService.inicial(scanner);
					break;
				case 3:
					unidadeService.inicial(scanner);
					break;
				case 4:
					relatorioService.inicial(scanner);
					break;
				case 5:
					relatorioDinamicoService.inicial(scanner);
					break;
				default:
					system = false;

			}
		}

	}
}
