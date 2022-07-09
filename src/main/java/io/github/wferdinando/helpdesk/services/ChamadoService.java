package io.github.wferdinando.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.wferdinando.helpdesk.domain.Chamado;
import io.github.wferdinando.helpdesk.repositories.ChamadoRepository;
import io.github.wferdinando.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	private ChamadoRepository chamadoRepository;

	public ChamadoService(ChamadoRepository chamadoRepository) {
		this.chamadoRepository = chamadoRepository;
	}

	public Chamado findById(Integer id) {
		Optional<Chamado> chamado = chamadoRepository.findById(id);
		return chamado.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! Id:" + id));
	}

	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}

}
