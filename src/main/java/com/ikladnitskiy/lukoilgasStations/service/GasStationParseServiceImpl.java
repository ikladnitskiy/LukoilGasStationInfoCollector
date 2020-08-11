package com.ikladnitskiy.lukoilgasStations.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikladnitskiy.lukoilgasStations.model.response.GasStationInfoResponse;
import com.ikladnitskiy.lukoilgasStations.model.response.GasStationResp;
import com.ikladnitskiy.lukoilgasStations.model.response.RollGasStationsResponse;
import com.ikladnitskiy.lukoilgasStations.utils.connection.ConnectionUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Сервис, предназначенный для сбора данных.
 */
@Slf4j
@Service
public class GasStationParseServiceImpl implements GasStationParseService {

  private ObjectMapper mapper = new ObjectMapper();

  @Override
  public List<Integer> getListGasStationId() throws Exception {
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
        .map(GasStationResp::getGasStationId)
        .collect(Collectors.toList());
  }

  @Override
  public GasStationInfoResponse getGasStationInfo(Integer gasStationId) throws Exception {
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

    return mapper.readValue(json, GasStationInfoResponse.class);
  }
}
