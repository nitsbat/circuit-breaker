package com.example.upstream.peopleservice;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {

  private final ResourceLoader resourceLoader;

  public PeopleController(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @GetMapping("/people")
  public ResponseEntity<List<People>> getDoctors() {
    Resource resource = resourceLoader.getResource("classpath:people.csv");
    try (CSVReader csvReader = new CSVReader(new FileReader(resource.getFile().getPath()))) {
      return ResponseEntity.ok().body(getPeopleFromCSV(csvReader));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private List<People> getPeopleFromCSV(CSVReader csvReader)
      throws CsvValidationException, IOException {
    List<People> peopleList = new ArrayList<>();
    while (csvReader.iterator().hasNext()) {
      String[] strings = csvReader.readNext();
      People people = getDoctor(strings);
      peopleList.add(people);
    }
    return peopleList.stream()
        .filter(p -> p.getProfession().equals("doctor"))
        .collect(Collectors.toList());
  }

  private People getDoctor(String[] strings) {
//    throw new RuntimeException();
    return new People(Long.valueOf(strings[0]), strings[1], strings[2], strings[3], strings[4]);
  }
}
