package br.com.alura.spring.data.repository.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.mysql.cj.x.protobuf.MysqlxCrud.ProjectionOrBuilder;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.projecao.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatorioService {

	private FuncionarioRepository funcionarioRepository;
	private Scanner sc = new Scanner(System.in);
	private boolean repetirLoop = true;

	// Formatar Data
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public RelatorioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void menuRelatorio() {
		while (repetirLoop) {

			System.out.println("Esolha uma opção:");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca de Funcionários por Nome");
			System.out.println("2 - Busca de Funcionários por Nome, Data de Contratação e Valor de salário maior");
			System.out.println("3 - Busca de Funcionários por uma data (superior)");
			System.out.println("4 - Relatório de Funcionário e seu Salário");
			Integer opcaoDigitada = Integer.parseInt(sc.nextLine());

			switch (opcaoDigitada) {
			case 0:
				System.out.println("Voltando Menu Principal...");
				repetirLoop = false;
				break;

			case 1:
				buscaFuncionarioPorNome();
				break;

			case 2:
				buscaFuncionarioPorNomeDataESalarioMaiorQue();
				break;

			case 3:
				buscaFuncionarioPorDataSuperiorA();
				break;
				
			case 4:
				relatorioDeFuncionarioComIdNomeESalario();
				break;
				
			default:
				System.out.println("Voltando Menu Principal...");
				repetirLoop = false;
				break;
			}
		}
	}

	private void relatorioDeFuncionarioComIdNomeESalario() {
		List<FuncionarioProjecao> projecoes = funcionarioRepository.relatorioDeFuncionarioComIdNomeESalario();
		projecoes.forEach(p -> System.out.println("ID = " + p.getId() + "|" + " Nome = "  + p.getNome() + "|" + " Salário = " + p.getSalario()));
	}

	private void buscaFuncionarioPorDataSuperiorA() {
		System.out.println("Digite a data limite:");
		String dataString = sc.nextLine();
		LocalDate data = LocalDate.parse(dataString, formatter);
		List<Funcionario> funcionarios = funcionarioRepository.buscaFuncionarioPorDataSuperior(data);
		funcionarios.forEach(f -> System.out.println(f.toString()));

	}

	private void buscaFuncionarioPorNomeDataESalarioMaiorQue() {
		System.out.println("Digite o nome que deseja buscar:");
		String nome = sc.nextLine();
		System.out.println("Digite a data de contratação");
		String dataString = sc.nextLine();
		LocalDate data = LocalDate.parse(dataString, formatter);
		System.out.println("Digite o salário:");
		Double salario = Double.parseDouble(sc.nextLine());
		List<Funcionario> funcionarios = funcionarioRepository.buscaFuncionarioPorNomeDataESalarioMaiorQue(nome, data,
				salario);
		funcionarios.forEach(f -> System.out.println(f.toString()));
	}

	private void buscaFuncionarioPorNome() {
		System.out.println("Digite o nome que deseja buscar:");
		String nome = sc.nextLine();
		List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
		funcionarios.forEach(f -> System.out.println(f.toString()));
	}

}
