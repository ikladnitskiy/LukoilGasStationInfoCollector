package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * Объект ответа сервера данных, включающий в себя список АЗС.
 */
@Data
public class RollGasStationsResponse {

  @JsonProperty("FuelClasses")
  private List<Integer> fuelClasses;
  @JsonProperty("GasStations")
  private List<GasStationResp> gasStations;

}
