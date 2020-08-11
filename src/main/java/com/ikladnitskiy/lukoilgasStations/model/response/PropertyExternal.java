package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Часть объекта ответа сервера данных, содержащий информацию об особенностях данной АЗС.
 *
 * @see InfoExternal
 * @see GasStationInfoResponse
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyExternal {

  @JsonProperty("PropertyId")
  private Integer propertyId;
  @JsonProperty("Name")
  private String name;
}
