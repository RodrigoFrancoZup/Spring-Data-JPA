package br.com.alura.spring.data.repository.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private FuncionarioRepository funcionarioRepository;
	private CargoRepository cargoRepository;
	private UnidadeTrabalhoRepository unidadeTrabalhoRepository;

	// Controla o status do loop do menu
	private boolean repetirLoop = true;

	Scanner sc = new Scanner(System.in);

	// Formatar Data
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository,
			UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	public void menuFuncionario() {
		while (repetirLoop) {

			System.out.println("Esolha uma opção:");
			System.out.println("0 - Sair");
			System.out.println("1 - Cadastrar Funcionario");
			System.out.println("2 - Atualizar Funcionario");
			System.out.println("3 - Visualizar Funcionarios");
			System.out.println("4 - Deletar Funcionario");
			Integer opcaoDigitada = Integer.parseInt(sc.nextLine());

			switch (opcaoDigitada) {
			case 0:
				System.out.println("Voltando Menu Principal...");
				repetirLoop = false;
				break;

			case 1:
				cadastrar();
				break;

			case 2:
				atualizar();
				break;

			case 3:
				visualizar();
				break;

			case 4:
				deletar();
				break;

			default:
				System.out.println("Voltando Menu Principal...");
				repetirLoop = false;
				break;
			}
		}
	}

	private void deletar() {
		System.out.println("Digite o ID do Funcionário de desejas Deletar");
		Integer id = Integer.parseInt(sc.nextLine());
		funcionarioRepository.deleteById(id);
		System.out.println("Registro apagado...");

	}

	private void visualizar() {
		System.out.println("Digite a página que deseja acessar da listagem - começa em 0:");
		Integer pagina = Integer.parseInt(sc.nextLine());
		Pageable paginacao = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.DESC, "salario"));
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(paginacao);
		System.out.println(funcionarios);
		System.out.println("Página atual = " + funcionarios.getNumber());
		System.out.println("Quantidade Total de Registros = " + funcionarios.getTotalElements());
		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario.toString());
		}

	}

	private void atualizar() {
		System.out.println("Digite o Id do Funcionário de desejas Atualizar:");
		Integer id = Integer.parseInt(sc.nextLine());

		System.out.println("Digite o nome do Funcionário:");
		String nome = sc.nextLine();

		System.out.println("Digite o cpf do Funcionário:");
		String cpf = sc.nextLine();

		System.out.println("Digite o salário do Funcionário:");
		Double salario = Double.parseDouble(sc.nextLine());

		System.out.println("Digite a data de contração");
		String dataString = sc.nextLine();
		LocalDate data = LocalDate.parse(dataString, formatter);

		System.out.println("Digite o ID do Cargo que o funcionário terá: ");
		Integer cargo_id = Integer.parseInt(sc.nextLine());
		Cargo cargo = cargoRepository.findById(cargo_id).get();

		List<UnidadeTrabalho> unidades = insereUnidadeTrabalho();

		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(data);
		funcionario.setCargo(cargo);
		funcionario.setUnidadeTrabalhos(unidades);

		funcionarioRepository.save(funcionario);

	}

	private void cadastrar() {
		System.out.println("Digite o nome do Funcionário de desejas Cadastrar:");
		String nome = sc.nextLine();

		System.out.println("Digite o cpf do Funcionário:");
		String cpf = sc.nextLine();

		System.out.println("Digite o salário do Funcionário:");
		Double salario = Double.parseDouble(sc.nextLine());

		System.out.println("Digite a data de contração");
		String dataString = sc.nextLine();
		LocalDate data = LocalDate.parse(dataString, formatter);

		System.out.println("Digite o ID do Cargo que o funcionário terá: ");
		Integer cargo_id = Integer.parseInt(sc.nextLine());
		Cargo cargo = cargoRepository.findById(cargo_id).get();

		List<UnidadeTrabalho> unidades = insereUnidadeTrabalho();

		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(data);
		funcionario.setCargo(cargo);
		funcionario.setUnidadeTrabalhos(unidades);

		funcionarioRepository.save(funcionario);
	}

	private List<UnidadeTrabalho> insereUnidadeTrabalho() {
		List<UnidadeTrabalho> unidadesSelecionadas = new ArrayList<UnidadeTrabalho>();
		boolean menuInsereUnidadeTrabalho = true;
		while (menuInsereUnidadeTrabalho) {
			System.out.println("Digite o ID da Unidade de Trabalho do Funcionário ou digite 0 para voltar:");
			Integer opcao = Integer.parseInt(sc.nextLine());
			if (opcao == 0) {
				System.out.println("Voltando ...");
				menuInsereUnidadeTrabalho = false;
			} else {
				UnidadeTrabalho unidadeSelecionada = unidadeTrabalhoRepository.findById(opcao).get();
				unidadesSelecionadas.add(unidadeSelecionada);
			}
		}
		return unidadesSelecionadas;
	}

}
