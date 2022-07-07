package io.github.wferdinando.helpdesk.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.wferdinando.helpdesk.domain.Tecnico;
import io.github.wferdinando.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	final private TecnicoService tecnicoService;

	public TecnicoResource(TecnicoService tecnicoService) {
		this.tecnicoService = tecnicoService;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Tecnico> findById(@PathVariable(name = "id") Integer id) {
		Tecnico tecnico = tecnicoService.findById(id);
		return ResponseEntity.ok().body(tecnico);
	}

}
