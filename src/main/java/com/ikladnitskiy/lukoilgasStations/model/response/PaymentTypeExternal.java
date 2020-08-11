package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Часть объекта ответа сервера данных, содержащий информацию о способе оплаты на АЗС.
 *
 * @see InfoExternal
 * @see GasStationInfoResponse
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentTypeExternal {

  @JsonProperty("PaymentTypeId")
  private Integer paymentTypeId;
  @JsonProperty("Name")
  private String name;
}
