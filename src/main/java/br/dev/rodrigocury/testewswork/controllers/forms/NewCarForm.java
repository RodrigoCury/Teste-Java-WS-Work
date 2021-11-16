package br.dev.rodrigocury.testewswork.controllers.forms;

import br.dev.rodrigocury.testewswork.entities.enums.FuelType;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class NewCarForm {

  @NotEmpty(message = "Model must not be Empty")
  @NotNull(message = "Model must not be null")
  private String model;

  @Min(value = 1800, message = "Year must be between 1800-2100")
  @Max(value = 2100, message = "Year must be between 1800-2100")
  @NotNull(message = "Year must not be null")
  private Short year;

  @NotNull(message = "fuelType must not be null")
  private FuelType fuelType;

  @Min(value = 1, message = "Must have at least 1 door")
  @Max(value = 4, message = "Must have a maximum of 4 door's")
  @NotNull(message = "Doors must not be null")
  private Short doors;

  @NotNull(message = "Cost must not be null")
  @Min(value = 0, message = "Must be a positive value")
  private BigDecimal cost;

  @NotNull(message = "Color must not be null")
  @NotEmpty(message = "Color must not be empty")
  private String color;

  @NotNull(message = "factoryId must not be null")
  @Min(value = 0, message = "Must be a positive value")
  private Long factoryId;




  public NewCarForm() {
  }

  public Long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(Long factoryId) {
    this.factoryId = factoryId;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Short getYear() {
    return year;
  }

  public void setYear(Short year) {
    this.year = year;
  }

  public FuelType getFuelType() {
    return fuelType;
  }

  public void setFuelType(FuelType fuelType) {
    this.fuelType = fuelType;
  }

  public Short getDoors() {
    return doors;
  }

  public void setDoors(Short doors) {
    this.doors = doors;
  }

  public BigDecimal getCost() {
    return cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}
