package br.dev.rodrigocury.testewswork.services;

import br.dev.rodrigocury.testewswork.controllers.dtos.CarDto;
import br.dev.rodrigocury.testewswork.controllers.forms.NewCarForm;
import br.dev.rodrigocury.testewswork.entities.Car;
import br.dev.rodrigocury.testewswork.entities.Factory;
import br.dev.rodrigocury.testewswork.repositories.CarRepository;
import br.dev.rodrigocury.testewswork.repositories.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CarService {

  private final CarRepository carRepository;
  private final FactoryRepository factoryRepository;

  @Autowired
  public CarService(CarRepository carRepository, FactoryRepository factoryRepository) {
    this.carRepository = carRepository;
    this.factoryRepository = factoryRepository;
  }

  public Car addCar(NewCarForm form) {
    Optional<Factory> factoryOpt = factoryRepository.findById(form.getFactoryId());

    if(factoryOpt.isEmpty()) throw new EntityNotFoundException(String.format("Factory with id %d not found", form.getFactoryId()));

    Car car = new Car(
        factoryOpt.get(),
        form.getModel(),
        form.getYear(),
        form.getFuelType(),
        form.getDoors(),
        form.getCost(),
        form.getColor()
    );

    carRepository.save(car);

    return car;
  }

  public Page<CarDto> findCarList(
      String factoryName,
      Short yearMin, Short yearMax,
      Short doorsMin, Short doorsMax,
      BigDecimal costMin, BigDecimal costMax,
      String color,
      Pageable pageable) {

    Page<Car> carDtos;

    if (factoryName != null && color !=null){
      carDtos = carRepository.findWithFilters(factoryName, yearMin, yearMax, doorsMin, doorsMax, costMin, costMax, color, pageable);
    } else if (factoryName != null){
      carDtos = carRepository.findWithFilters(factoryName, yearMin, yearMax, doorsMin, doorsMax, costMin, costMax, pageable);
    } else if (color != null){
      carDtos = carRepository.findWithFilters(yearMin, yearMax, doorsMin, doorsMax, costMin, costMax, color, pageable);
    } else {
      carDtos = carRepository.findWithFilters(yearMin, yearMax, doorsMin, doorsMax, costMin, costMax, pageable);
    }

    return carDtos.map(CarDto::new);
  }

  @Transactional
  public Car getCarById(Long id) {
    Optional<Car> car = carRepository.findById(id);

    if(car.isEmpty()) throw new EntityNotFoundException(String.format("Car with id %d not found", id));

    return car.get();
  }

  @Transactional
  public Car updateCarData(Long id, NewCarForm form) {
    Optional<Car> carOpt = carRepository.findById(id);

    if(carOpt.isEmpty()) throw new EntityNotFoundException(String.format("Car with id %d not found", id));

    Car car = carOpt.get();

    if (!car.getFactory().getId().equals(form.getFactoryId())){
      Optional<Factory> factoryOpt = factoryRepository.findById(id);

      if (factoryOpt.isEmpty()) throw new EntityNotFoundException(String.format("Factory with id %d not found", form.getFactoryId()));

      car.setFactory(factoryOpt.get());
    }

    car.setModel(form.getModel());
    car.setYear(form.getYear());
    car.setFuel(form.getFuelType());
    car.setDoors(form.getDoors());
    car.setCost(form.getCost());
    car.setColor(form.getColor());

    return car;
  }

  public void deleteCarFromDB(Long id) {
    Car car = getCarById(id);

    carRepository.delete(car);
  }
}
