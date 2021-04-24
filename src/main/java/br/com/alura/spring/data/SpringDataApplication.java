package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.alura.spring.data.repository.service.CrudCargoService;
import br.com.alura.spring.data.repository.service.CrudFuncionarioService;
import br.com.alura.spring.data.repository.service.CrudUnidadeTrabalhoService;
import br.com.alura.spring.data.repository.service.RelatorioFuncionarioDinamico;
import br.com.alura.spring.data.repository.service.RelatorioService;

@EnableJpaRepositories
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	Scanner sc = new Scanner(System.in);

	// Controla o status do loop do menu
	private boolean repetirLoop = true;

	private CrudCargoService cargoService;
	private CrudFuncionarioService funcionarioService;
	private CrudUnidadeTrabalhoService unidadeTrabalhoService;
	private RelatorioService relatorioService;
	private RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

	public SpringDataApplication(CrudCargoService cargoService, CrudFuncionarioService funcionarioService,
			CrudUnidadeTrabalhoService unidadeTrabalhoService, RelatorioService relatorioService,
			RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {

		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.relatorioService = relatorioService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Ler o que foi digitado no terminal

		while (repetirLoop) {

			System.out.println("Esolha uma opção:");
			System.out.println("0 - Sair");
			System.out.println("1 - Entra na seção de Cargo");
			System.out.println("2 - Entra na seção de Funcionário");
			System.out.println("3 - Entra na seção de Unidade Trabalho");
			System.out.println("4 - Relatórios");
			System.out.println("5 - Relatório Dinâmico de Funcionários");
			Integer opcaoDigitada = Integer.parseInt(sc.nextLine());
			switch (opcaoDigitada) {
			case 0:
				repetirLoop = false;
				break;

			case 1:
				cargoService.menuCargo();
				break;

			case 2:
				funcionarioService.menuFuncionario();
				break;

			case 3:
				unidadeTrabalhoService.menuUnidadeTrabalho();
				break;

			case 4:
				relatorioService.menuRelatorio();
				break;

			case 5:
				relatorioFuncionarioDinamico.relatorioDinamico();
				break;

			default:
				System.out.println("Saindo..");
				repetirLoop = false;
				break;
			}
		}
	}

}
