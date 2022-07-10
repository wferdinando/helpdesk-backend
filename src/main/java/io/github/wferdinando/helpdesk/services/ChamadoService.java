package io.github.wferdinando.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.wferdinando.helpdesk.domain.Chamado;
import io.github.wferdinando.helpdesk.domain.dtos.ChamadoDTO;
import io.github.wferdinando.helpdesk.domain.enums.Prioridade;
import io.github.wferdinando.helpdesk.domain.enums.Status;
import io.github.wferdinando.helpdesk.repositories.ChamadoRepository;
import io.github.wferdinando.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	private ChamadoRepository chamadoRepository;
	private TecnicoService tecnicoService;
	private ClienteService clienteService;

	public ChamadoService(ChamadoRepository chamadoRepository, TecnicoService tecnicoService,
			ClienteService clienteService) {
		this.chamadoRepository = chamadoRepository;
		this.tecnicoService = tecnicoService;
		this.clienteService = clienteService;
	}

	public Chamado findById(Integer id) {
		Optional<Chamado> chamado = chamadoRepository.findById(id);
		return chamado.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! Id:" + id));
	}

	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}

	public Chamado create(ChamadoDTO chamadoDTO) {
		return chamadoRepository.save(novoChamado(chamadoDTO));
	}
	
	public Chamado update(Integer id, ChamadoDTO chamadoDTO) {
		chamadoDTO.setId(id);
		var chamadoOld  = findById(id);
		chamadoOld = novoChamado(chamadoDTO);
		return chamadoRepository.save(chamadoOld);
	}

	private Chamado novoChamado(ChamadoDTO chamadoDTO) {
		var tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
		var cliente = clienteService.findById(chamadoDTO.getCliente());
		Chamado chamado = new Chamado();
		if (chamadoDTO.getId() != null) {
			chamado.setId(chamadoDTO.getId());
		}
		
		if(chamadoDTO.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
		chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
		chamado.setTitulo(chamadoDTO.getTitulo());
		chamado.setObservacoes(chamadoDTO.getObservacoes());
		return chamado;

	}

	

}
