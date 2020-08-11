package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Часть объекта ответа сервера данных, содержащий информацию об особенностях данной АЗС.
 *
 * @see InfoResp
 * @see GasStationInfoResponse
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyResp {

  @JsonProperty("PropertyId")
  private Integer propertyId;
  @JsonProperty("Name")
  private String name;
}
