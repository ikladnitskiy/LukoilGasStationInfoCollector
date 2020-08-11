package com.ikladnitskiy.lukoilgasStations.model;

import lombok.Data;

/**
 * Сущность компании-собственника АЗС.
 */
@Data
public class Company {

  private Integer companyId;
  private String address;
  private String name;
  private String phone;
  private String fax;
  private String email;
  private String databaseHomePageUrl;
  private String homePageUrl;
}
