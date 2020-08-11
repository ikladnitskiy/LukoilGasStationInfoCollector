package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * Часть объекта ответа сервера данных, содержащий ID АЗС.
 *
 * @see RollGasStationsResponse
 */
@Data
public class GasStationExternal {

  @JsonProperty("GasStationId")
  private Integer gasStationId;
  @JsonProperty("FuelClasses")
  private List<Integer> fuelClasses;

}
