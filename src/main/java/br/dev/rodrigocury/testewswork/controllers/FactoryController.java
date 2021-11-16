package br.dev.rodrigocury.testewswork.controllers;

import br.dev.rodrigocury.testewswork.controllers.dtos.FactoryDto;
import br.dev.rodrigocury.testewswork.controllers.forms.NewFactoryForm;
import br.dev.rodrigocury.testewswork.entities.Factory;
import br.dev.rodrigocury.testewswork.services.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("factory")
public class FactoryController {

  private final FactoryService factoryService;

  @Autowired
  public FactoryController(FactoryService factoryService) {
    this.factoryService = factoryService;
  }

  @PostMapping
  public ResponseEntity<Factory> addFactoryToDB(@RequestBody @Valid NewFactoryForm form, UriComponentsBuilder builder){

    Factory factory = factoryService.createFactory(form);

    URI uri = builder.path("/factory/{id}").buildAndExpand(factory.getId()).toUri();

    return ResponseEntity.created(uri).body(factory);
  }

  @GetMapping
  public ResponseEntity<Page<Factory>> listFactories(
      @PageableDefault(sort = "id") Pageable page
      ){
    Page<Factory> factories = factoryService.listFactories(page);

    return ResponseEntity.ok(factories);
  }

  @GetMapping("{id}")
  public ResponseEntity<FactoryDto> findFactoryById(@PathVariable("id") Long id){
    FactoryDto factory = factoryService.findFactory(id);
    return ResponseEntity.ok(factory);
  }

  @PutMapping("{id}")
  @Transactional
  public ResponseEntity<FactoryDto> changeFactoryData(
      @PathVariable("id") Long id,
      @RequestBody @Valid NewFactoryForm form
  ){
    FactoryDto factory = factoryService.changeFactoryData(id, form);

    return ResponseEntity.ok(factory);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteFactoryFromDB(@PathVariable("id") Long id){
    factoryService.deleteFactory(id);
    return ResponseEntity.noContent().build();
  }

}
