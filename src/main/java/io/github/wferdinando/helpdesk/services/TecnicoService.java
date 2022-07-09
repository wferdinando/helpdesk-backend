package io.github.wferdinando.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.wferdinando.helpdesk.domain.Pessoa;
import io.github.wferdinando.helpdesk.domain.Tecnico;
import io.github.wferdinando.helpdesk.domain.dtos.TecnicoDTO;
import io.github.wferdinando.helpdesk.repositories.PessoaRepository;
import io.github.wferdinando.helpdesk.repositories.TecnicoRepository;
import io.github.wferdinando.helpdesk.services.exceptions.DataIntegrityViolationException;
import io.github.wferdinando.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	final private TecnicoRepository tecnicoRepository;
	final private PessoaRepository pessoaRepository;

	public TecnicoService(TecnicoRepository tecnicoRepository, PessoaRepository pessoaRepository) {
		this.tecnicoRepository = tecnicoRepository;
		this.pessoaRepository = pessoaRepository;
	}

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(null); // para ter certeza que vai vir um id nulo
		validaPorCpfEEmail(tecnicoDTO);
		Tecnico novoTecnico = new Tecnico(tecnicoDTO);
		return tecnicoRepository.save(novoTecnico);
	}

	public Tecnico update(Integer id, TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(id); // seta o id
		Tecnico oldObj = findById(id); // busca o id no banco
		validaPorCpfEEmail(tecnicoDTO); // valida se os dados são corretos
		oldObj = new Tecnico(tecnicoDTO); // cria um novo objeto
		return tecnicoRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Tecnico tecnico = findById(id);
		if (tecnico.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordem de serviços, e não pode ser deletado!");
		}
		tecnicoRepository.deleteById(id);
	}

	private void validaPorCpfEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}
}
