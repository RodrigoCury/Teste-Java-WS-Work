package br.dev.rodrigocury.testewswork.services;

import br.dev.rodrigocury.testewswork.entities.Car;
import br.dev.rodrigocury.testewswork.entities.Factory;
import br.dev.rodrigocury.testewswork.repositories.CarRepository;
import br.dev.rodrigocury.testewswork.repositories.FactoryRepository;
import br.dev.rodrigocury.testewswork.utils.CsvHelper;
import br.dev.rodrigocury.testewswork.utils.CsvModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class CsvService {

  private final FactoryRepository factoryRepository;
  private final CarRepository carRepository;

  @Autowired
  public CsvService(FactoryRepository factoryRepository, CarRepository carRepository) {
    this.factoryRepository = factoryRepository;
    this.carRepository = carRepository;
  }

  public void save(MultipartFile file){
    List<Factory> factories = new ArrayList<>();
    Set<Car> cars = new HashSet<>();

    try {
      InputStream stream = file.getInputStream();

      Set<CsvModel> csvLines = CsvHelper.csvParser(stream);

      Set<String> nome = new HashSet<>();

      for (CsvModel csv:
           csvLines) {

        if (!nome.contains(csv.getMarcaNome())){
          Optional<Factory> factoryOpt = factoryRepository.findByFactoryName(csv.getMarcaNome());
          Factory factory = factoryOpt.orElseGet(Factory::new);

          factory.setFactoryName(csv.getMarcaNome());

          nome.add(csv.getMarcaNome());
          factories.add(factory);
        }

      }

      for (CsvModel csv:
           csvLines) {

        Factory factoryFound = factories.stream()
            .filter(factory -> factory.getFactoryName().equals(csv.getMarcaNome()))
            .toList().get(0);

        Car car = new Car(factoryFound, csv.getModelo(), csv.getAno(), csv.getCombustivel(), csv.getNumPortas(), csv.getValorFipe(), csv.getCor());

        cars.add(car);
      }

    } catch (IOException | IllegalArgumentException e) {
      throw new IllegalArgumentException("An Error Ocurred when get data from file");
    }

    factoryRepository.saveAll(factories);
    carRepository.saveAll(cars);
  }
}
