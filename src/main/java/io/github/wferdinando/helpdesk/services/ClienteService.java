package io.github.wferdinando.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.wferdinando.helpdesk.domain.Cliente;
import io.github.wferdinando.helpdesk.domain.Pessoa;
import io.github.wferdinando.helpdesk.domain.dtos.ClienteDTO;
import io.github.wferdinando.helpdesk.repositories.ClienteRepository;
import io.github.wferdinando.helpdesk.repositories.PessoaRepository;
import io.github.wferdinando.helpdesk.services.exceptions.DataIntegrityViolationException;
import io.github.wferdinando.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	final private ClienteRepository clienteRepository;
	final private PessoaRepository pessoaRepository;
	final private BCryptPasswordEncoder encoder;

	public ClienteService(ClienteRepository clienteRepository, PessoaRepository pessoaRepository, BCryptPasswordEncoder encoder) {
		this.clienteRepository = clienteRepository;
		this.pessoaRepository = pessoaRepository;
		this.encoder = encoder;
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente create(ClienteDTO clienteDTO) {
		clienteDTO.setId(null);
		clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));
		validaPorCpfEEmail(clienteDTO);
		Cliente cliente = new Cliente(clienteDTO);
		return clienteRepository.save(cliente);
	}

	public Cliente update(Integer id, ClienteDTO clienteDTO) {
		clienteDTO.setId(id);
		Cliente cliente = findById(id);
		validaPorCpfEEmail(clienteDTO);
		cliente = new Cliente(clienteDTO);
		return clienteRepository.save(cliente);
	}

	private void validaPorCpfEEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}

	public void delete(Integer id) {
		var cliente = findById(id);
		if (cliente.getChamados().size() > 0) {
			throw new DataIntegrityViolationException(
					"Cliente possui ordem de serviços em aberto, e não pode ser deletado!");
		}
		clienteRepository.deleteById(id);

	}

}
