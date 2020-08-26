package com.ikladnitskiy.lukoilgasStations.repository;

import com.ikladnitskiy.lukoilgasStations.model.GasStation;
import java.util.List;

/**
 * Интерфейс, описывающий методы для работы с данными.
 */
public interface GasStationRepository {

  void createTables();

  void saveGasStation(GasStation station);

  Integer getCountGasStations();

  List<Integer> getGasStationIdsList();

  GasStation getGasStationById(Integer id);
}
