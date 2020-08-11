package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Часть объекта ответа сервера данных, содержащий информацию о компании-собственнике АЗС.
 *
 * @see InfoExternal
 * @see GasStationInfoResponse
 */
@Data
public class CompanyExternal {

  @JsonProperty("CompanyId")
  private Integer companyId;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("Address")
  private String address;
  @JsonProperty("Phone")
  private String phone;
  @JsonProperty("Fax")
  private String fax;
  @JsonProperty("Email")
  private String email;
  @JsonProperty("DatabaseHomePageUrl")
  private String databaseHomePageUrl;
  @JsonProperty("HomePageUrl")
  private String homePageUrl;
}
