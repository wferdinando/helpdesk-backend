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

import io.github.wferdinando.helpdesk.domain.Cliente;
import io.github.wferdinando.helpdesk.domain.dtos.ClienteDTO;
import io.github.wferdinando.helpdesk.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteRersource {

	private final ClienteService clienteService;

	public ClienteRersource(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable(value = "id") Integer id) {
		Cliente cliente = clienteService.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(cliente));
	}

	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> listaCliente = clienteService.findAll();
		List<ClienteDTO> listaCliDTO = listaCliente.stream().map(cliDTO -> new ClienteDTO(cliDTO))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listaCliDTO);

	}

	@PostMapping()
	public ResponseEntity<ClienteDTO> create(@RequestBody @Valid ClienteDTO clienteDTO) {
		Cliente novoCliente = clienteService.create(clienteDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoCliente.getId())
				.toUri(); // pega o ID do novo Objeto
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable(value = "id") Integer id,
			@RequestBody @Valid ClienteDTO clienteDTO) {
		Cliente cliente = clienteService.update(id, clienteDTO);
		return ResponseEntity.ok().body(new ClienteDTO(cliente));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable(value = "id") Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();

	}
}
