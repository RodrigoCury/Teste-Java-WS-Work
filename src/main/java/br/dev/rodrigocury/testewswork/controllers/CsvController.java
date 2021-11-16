package br.dev.rodrigocury.testewswork.controllers;

import br.dev.rodrigocury.testewswork.services.CsvService;
import br.dev.rodrigocury.testewswork.utils.CsvHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/csv")
public class CsvController {

  private final CsvService csvService;

  @Autowired
  public CsvController(CsvService csvService) {
    this.csvService = csvService;
  }

  @PostMapping
   public ResponseEntity<?> addMany(@RequestParam("file") MultipartFile file){
    if(!CsvHelper.hasCSVFormat(file)) throw new IllegalArgumentException("File is not CSV format");

    try {
      csvService.save(file);

      return ResponseEntity.ok("Uploaded the file successfully: " + file.getOriginalFilename());
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload the file: " + file.getOriginalFilename() + "!");
    }

  }
}
