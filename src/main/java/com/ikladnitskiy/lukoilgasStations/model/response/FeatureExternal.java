package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Часть объекта ответа сервера данных, возможно, содержащий информацию, классифицирующую
 * месторасположение АЗС.
 *
 * @see InfoExternal
 * @see GasStationInfoResponse
 */
@Data
public class FeatureExternal {

  @JsonProperty("FeatureId")
  private Integer featureId;
  @JsonProperty("Name")
  private String name;
}
