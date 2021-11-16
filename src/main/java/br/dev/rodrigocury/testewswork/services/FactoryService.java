package br.dev.rodrigocury.testewswork.services;

import br.dev.rodrigocury.testewswork.controllers.dtos.FactoryDto;
import br.dev.rodrigocury.testewswork.controllers.forms.NewFactoryForm;
import br.dev.rodrigocury.testewswork.entities.Factory;
import br.dev.rodrigocury.testewswork.repositories.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class FactoryService {

  private final FactoryRepository factoryRepository;

  @Autowired
  public FactoryService(FactoryRepository factoryRepository) {
    this.factoryRepository = factoryRepository;
  }

  public Factory createFactory(NewFactoryForm form) {
    Factory factory = new Factory();
    factory.setFactoryName(form.getFactoryName());
    factory.setCountryCode(form.getCountryCode());

    factoryRepository.save(factory);

    return factory;
  }

  public Page<Factory> listFactories(Pageable page) {
    return factoryRepository.findAll(page);
  }

  public FactoryDto findFactory(Long id) {
    Optional<Factory> opt = factoryRepository.findByIdWithCars(id);

    if (opt.isEmpty()){
      throw new EntityNotFoundException(String.format("Factory with id %d not found", id));
    }

    return new FactoryDto(opt.get());
  }

  @Transactional
  public FactoryDto changeFactoryData(Long id, NewFactoryForm form) {
    Optional<Factory> opt = factoryRepository.findByIdWithCars(id);

    if (opt.isEmpty()){
      throw new EntityNotFoundException(String.format("Factory with id %d not found", id));
    }

    Factory factory = opt.get();

    factory.setFactoryName(form.getFactoryName());
    factory.setCountryCode(form.getCountryCode());

    return new FactoryDto(factory);
  }

  public void deleteFactory(Long id) {
    Optional<Factory> opt = factoryRepository.findById(id);

    if (opt.isEmpty()){
      throw new EntityNotFoundException(String.format("Factory with id %d not found", id));
    }

    factoryRepository.delete(opt.get());
  }
}
