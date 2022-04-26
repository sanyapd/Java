package SoulCode.Services.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Models.Servico;
import SoulCode.Services.Models.StatusServico;
import SoulCode.Services.Repositories.FuncionarioRepository;
import SoulCode.Services.Repositories.ServicoRepository;

@Service
public class ServicoService {

	//injecao de dependencia - chamando o repository de servicos
	@Autowired
	ServicoRepository servicoRepository;
	
	//autowired pra cada injeção
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	//findAll dos servicos cadastrados
	public List<Servico> mostrarTodosServicos(){
		return servicoRepository.findAll();
	}
	
	//findById - busca o serviço pelo seu id
	public Servico mostrarUmServico(Integer idServico) {
		Optional<Servico> servico = servicoRepository.findById(idServico);
			return servico.orElseThrow();
	}
	
	//findByFuncionario busca todos os serviços de um determinado funcionario
	public List<Servico> buscarServicosDoFuncionario(Integer idFuncionario){
		Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
		return servicoRepository.findByFuncionario(funcionario);
	}
	
	////findByData busca todos os serviços por data
	public List<Servico> buscarServicosPorData(Date dataEntrada){
		return servicoRepository.findByDataEntrada(dataEntrada);
	}
	
	public List<Servico> buscarServicoPorIntervaloData(Date data1, Date data2){
		return servicoRepository.findByIntervaloData(data1, data2);
	}
	
	//busca por status
	public List<Servico> buscarServicoPeloStatus(String status){
		return servicoRepository.findByStatus(status);
	}
	
	//busca o serviço que não foi atribuido a um funcionario
	public List<Servico> buscarServicoSemFuncionario(){
		return servicoRepository.findByIdFuncionarioNull();
	}
	
	//metodo para cadastro de um servico
	//no momento de cadastro do novo serviço o status tem que ficar como recebido
	//no momento do cadastro do novo serviço o idFuncionario tem que ficar null
	public Servico inserirServico(Servico servico) {
		servico.setIdServico(null);
		servico.setStatus(StatusServico.RECEBIDO);
		servico.setFuncionario(null);
		return servicoRepository.save(servico);
	}
	
	//metodo para atribuir um determinado servico pra um determinado funcionario
	//o servico precisa receber o status de atribuido
	public Servico atribuirFuncionario(Integer idServico, Integer idFuncionario) {
		//buscamos o funcionario pra ser atribuido ao servico atraves do seu id
		Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
		//buscamos um servico para o qual esse funcionario vai ser atribuido
		Servico servico = mostrarUmServico(idServico);
		if(servico.getFuncionario() != null) {
		servico.setIdServico(idServico);
		servico.setFuncionario(funcionario.get());
		servico.setStatus(StatusServico.ATRIBUIDO);
		}
		return servicoRepository.save(servico);
		
	}
	
	//metodo para mudar o status do servico para concluido
	public Servico concluirServico(Integer idServico) {
		Servico servico = mostrarUmServico(idServico);
		//servico.setIdServico(idServico);
		if (servico.getFuncionario() != null) {
		servico.setStatus(StatusServico.CONCLUIDO);
		}
		return servicoRepository.save(servico);
	}
	
	
	
	
}
