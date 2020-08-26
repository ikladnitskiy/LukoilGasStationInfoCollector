package com.ikladnitskiy.lukoilgasStations.service;

import com.ikladnitskiy.lukoilgasStations.model.GasStation;
import java.util.List;

/**
 * Интерфейс, перечисляющий методы для доступа к информации об АЗС.
 */
public interface GasStationService {

  Integer getCountGasStation();

  List<Integer> getGasStationNumberList();

  GasStation getGasStationById(Integer id);

  void getTwelveRecords() throws Exception;

  void refreshData() throws Exception;

}
