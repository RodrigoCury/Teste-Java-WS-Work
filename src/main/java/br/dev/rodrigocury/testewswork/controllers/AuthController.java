package br.dev.rodrigocury.testewswork.controllers;

import br.dev.rodrigocury.testewswork.config.security.TokenService;
import br.dev.rodrigocury.testewswork.controllers.dtos.AuthDTO;
import br.dev.rodrigocury.testewswork.controllers.forms.LoginForm;
import br.dev.rodrigocury.testewswork.controllers.forms.NewUserForm;
import br.dev.rodrigocury.testewswork.entities.User;
import br.dev.rodrigocury.testewswork.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;
  private final UserRepository userRepository;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
    this.userRepository = userRepository;
  }

  @PostMapping("auth")
  public ResponseEntity<AuthDTO> autenticar(@RequestBody @Valid LoginForm form){

    UsernamePasswordAuthenticationToken dadosLogin = form.converter();
    try {
      Authentication authenticate = authenticationManager.authenticate(dadosLogin);

      String token = tokenService.gerarToken(authenticate);
      return ResponseEntity.ok(new AuthDTO(token, "Bearer"));
    } catch (AuthenticationException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping("signup")
  public ResponseEntity<AuthDTO> newUser(@RequestBody @Valid NewUserForm form){
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    User user = new User();
    user.setUsername(form.getUsername());
    user.setEmail(form.getEmail());
    user.setPassword(encoder.encode(form.getPassword()));

    try {
      userRepository.save(user);

      UsernamePasswordAuthenticationToken dadosLogin = form.converter();

      Authentication authenticate = authenticationManager.authenticate(dadosLogin);

      String token = tokenService.gerarToken(authenticate);
      return ResponseEntity.ok(new AuthDTO(token, "Bearer"));

    } catch (DataIntegrityViolationException ex){
      throw new IllegalArgumentException(String.format("Email %s is already in use", form.getEmail()));
    } catch (AuthenticationException ex) {
      return ResponseEntity.badRequest().build();
    }

  }

}
