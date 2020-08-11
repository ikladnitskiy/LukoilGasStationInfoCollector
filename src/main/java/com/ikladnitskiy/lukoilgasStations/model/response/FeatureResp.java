package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Часть объекта ответа сервера данных, возможно, содержащий информацию, классифицирующую
 * месторасположение АЗС.
 *
 * @see InfoResp
 * @see GasStationInfoResponse
 */
@Data
@EqualsAndHashCode
public class FeatureResp {

  @JsonProperty("FeatureId")
  private Integer featureId;
  @JsonProperty("Name")
  private String name;
}
