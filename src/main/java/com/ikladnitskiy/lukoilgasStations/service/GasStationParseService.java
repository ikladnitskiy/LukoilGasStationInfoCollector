package com.ikladnitskiy.lukoilgasStations.service;

import com.ikladnitskiy.lukoilgasStations.model.response.GasStationInfoResponse;
import java.util.List;

/**
 * Интерфейс, перечисляющий методы для сбора данных.
 */
public interface GasStationParseService {

  List<Integer> getListGasStationId() throws Exception;

  GasStationInfoResponse getGasStationInfo(Integer gasStationId) throws Exception;
}
