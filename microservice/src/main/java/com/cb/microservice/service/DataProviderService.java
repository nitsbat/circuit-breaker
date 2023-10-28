package com.cb.microservice.service;

import com.cb.microservice.dataprovider.DataProxy;
import com.cb.microservice.model.People;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DataProviderService {

  private final DataProxy dataProxy;

  public DataProviderService(DataProxy dataProxy) {
    this.dataProxy = dataProxy;
  }

  public ResponseEntity<List<People>> getUsers() {
    return dataProxy.getCurrentUsers();
  }
}
