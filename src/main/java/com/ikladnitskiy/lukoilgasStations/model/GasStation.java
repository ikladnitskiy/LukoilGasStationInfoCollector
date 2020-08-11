package com.ikladnitskiy.lukoilgasStations.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * Сущность АЗС.
 */
@Data
public class GasStation {

  private Integer gasStationId;
  private Double latitude;
  private Double longitude;
  private String stationNumber;
  private Integer regionId;
  private Integer federalLineId;
  private String managerFullName;
  private String email;
  private String phone;
  private String fax;
  private Integer gasStationStatus;
  private String name;
  private String displayName;
  private String address;
  private Boolean twentyFourHour;
  private Boolean hasStory;
  private Boolean sellsOil;
  private Company company;
  private List<Service> serviceList;
  private List<PaymentType> paymentTypeList;
  private List<Feature> featureList;
  private List<Property> propertyList;
  private List<Fuel> fuelList;

  /**
   * Конструктор АЗС.
   */
  public GasStation() {
    this.company = new Company();
    this.serviceList = new ArrayList<>();
    this.paymentTypeList = new ArrayList<>();
    this.featureList = new ArrayList<>();
    this.propertyList = new ArrayList<>();
    this.fuelList = new ArrayList<>();
  }
}
