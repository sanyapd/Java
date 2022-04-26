package SoulCode.Services.Repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Models.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Integer>{
	
	//esse metodo vai trazer todos os servicos de um determinado funcionario
	//como na tabela de servico nos temos o atributo funcionario devemos
	//fazer uso do findBy. o nome do metodo sera findByFuncionario e recebe
	//como parametro um optional de funcionario
	List<Servico> findByFuncionario(Optional<Funcionario> funcionario);
	
	//esse metodo vai trazer todas as datas de um serviço
	List<Servico> findByDataEntrada(Date dataEntrada);
	
	//esse metodo retorna um intervalo de data implementando o mysql no java
	@Query(value = "SELECT * FROM servico WHERE data_entrada BETWEEN :data1 AND :data2", nativeQuery = true)
	List<Servico> findByIntervaloData(Date data1, Date data2);	

	//busca o servico de acordo com seu status
	@Query(value = "SELECT * FROM  servico WHERE status = :status", nativeQuery = true)
	List<Servico> findByStatus(String status);
	
	//busca o serviço que não foi atribuido a um funcionario
	@Query(value = "SELECT * FROM  servico WHERE id_funcionario is null", nativeQuery = true)
	List<Servico> findByIdFuncionarioNull();
	
	
}
