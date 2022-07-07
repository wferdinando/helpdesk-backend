package io.github.wferdinando.helpdesk.services;

import java.util.Arrays;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.github.wferdinando.helpdesk.domain.Chamado;
import io.github.wferdinando.helpdesk.domain.Cliente;
import io.github.wferdinando.helpdesk.domain.Tecnico;
import io.github.wferdinando.helpdesk.domain.enums.Perfil;
import io.github.wferdinando.helpdesk.domain.enums.Prioridade;
import io.github.wferdinando.helpdesk.domain.enums.Status;
import io.github.wferdinando.helpdesk.repositories.ChamadoRepository;
import io.github.wferdinando.helpdesk.repositories.ClienteRepository;
import io.github.wferdinando.helpdesk.repositories.TecnicoRepository;

@Service
@Profile(value = "test")
public class DBService {

	// Injeção de Dependendencia
	private TecnicoRepository tecnicoRepository;
	private ClienteRepository clienteRepository;
	private ChamadoRepository chamadoRepository;

	public DBService(TecnicoRepository tecnicoRepository, ClienteRepository clienteRepository,
			ChamadoRepository chamadoRepository) {
		this.tecnicoRepository = tecnicoRepository;
		this.clienteRepository = clienteRepository;
		this.chamadoRepository = chamadoRepository;
	}

	public void instanciaDB() {

		Tecnico tec1 = new Tecnico(null, "Willyan Ferdinando", "55525265036", "willyan@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cl1 = new Cliente(null, "Linus Torvalds", "38786730002", "torvalds@mail.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cl1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cl1));
		chamadoRepository.saveAll(Arrays.asList(c1));

	}

}
