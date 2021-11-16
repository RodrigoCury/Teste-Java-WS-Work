package br.dev.rodrigocury.testewswork.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Table(name = "factory")
@Entity
public class Factory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String factoryName;

  private String countryCode;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "factory")
  @JsonBackReference
  private Set<Car> cars;

  public Factory() {
  }

  public Factory(String factoryName, String countryCode) {
    this.factoryName = factoryName;
    this.countryCode = countryCode;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Set<Car> getCars() {
    return cars;
  }

  public void setCars(Set<Car> cars) {
    this.cars = cars;
  }
}