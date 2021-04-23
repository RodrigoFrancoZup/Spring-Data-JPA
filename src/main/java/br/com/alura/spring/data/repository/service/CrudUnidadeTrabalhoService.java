package br.com.alura.spring.data.repository.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	
	// Controla o status do loop do menu
	private boolean repetirLoop = true;

	Scanner sc = new Scanner(System.in);
	
	private UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	
	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}


	public void menuUnidadeTrabalho() {
		while (repetirLoop) {
			
			System.out.println("Esolha uma opção:");
			System.out.println("0 - Sair");
			System.out.println("1 - Cadastrar Unidade de Trabalho");
			System.out.println("2 - Atualizar Unidade de Trabalho");
			System.out.println("3 - Visualizar Unidades de Trabalho");
			System.out.println("4 - Deletar Unidade de Trabalho");
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
		System.out.println("Digite o ID da Unidade que desejas Deletar:");
		Integer id = Integer.parseInt(sc.nextLine());
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Registro apagado...");
		
	}


	private void visualizar() {
		List<UnidadeTrabalho> unidade = (List<UnidadeTrabalho>) unidadeTrabalhoRepository.findAll();
		System.out.println("Todas Unidade de Trabalho: ");
		for (UnidadeTrabalho unidadeTrabalho : unidade) {
			System.out.println(unidadeTrabalho.toString());
		}
		
	}


	private void atualizar() {
		System.out.println("Digita o ID da Unidade que desejas atualizar:");
		Integer id = Integer.parseInt(sc.nextLine());
		System.out.println("Digita a nova descrição:");
		String descricao = sc.nextLine();
		System.out.println("Digita o novo endereço da Unidade:");
		String endereco = sc.nextLine();
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Registro atualizado...");
		
	}


	private void cadastrar() {
		System.out.println("Digita a descrição da Unidade que desejas cadastrar:");
		String descricao = sc.nextLine();
		System.out.println("Digita o endereço da Unidade:");
		String endereco = sc.nextLine();
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Registro cadastrado...");
	}
}
