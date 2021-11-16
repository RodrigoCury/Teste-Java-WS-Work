package br.dev.rodrigocury.testewswork.controllers;

import br.dev.rodrigocury.testewswork.controllers.dtos.CarDto;
import br.dev.rodrigocury.testewswork.controllers.forms.NewCarForm;
import br.dev.rodrigocury.testewswork.entities.Car;
import br.dev.rodrigocury.testewswork.entities.Factory;
import br.dev.rodrigocury.testewswork.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/car")
public class CarController {

  private final CarService carService;

  @Autowired
  public CarController(CarService carService) {
    this.carService = carService;
  }

  @PostMapping
  public ResponseEntity<Car> addCarToDB(@RequestBody @Valid NewCarForm form, UriComponentsBuilder builder){
    Car car = carService.addCar(form);

    URI uri = builder.path("/car/{id}").buildAndExpand(car.getId()).toUri();

    return ResponseEntity.created(uri).body(car);
  }

  @GetMapping
  public ResponseEntity<Page<CarDto>> getCarList(
      @RequestParam(value = "factory", required = false) String factoryName,
      @RequestParam(value = "yearMin", defaultValue = "1800") Short yearMin,
      @RequestParam(value = "yearMax", defaultValue = "2100") Short yearMax,
      @RequestParam(value = "doorsMin", defaultValue = "1") Short doorsMin,
      @RequestParam(value = "doorsMax", defaultValue = "4") Short doorsMax,
      @RequestParam(value = "costMin", defaultValue = "0") BigDecimal costMin,
      @RequestParam(value = "costMax", defaultValue = "999999999") BigDecimal costMax,
      @RequestParam(value = "color", required = false) String color,
      @PageableDefault(sort = "id") Pageable pageable
  ){
    Page<CarDto> cars = carService.findCarList(factoryName, yearMin, yearMax, doorsMin, doorsMax, costMin, costMax, color, pageable);

    return ResponseEntity.ok(cars);
  }

  @GetMapping("{id}")
  public ResponseEntity<Car> getCarById(@PathVariable("id") Long id){

    Car car = carService.getCarById(id);

    return ResponseEntity.ok(car);
  }

  @PutMapping("{id}")
  @Transactional
  public ResponseEntity<Car> updateCarData(@PathVariable("id") Long id, @RequestBody @Valid NewCarForm form){
    Car car = carService.updateCarData(id, form);

    return ResponseEntity.ok(car);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> deletaCarData(@PathVariable("id") Long id){
    carService.deleteCarFromDB(id);
    return ResponseEntity.noContent().build();
  }

}
