package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Часть объекта ответа сервера данных, содержащий информацию о доступном сервисе на АЗС.
 *
 * @see InfoExternal
 * @see GasStationInfoResponse
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceExternal {

  @JsonProperty("ServiceId")
  private Integer serviceId;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("ServiceGroupId")
  private Integer serviceGroupId;
}
