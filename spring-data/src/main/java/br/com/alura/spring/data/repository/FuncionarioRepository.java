package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;

@Repository                                   //Paging Repository para acessar por paginação a query
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, 
		JpaSpecificationExecutor<Funcionario> {
	// findBy faz pesquisa por algum atributo da classe Funcionario
	List<Funcionario> findByNome(String nome);

	//usando JPQL para criar query de busca 
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
			+ "AND f.salario >= :salario AND f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data);
	
	
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data",
			nativeQuery = true)
	List<Funcionario> findContratacaoMaior(LocalDate data);
	
	//consulta de projeção apenas apresentando atributos necessarios passando a interface FuncionarioProjecao
	@Query(value = "SELECT f.id, f.salario FROM funcionarios f", 
			nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
}
