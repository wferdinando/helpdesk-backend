package io.github.wferdinando.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.wferdinando.helpdesk.domain.Chamado;
import io.github.wferdinando.helpdesk.domain.dtos.ChamadoDTO;
import io.github.wferdinando.helpdesk.services.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

	private final ChamadoService chamadoService;

	public ChamadoResource(ChamadoService chamadoService) {
		this.chamadoService = chamadoService;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable(value = "id") Integer id) {
		Chamado chamado = chamadoService.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
	}

	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll() {
		List<Chamado> listaChamado = chamadoService.findAll();
		List<ChamadoDTO> listaChamadoDTO = listaChamado.stream().map(obj -> new ChamadoDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listaChamadoDTO);
	}

	@PostMapping
	public ResponseEntity<ChamadoDTO> create(@RequestBody @Valid ChamadoDTO chamadoDTO) {
		Chamado chamado = chamadoService.create(chamadoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> update(@PathVariable(value = "id") Integer id,
			@RequestBody @Valid ChamadoDTO chamadoDTO) {
		Chamado chamado = chamadoService.update(id, chamadoDTO);
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
	}

}
