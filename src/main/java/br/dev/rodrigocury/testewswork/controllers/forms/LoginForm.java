package br.dev.rodrigocury.testewswork.controllers.forms;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginForm {
  @NotEmpty(message = "email must not be Empty")
  @NotNull(message = "email must not be null")
  private String email;

  @NotEmpty(message = "password must not be Empty")
  @NotNull(message = "password must not be null")
  private String password;

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public UsernamePasswordAuthenticationToken converter() {
    return new UsernamePasswordAuthenticationToken(email, password);
  }
}
