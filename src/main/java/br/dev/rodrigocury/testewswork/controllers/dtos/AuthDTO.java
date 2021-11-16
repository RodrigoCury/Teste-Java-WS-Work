package br.dev.rodrigocury.testewswork.controllers.dtos;

public class AuthDTO {
  private final String token;
  private final String tipo;

  public String getToken() {
    return token;
  }

  public String getTipo() {
    return tipo;
  }

  public AuthDTO(String token, String tipo) {
    this.tipo = tipo;
    this.token = token;
  }
}
