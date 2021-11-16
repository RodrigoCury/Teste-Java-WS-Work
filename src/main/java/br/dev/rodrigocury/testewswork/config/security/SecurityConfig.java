package br.dev.rodrigocury.testewswork.config.security;

import br.dev.rodrigocury.testewswork.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final AutenticacaoService autenticacaoService;
  private final TokenService tokenService;
  private final UserRepository userRepository;

  @Autowired
  public SecurityConfig(AutenticacaoService autenticacaoService, TokenService tokenService, UserRepository userRepository) {
    this.autenticacaoService = autenticacaoService;
    this.tokenService = tokenService;
    this.userRepository = userRepository;
  }

  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    super.configure(web);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    configuraRotas(http);
    configuraAutenticacao(http);
  }

  private void configuraAutenticacao(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(new AutenticacaoTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);


    http.headers().frameOptions().disable();
  }

  private void configuraRotas(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .mvcMatchers(HttpMethod.POST, "/auth", "/signup").permitAll()
        .mvcMatchers("/console","/console/**").permitAll() // Console H2
        .anyRequest().authenticated();
  }



}
