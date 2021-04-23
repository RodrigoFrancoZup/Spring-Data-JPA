package br.com.alura.spring.data.repository.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {

	// Controla o status do loop do menu
	private boolean repetirLoop = true;

	Scanner sc = new Scanner(System.in);

	private CargoRepository cargorepository;

	public CrudCargoService(CargoRepository cargorepository) {
		this.cargorepository = cargorepository;
	}

	public void menuCargo() {

		while (repetirLoop) {
			System.out.println("Esolha uma opção:");
			System.out.println("0 - Sair");
			System.out.println("1 - Cadastrar Cargo");
			System.out.println("2 - Atualizar Cargo");
			System.out.println("3 - Visualizar Cargos");
			System.out.println("4 - Deletar Cargo");
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
		System.out.println("Digite o id do Cargo que desejas deletar: ");
		Integer id = Integer.parseInt(sc.nextLine());
		cargorepository.deleteById(id);
		System.out.println("Registro apagado...");
	}

	private void visualizar() {
		System.out.println("Todos Cargos: ");
		List<Cargo> cargos = (List<Cargo>) cargorepository.findAll();
		for (Cargo cargo : cargos) {
			System.out.println(cargo.toString());
		}

	}

	private void atualizar() {
		System.out.println("Digite o id do Cargo que desejas atualizar: ");
		Integer id = Integer.parseInt(sc.nextLine());
		System.out.println("Digite a nova descrição: ");
		String descricao = sc.nextLine();
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargorepository.save(cargo);
		System.out.println("Registro atualizado...");
	}

	private void cadastrar() {
		System.out.println("Digite o nome do cargo:");
		String nome = sc.nextLine();
		Cargo cargo = new Cargo();
		cargo.setDescricao(nome);
		cargorepository.save(cargo);
		System.out.println("Registro cadastrado...");
	}

}
