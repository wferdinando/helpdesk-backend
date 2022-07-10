package io.github.wferdinando.helpdesk.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.wferdinando.helpdesk.domain.Pessoa;
import io.github.wferdinando.helpdesk.repositories.PessoaRepository;
import io.github.wferdinando.helpdesk.security.UserSS;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private final PessoaRepository pessoaRepository;

	public UserDetailServiceImpl(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Pessoa> user = pessoaRepository.findByEmail(email);
		if (user.isPresent()) {
			return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getSenha(), user.get().getPerfis());
		}
		throw new UsernameNotFoundException(email);
	}

}
