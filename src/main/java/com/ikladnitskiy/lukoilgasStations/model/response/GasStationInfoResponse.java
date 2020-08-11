package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.ToString;

/**
 * Объект ответа сервера данных, содержащий информацию об АЗС.
 */
@Data
@ToString
public class GasStationInfoResponse {

  @JsonProperty("GasStation")
  private InfoResp infoResp;
  @JsonProperty("Fuels")
  private List<FuelResp> fuels;
  @JsonProperty("GasStationId")
  private Integer gasStationId;
}
