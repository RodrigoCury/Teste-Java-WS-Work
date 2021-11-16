package br.dev.rodrigocury.testewswork.repositories;

import br.dev.rodrigocury.testewswork.controllers.dtos.CarDto;
import br.dev.rodrigocury.testewswork.entities.Car;
import br.dev.rodrigocury.testewswork.entities.enums.FuelType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

  @Query("SELECT C FROM Car C " +
      "INNER JOIN C.factory F " +
      "WHERE " +
      "F.factoryName LIKE :factoryName AND " +
      "(C.year BETWEEN :yearMin AND :yearMax) AND " +
      "(C.doors BETWEEN :doorsMin AND :doorsMax) AND " +
      "(C.cost BETWEEN :costMin AND :costMax) AND " +
      "C.color = :color" )
  Page<Car> findWithFilters(String factoryName,
                               Short yearMin, Short yearMax,
                               Short doorsMin, Short doorsMax,
                               BigDecimal costMin, BigDecimal costMax,
                               String color,
                               Pageable pageable);

  @Query("SELECT C FROM Car C " +
      "WHERE " +
      "(C.year BETWEEN :yearMin AND :yearMax) AND " +
      "(C.doors BETWEEN :doorsMin AND :doorsMax) AND " +
      "(C.cost BETWEEN :costMin AND :costMax) AND " +
      "C.color = :color" )
  Page<Car> findWithFilters(Short yearMin, Short yearMax,
                               Short doorsMin, Short doorsMax,
                               BigDecimal costMin, BigDecimal costMax,
                               String color,
                               Pageable pageable);

  @Query("SELECT C FROM Car C " +
      "INNER JOIN C.factory F " +
      "WHERE " +
      "F.factoryName LIKE :factoryName AND " +
      "(C.year BETWEEN :yearMin AND :yearMax) AND " +
      "(C.doors BETWEEN :doorsMin AND :doorsMax) AND " +
      "(C.cost BETWEEN :costMin AND :costMax) ")
  Page<Car> findWithFilters(String factoryName,
                               Short yearMin, Short yearMax,
                               Short doorsMin, Short doorsMax,
                               BigDecimal costMin, BigDecimal costMax,
                               Pageable pageable);

  @Query("SELECT C FROM Car C " +
      "WHERE " +
      "(C.year BETWEEN :yearMin AND :yearMax) AND " +
      "(C.doors BETWEEN :doorsMin AND :doorsMax) AND " +
      "(C.cost BETWEEN :costMin AND :costMax)")
  Page<Car> findWithFilters(Short yearMin, Short yearMax,
                               Short doorsMin, Short doorsMax,
                               BigDecimal costMin, BigDecimal costMax,
                               Pageable pageable);

  Optional<Car> findByFactory_IdAndModelAndAndYearAndFuelAndDoorsAndCostAndColor(Long id, String modelo, Short ano, FuelType combustivel, Short numPortas, BigDecimal valorFipe, String cor);
}