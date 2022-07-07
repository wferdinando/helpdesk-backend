package io.github.wferdinando.helpdesk.domain.enums;

public enum Prioridade {

	// Declarar os enuns
	// criar os atributos cod, descricao
	// gerar os contrutores
	// criar um método static para verificar se existe o código, caso nao retornar
	// null
	// caso exista o código verificar se é um código válido

	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");

	private Integer codigo;
	private String descricao;

	private Prioridade(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Prioridade toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Prioridade p : Prioridade.values()) {
			if (cod.equals(p.getCodigo())) {
				return p;
			}
		}
		throw new IllegalArgumentException("Prioridáde é inválida!");
	}

}
