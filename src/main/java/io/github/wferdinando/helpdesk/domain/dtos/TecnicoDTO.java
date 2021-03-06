package io.github.wferdinando.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.github.wferdinando.helpdesk.domain.Tecnico;
import io.github.wferdinando.helpdesk.domain.enums.Perfil;

public class TecnicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotNull(message = "O Campo NOME é obrigatório!")
	private String nome;
	
	@NotNull(message = "O Campo CPF é obrigatório!")
	private String cpf;
	
	@NotNull(message = "O Campo EMAIL é obrigatório!")
	private String email;
	
	@NotNull(message = "O Campo SENHA é obrigatório!")
	private String senha;

	private Set<Integer> perfis = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCriacao = LocalDate.now();

	public TecnicoDTO() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public TecnicoDTO(Tecnico tecncioDTO) {
		super();
		this.id = tecncioDTO.getId();
		this.nome = tecncioDTO.getNome();
		this.cpf = tecncioDTO.getCpf();
		this.email = tecncioDTO.getEmail();
		this.senha = tecncioDTO.getSenha();
		this.perfis = tecncioDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = tecncioDTO.getDataCriacao();
		addPerfil(Perfil.CLIENTE);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
