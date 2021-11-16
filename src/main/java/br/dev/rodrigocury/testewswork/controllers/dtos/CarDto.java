package br.dev.rodrigocury.testewswork.controllers.dtos;

import br.dev.rodrigocury.testewswork.entities.Car;
import br.dev.rodrigocury.testewswork.entities.enums.FuelType;

import java.math.BigDecimal;

public class CarDto {
  private final Long id;
  private final String factoryName;
  private final String model;
  private final Short year;
  private final FuelType fuel;
  private final Short doors;
  private final BigDecimal cost;
  private final String color;

  public CarDto(Car car) {
    this.id = car.getId();
    this.factoryName = car.getFactory().getFactoryName();
    this.model = car.getModel();
    this.year = car.getYear();
    this.fuel = car.getFuel();
    this.doors = car.getDoors();
    this.cost = car.getCost();
    this.color = car.getColor();
  }

  public Long getId() {
    return id;
  }

  public String getFactoryName() {
    return factoryName;
  }

  public String getModel() {
    return model;
  }

  public Short getYear() {
    return year;
  }

  public FuelType getFuel() {
    return fuel;
  }

  public Short getDoors() {
    return doors;
  }

  public BigDecimal getCost() {
    return cost;
  }

  public String getColor() {
    return color;
  }
}
