package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * Часть объекта ответа сервера данных, содержащий общую информацию об АЗС.
 *
 * @see GasStationInfoResponse
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoExternal {

  @JsonProperty("GasStationId")
  private Integer id;
  @JsonProperty("Latitude")
  private Double latitude;
  @JsonProperty("Longitude")
  private Double longitude;
  @JsonProperty("StationNumber")
  private String stationNumber;
  @JsonProperty("RegionId")
  private Integer regionId;
  @JsonProperty("FederalLineId")
  private Integer federalLineId;
  @JsonProperty("ManagerFullName")
  private String managerFullName;
  @JsonProperty("Email")
  private String email;
  @JsonProperty("Phone")
  private String phone;
  @JsonProperty("Fax")
  private String fax;
  @JsonProperty("CompanyId")
  private Integer companyId;
  @JsonProperty("GasStationStatus")
  private Integer gasStationStatus;
  @JsonProperty("Company")
  private CompanyExternal company;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("Address")
  private String address;
  @JsonProperty("TwentyFourHour")
  private Boolean twentyFourHour;
  @JsonProperty("HasStore")
  private Boolean hasStore;
  @JsonProperty("DisplayName")
  private String displayName;
  @JsonProperty("SellsOil")
  private Boolean sellsOil;
  @JsonProperty("Services")
  private List<ServiceExternal> services;
  @JsonProperty("PaymentTypes")
  private List<PaymentTypeExternal> paymentTypes;
  @JsonProperty("Features")
  private List<FeatureExternal> features;
  @JsonProperty("Properties")
  private List<PropertyExternal> properties;

}
