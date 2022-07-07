package io.github.wferdinando.helpdesk.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.wferdinando.helpdesk.domain.Tecnico;
import io.github.wferdinando.helpdesk.repositories.TecnicoRepository;

@Service
public class TecnicoService {

	final private TecnicoRepository tecnicoRepository;

	public TecnicoService(TecnicoRepository tecnicoRepository) {
		this.tecnicoRepository = tecnicoRepository;
	}
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElse(null);
	}

}
