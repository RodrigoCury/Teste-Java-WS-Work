package br.dev.rodrigocury.testewswork.config.security;

import br.dev.rodrigocury.testewswork.entities.User;
import br.dev.rodrigocury.testewswork.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public AutenticacaoService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> usuario = userRepository.findByEmail(email);

    if (usuario.isEmpty()) throw new UsernameNotFoundException("Dados Inválidos");

    return usuario.get();
  }
}
