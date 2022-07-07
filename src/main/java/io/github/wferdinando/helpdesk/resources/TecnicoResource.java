package io.github.wferdinando.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.wferdinando.helpdesk.domain.Tecnico;
import io.github.wferdinando.helpdesk.domain.dtos.TecnicoDTO;
import io.github.wferdinando.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	final private TecnicoService tecnicoService;

	public TecnicoResource(TecnicoService tecnicoService) {
		this.tecnicoService = tecnicoService;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable(name = "id") Integer id) {
		Tecnico tecnico = tecnicoService.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
	}

	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		List<Tecnico> listTecnico = tecnicoService.findAll();
		List<TecnicoDTO> listTecnicoDTO = listTecnico.stream().map(obj -> new TecnicoDTO(obj))
				.collect(Collectors.toList()); // convert um Objeto para um ObjetoDTO
		return ResponseEntity.ok().body(listTecnicoDTO);
	}

}
