package com.ikladnitskiy.lukoilgasStations.model;

import lombok.Data;

/***
 * Сущность, описывающая способ оплаты.
 */
@Data
public class PaymentType {

  private Integer paymentTypeId;
  private String name;
}
