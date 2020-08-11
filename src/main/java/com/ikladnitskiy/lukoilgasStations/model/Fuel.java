package com.ikladnitskiy.lukoilgasStations.model;

import lombok.Data;

/**
 * Сущность, описывающая вид топлива.
 */
@Data
public class Fuel {

  private Integer fuelId;
  private String name;
  private String price;
  private String displayPrice;
  private String companyPrice;
  private String companyPriceType;
  private String currencyIsoCode;
  private String currencyDisplayName;

}
