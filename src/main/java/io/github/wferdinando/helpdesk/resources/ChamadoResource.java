package io.github.wferdinando.helpdesk.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
