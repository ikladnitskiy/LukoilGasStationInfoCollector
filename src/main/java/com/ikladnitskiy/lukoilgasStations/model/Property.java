package com.ikladnitskiy.lukoilgasStations.model;

import lombok.Data;

/**
 * Сущность, содержащая информацию об особенностях данной АЗС.
 */
@Data
public class Property {

  private Integer propertyId;
  private String name;
}
