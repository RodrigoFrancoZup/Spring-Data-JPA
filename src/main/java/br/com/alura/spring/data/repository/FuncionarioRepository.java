package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer>{

	public List<Funcionario> findByNome(String nome);
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.dataContratacao = :data AND f.salario > :salario")
	public List<Funcionario>buscaFuncionarioPorNomeDataESalarioMaiorQue(String nome, LocalDate data, Double salario);
	
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao > :data", nativeQuery = true)
	public List<Funcionario> buscaFuncionarioPorDataSuperior(LocalDate data);
	
}
