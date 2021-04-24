package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.projecao.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {

	public List<Funcionario> findByNome(String nome);

	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.dataContratacao = :data AND f.salario > :salario")
	public List<Funcionario> buscaFuncionarioPorNomeDataESalarioMaiorQue(String nome, LocalDate data, Double salario);

	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao > :data", nativeQuery = true)
	public List<Funcionario> buscaFuncionarioPorDataSuperior(LocalDate data);

	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
	public List<FuncionarioProjecao> relatorioDeFuncionarioComIdNomeESalario();
}
