package br.dev.rodrigocury.testewswork.utils;

import br.dev.rodrigocury.testewswork.entities.enums.FuelType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CsvHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERS = {"ID","MARCA_ID","MARCA_NOME","MODELO","ANO","COMBUSTIVEL","NUM_PORTAS","VALOR_FIPE","COR"};

  public static boolean hasCSVFormat(MultipartFile file) {
    return TYPE.equals(file.getContentType());
  }

  public static Set<CsvModel> csvParser(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
         CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
    ) {

      Set<CsvModel> csvModelSet = new HashSet<>();

      List<CSVRecord> csvRecords = csvParser.getRecords();

      List<String> errors = new ArrayList<>();

      for (CSVRecord csvRecord : csvRecords) {
        try {

          CsvModel csv = new CsvModel(
            Long.parseLong(csvRecord.get("ID")),
            Long.parseLong(csvRecord.get("MARCA_ID")),
            csvRecord.get("MARCA_NOME"),
            csvRecord.get("MODELO"),
            Short.parseShort(csvRecord.get("ANO")),
            FuelType.valueOf(csvRecord.get("COMBUSTIVEL").toUpperCase()),
            Short.parseShort(csvRecord.get("NUM_PORTAS")),
            trimStringtoBigDecimal(csvRecord.get("VALOR_FIPE")),
            csvRecord.get("COR")
          );

          csvModelSet.add(csv);
        } catch (NumberFormatException ex){
          throw new IllegalArgumentException("An Error Ocurre one of the numbers sent");
        } catch (IllegalArgumentException ex) {
          throw new IllegalArgumentException("An Error Ocurre one of the Fuel sent");
        }
      }

      return csvModelSet;

    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

  private static BigDecimal trimStringtoBigDecimal(String s){
    List<String> separatedByDots = List.of(s.split("\\."));
    String newS = separatedByDots.stream().reduce((str1, str2) ->  str1+str2).orElseGet(String::new);
    newS = newS.replace(",", ".");
    return new BigDecimal(newS);
  }

}