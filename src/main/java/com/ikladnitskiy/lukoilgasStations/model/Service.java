package com.ikladnitskiy.lukoilgasStations.model;

import lombok.Data;

/**
 * Сущность, описывающая тип сервиса на АЗС.
 */
@Data
public class Service {

  private Integer serviceId;
  private String name;
  private Integer serviceGroupId;
}
