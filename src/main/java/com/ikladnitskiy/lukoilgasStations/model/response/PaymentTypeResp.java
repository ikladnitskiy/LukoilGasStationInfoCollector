package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Часть объекта ответа сервера данных, содержащий информацию о способе оплаты на АЗС.
 *
 * @see InfoResp
 * @see GasStationInfoResponse
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentTypeResp {

  @JsonProperty("PaymentTypeId")
  private Integer paymentTypeId;
  @JsonProperty("Name")
  private String name;
}
