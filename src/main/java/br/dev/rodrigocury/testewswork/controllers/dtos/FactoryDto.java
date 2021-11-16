package br.dev.rodrigocury.testewswork.controllers.dtos;

import br.dev.rodrigocury.testewswork.entities.Car;
import br.dev.rodrigocury.testewswork.entities.Factory;

import java.util.HashSet;
import java.util.Set;

public class FactoryDto {
  private final Long id;
  private final String factoryName;
  private final String countryCode;
  private final Set<CarDto> cars;

  public FactoryDto(Factory factory) {
    this.id = factory.getId();
    this.factoryName = factory.getFactoryName();
    this.countryCode = factory.getCountryCode();
    this.cars = new HashSet<>(factory.getCars().stream().map(CarDto::new).toList());
  }

  public Long getId() {
    return id;
  }

  public String getFactoryName() {
    return factoryName;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public Set<CarDto> getCars() {
    return cars;
  }
}
