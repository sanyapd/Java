package SoulCode.Services.Models;

public enum StatusServico {
	
	RECEBIDO("Recebido"),
	ATRIBUIDO("Atribuido"),
	CONCLUIDO("Concluido");
	
	private String descricao;

	//construtor do ENUM
	private StatusServico(String descricao) {
		this.descricao = descricao;
	}

	//get do atributo descricao	
	public String getDescricao() {
		return descricao;
	}

}
