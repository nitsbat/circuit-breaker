package com.cb.microservice.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @GetMapping("/users")
  public List<String> getUsers() {
    throw new RuntimeException();
//    return List.of("test", "test1");
  }
}
