package br.com.alura.spring.data.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.spring.data.orm.Funcionario;

public class SpecificationFuncionario {

	public static Specification<Funcionario> nome(String nome) {
		return (root, criteriaQuery, CriteriaBuilder) -> CriteriaBuilder.like(root.get("nome"), "%" + nome + "%");
		// O primeiro nome refere-se ao atributo de funcionario
		// O segundo nome refere-se ao filtro que o usuário digitou
	}
	
	public static Specification<Funcionario> cpf(String cpf) {
		return (root, criteriaQuery, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("cpf"), cpf);
		// O primeiro nome refere-se ao atributo de funcionario
		// O segundo nome refere-se ao filtro que o usuário digitou
	}
	
	public static Specification<Funcionario> salario(Double salario) {
		return (root, criteriaQuery, CriteriaBuilder) -> CriteriaBuilder.greaterThan(root.get("salario"), salario);
		// O primeiro nome refere-se ao atributo de funcionario
		// O segundo nome refere-se ao filtro que o usuário digitou
	}
	
	public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao) {
		return (root, criteriaQuery, CriteriaBuilder) -> CriteriaBuilder.greaterThan(root.get("dataContratacao"), dataContratacao);
		// O primeiro nome refere-se ao atributo de funcionario
		// O segundo nome refere-se ao filtro que o usuário digitou
	}
}
