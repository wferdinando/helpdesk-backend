package io.github.wferdinando.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
		Tecnico novoTecnico = tecnicoService.create(tecnicoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoTecnico.getId())
				.toUri(); // pega o ID do novo Objeto
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable(name = "id") Integer id,
			@Valid @RequestBody TecnicoDTO tecnicoDTO) {
		Tecnico obj = tecnicoService.update(id, tecnicoDTO);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> delete(@PathVariable(name = "id") Integer id) {
		tecnicoService.delete(id);
		return ResponseEntity.noContent().build();

	}

}