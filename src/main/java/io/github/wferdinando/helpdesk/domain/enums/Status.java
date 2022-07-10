package io.github.wferdinando.helpdesk.domain.enums;

public enum Status {

	// Declarar os enuns
	// criar os atributos cod, descricao
	// gerar os contrutores
	// criar um método static para verificar se existe o código, caso nao retornar
	// null
	// caso exista o código verificar se é um código válido

	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

	private Integer codigo;
	private String descricao;

	private Status(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Status toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Status s : Status.values()) {
			if (cod.equals(s.getCodigo())) {
				return s;
			}
		}
		throw new IllegalArgumentException("Status inválido!");
	}

}
