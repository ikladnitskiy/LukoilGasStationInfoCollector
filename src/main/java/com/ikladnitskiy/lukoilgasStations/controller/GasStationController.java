package com.ikladnitskiy.lukoilgasStations.controller;

import com.ikladnitskiy.lukoilgasStations.model.GasStation;
import com.ikladnitskiy.lukoilgasStations.service.GasStationService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер действий с АЗС.
 */
@Slf4j
@RestController
@RequestMapping("/api/station")
public class GasStationController {

  private GasStationService gasStationService;

  @Autowired
  public GasStationController(GasStationService gasStationService) {
    this.gasStationService = gasStationService;
  }

  /**
   * Получение списка номеров доступных АЗС.
   */
  @GetMapping(value = "")
  public ResponseEntity<List<Integer>> getGasStationNumberList() {
    return new ResponseEntity<>(gasStationService.getGasStationNumberList(), HttpStatus.OK);
  }

  /**
   * Получение количества доступных АЗС.
   */
  @GetMapping(value = "/count")
  public ResponseEntity<Integer> getCountGasStation() {
    return new ResponseEntity<>(gasStationService.getCountGasStation(), HttpStatus.OK);
  }

  /**
   * Получение информации об АЗС по ее ID.
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<GasStation> getGasStationById(@PathVariable Integer id) {
    GasStation station = gasStationService.getGasStationById(id);
    if (station == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(station, HttpStatus.OK);
  }
}
