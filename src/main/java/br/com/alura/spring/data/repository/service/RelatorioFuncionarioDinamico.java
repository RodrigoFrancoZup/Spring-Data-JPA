package br.com.alura.spring.data.repository.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {

	private FuncionarioRepository funcionarioRepository;
	private Scanner sc = new Scanner(System.in);
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void relatorioDinamico() {
			System.out.println("Digite o nome que deseja filtrar:");
			String nomeString = sc.nextLine();
			if (nomeString.equalsIgnoreCase("NULL")) {
				nomeString = null;
			}
			System.out.println("Digite o CPF que deseja filtrar:");
			String cpf = sc.nextLine();
			if (cpf.equalsIgnoreCase("NULL")) {
				cpf = null;
			}
			System.out.println("Digite o salario que deseja filtrar:");
			Double salario = Double.parseDouble(sc.nextLine());
			if (salario == 0) {
				salario = null;
			}
			System.out.println("Digite a data que deseja filtrar:");
			String dataString = sc.nextLine();
			LocalDate data;
			if (dataString.equalsIgnoreCase("NULL")) {
				data = null;
			} else {
				data = LocalDate.parse(dataString, formatter);
			}

			List<Funcionario> funcionarios = funcionarioRepository
					.findAll(Specification.where(
							SpecificationFuncionario.nome(nomeString)).or
							(SpecificationFuncionario.cpf(cpf)).or
							(SpecificationFuncionario.salario(salario)).or
							(SpecificationFuncionario.dataContratacao(data)));
			funcionarios.forEach(f -> System.out.println(f.toString()));

	}
}
