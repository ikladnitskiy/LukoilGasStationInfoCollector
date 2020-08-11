package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Часть объекта ответа сервера данных, содержащий информацию о доступном сервисе на АЗС.
 *
 * @see InfoResp
 * @see GasStationInfoResponse
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceResp {

  @JsonProperty("ServiceId")
  private Integer serviceId;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("ServiceGroupId")
  private Integer serviceGroupId;
}
