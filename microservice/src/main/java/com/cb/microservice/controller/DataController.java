package com.cb.microservice.controller;

import com.cb.microservice.model.People;
import com.cb.microservice.service.DataProviderService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

  private final DataProviderService dataProviderService;

  public DataController(DataProviderService dataProviderService) {
    this.dataProviderService = dataProviderService;
  }

  @GetMapping("/data")
  public List<People> getUsers() {
    return dataProviderService.getUsers().getBody();
  }
}
