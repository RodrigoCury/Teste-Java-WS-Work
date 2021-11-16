package br.dev.rodrigocury.testewswork.controllers.forms;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class NewFactoryForm {

  @NotEmpty(message = "factoryName must not be Empty")
  @NotNull(message = "factoryName must not be null")
  @Length(min = 2, max = 50, message = "factoryName must have between 2 and 50 characters")
  private String factoryName;

  @NotEmpty(message = "countryCode must not be Empty")
  @NotNull(message = "countryCode must not be null")
  @Pattern(regexp = "^[A-Z]{2}", message = "countryCode must have only 2 Capital Letters")
  private String countryCode;

  public NewFactoryForm() {
  }

  public String getFactoryName() {
    return factoryName;
  }

  public void setFactoryName(String factoryName) {
    this.factoryName = factoryName;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }
}
