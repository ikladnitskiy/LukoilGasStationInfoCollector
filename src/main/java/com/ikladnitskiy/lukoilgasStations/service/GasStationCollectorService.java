package com.ikladnitskiy.lukoilgasStations.service;

import com.ikladnitskiy.lukoilgasStations.model.GasStation;
import java.util.List;

/**
 * Интерфейс, перечисляющий методы для сбора данных.
 */
public interface GasStationCollectorService {

  List<Integer> getGasStationIdList() throws Exception;

  GasStation getGasStation(Integer gasStationId) throws Exception;
}
