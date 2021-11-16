package br.dev.rodrigocury.testewswork.config.security;

import br.dev.rodrigocury.testewswork.entities.User;
import br.dev.rodrigocury.testewswork.repositories.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {
  private final TokenService tokenService;
  private final UserRepository userRepository;

  public AutenticacaoTokenFilter(TokenService tokenService, UserRepository userRepository) {
    this.tokenService = tokenService;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    String token = recuperarToken(request);

    boolean valido = tokenService.isTokenValido(token);

    if(valido){
      autenticarCliente(token);
    }

    filterChain.doFilter(request, response);
  }

  private void autenticarCliente(String token) {
    Long id = tokenService.getIdUsuario(token);

    Optional<User> usuarioFound = userRepository.findById(id);
    if (usuarioFound.isEmpty()) {
      throw new EntityNotFoundException("Usuário não encontrado");
    }
    User usuario = usuarioFound.get();

    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  private String recuperarToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if(token == null || !token.startsWith("Bearer ")){
      return null;
    }

    return token.substring(7);
  }


}
