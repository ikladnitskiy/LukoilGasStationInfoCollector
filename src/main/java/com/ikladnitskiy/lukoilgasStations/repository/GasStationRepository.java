package com.ikladnitskiy.lukoilgasStations.repository;

import com.ikladnitskiy.lukoilgasStations.model.GasStation;

/**
 * Интерфейс, описывающий методы для работы с данными.
 */
public interface GasStationRepository {

  void createTables();

  void saveGasStation(GasStation station);
}
