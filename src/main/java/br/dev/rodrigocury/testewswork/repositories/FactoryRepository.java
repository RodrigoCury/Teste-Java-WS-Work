package br.dev.rodrigocury.testewswork.repositories;

import br.dev.rodrigocury.testewswork.entities.Factory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FactoryRepository extends PagingAndSortingRepository<Factory, Long> {

  @Query("SELECT F FROM Factory F " +
      "LEFT JOIN FETCH F.cars C " +
      "WHERE F.id = :id")
  Optional<Factory> findByIdWithCars(@Param("id") Long id);

  Optional<Factory> findByFactoryName(String nome);
}