package com.ikladnitskiy.lukoilgasStations.model;

import lombok.Data;

/**
 * Сущность, возможно, содержащая информацию, классифицирующую месторасположение АЗС.
 */
@Data
public class Feature {

  private Integer featureId;
  private String name;
}
