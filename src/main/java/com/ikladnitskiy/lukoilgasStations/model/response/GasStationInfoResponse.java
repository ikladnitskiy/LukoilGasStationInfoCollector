package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * Объект ответа сервера данных, содержащий информацию об АЗС.
 */
@Data
public class GasStationInfoResponse {

  @JsonProperty("GasStation")
  private InfoExternal infoExternal;
  @JsonProperty("Fuels")
  private List<FuelExternal> fuels;
  @JsonProperty("GasStationId")
  private Integer gasStationId;
}
