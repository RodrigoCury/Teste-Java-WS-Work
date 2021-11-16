package br.dev.rodrigocury.testewswork.controllers.forms;

import br.dev.rodrigocury.testewswork.controllers.forms.customvalidators.EmailValidator;
import br.dev.rodrigocury.testewswork.controllers.forms.customvalidators.PasswordValidator;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewUserForm {

  @NotEmpty(message = "username must not be Empty")
  @NotNull(message = "username must not be null")
  @Length(min = 2, max = 50, message = "username must have between 2 and 50 characters")
  private String username;

  @EmailValidator
  private String email;

  @PasswordValidator
  private String password;

  public NewUserForm() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UsernamePasswordAuthenticationToken converter() {
    return new UsernamePasswordAuthenticationToken(email, password);
  }
}
