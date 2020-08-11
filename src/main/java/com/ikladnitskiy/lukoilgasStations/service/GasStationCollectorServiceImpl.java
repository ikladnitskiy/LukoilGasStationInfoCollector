package com.ikladnitskiy.lukoilgasStations.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikladnitskiy.lukoilgasStations.model.GasStation;
import com.ikladnitskiy.lukoilgasStations.model.response.GasStationExternal;
import com.ikladnitskiy.lukoilgasStations.model.response.GasStationInfoResponse;
import com.ikladnitskiy.lukoilgasStations.model.response.RollGasStationsResponse;
import com.ikladnitskiy.lukoilgasStations.utils.connection.ConnectionUtils;
import com.ikladnitskiy.lukoilgasStations.utils.converter.GasStationConverter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис, предназначенный для сбора данных.
 */
@Slf4j
@Service
public class GasStationCollectorServiceImpl implements GasStationCollectorService {

  private ObjectMapper mapper = new ObjectMapper();
  private GasStationConverter converter;

  @Autowired
  public GasStationCollectorServiceImpl(GasStationConverter converter) {
    this.converter = converter;
  }

  @Override
  public List<Integer> getGasStationIdList() throws Exception {
    HttpsURLConnection conn = ConnectionUtils.getRollStationsConnection();
    if (conn == null) {
      return null;
    }
    if (conn.getResponseCode() != 200) {
      log.info("Не удалось получить данные с сервера. Код ответа: {}", conn.getResponseCode());
      ConnectionUtils.closeConnection(conn);
    }
    String json;
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
      json = reader.lines().collect(Collectors.joining(System.lineSeparator()));
      ConnectionUtils.closeConnection(conn);
    }

    RollGasStationsResponse roll = mapper.readValue(json, RollGasStationsResponse.class);
    return roll.getGasStations().stream()
        .map(GasStationExternal::getGasStationId)
        .collect(Collectors.toList());
  }

  @Override
  public GasStation getGasStation(Integer gasStationId) throws Exception {
    HttpsURLConnection conn = ConnectionUtils.getGasStationConnection(gasStationId);
    if (conn == null) {
      return null;
    }
    if (conn.getResponseCode() != 200) {
      log.info("Не удалось получить данные с сервера. Код ответа: {}", conn.getResponseCode());
      ConnectionUtils.closeConnection(conn);
    }
    StringBuilder builder;
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
      builder = new StringBuilder(
          reader.lines().collect(Collectors.joining(System.lineSeparator())));
      ConnectionUtils.closeConnection(conn);
    }
    String json = builder.substring(1, builder.length() - 1);

    return GasStationConverter.convert(mapper.readValue(json, GasStationInfoResponse.class));
  }
}
