package SoulCode.Services.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Repositories.FuncionarioRepository;


@Service
public class FuncionarioService {

	//injeção de dependência
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	//serviço para buscar todos os funcionarios cadastrados
		public List<Funcionario> mostrarTodosFuncionarios(){
		return funcionarioRepository.findAll();
	}
	
	//findById busca um funcionario especifico pelo seu id
		public Funcionario mostrarUmFuncionario(Integer idFuncionario) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
		return funcionario.orElseThrow();
		}
		
		//findByEmail
		public Funcionario mostrarFuncionarioPeloEmail(String email) {
			Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(email);
			return funcionario.orElseThrow();
		}
		
		//findByNomeAndEmail
		public Funcionario mostrarFuncionarioPeloNomeEEmail(String nome, String email) {
			Optional<Funcionario> funcionario = funcionarioRepository.findByNomeAndEmail(nome, email);
			return funcionario.orElseThrow();
		}
		
		
		// save insere um novo registro na tabela do nosso db 
		// o metodo save pega os dados novo funcionario salva no bd e gera um id pra esse funcionario
		public Funcionario inserirFuncionario(Funcionario funcionario) {
			//por precaução vamos limpar o compo de id do funcionario
			funcionario.setIdFuncionario(null); 
			return funcionarioRepository.save(funcionario);
		}
		
		//editar um funcionario ja cadastrado
		public Funcionario editarFuncionario(Funcionario funcionario) {
			mostrarUmFuncionario(funcionario.getIdFuncionario());
			return funcionarioRepository.save(funcionario);
		}
		
		//deletar um funcionario pelo seu id
		public void excluirFuncionario(Integer idFuncionario) {
			mostrarUmFuncionario(idFuncionario);
			funcionarioRepository.deleteById(idFuncionario);
		}
		
		
		
}

