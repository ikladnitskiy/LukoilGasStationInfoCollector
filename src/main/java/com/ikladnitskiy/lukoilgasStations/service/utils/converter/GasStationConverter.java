package com.ikladnitskiy.lukoilgasStations.service.utils.converter;

import com.ikladnitskiy.lukoilgasStations.model.Company;
import com.ikladnitskiy.lukoilgasStations.model.Feature;
import com.ikladnitskiy.lukoilgasStations.model.Fuel;
import com.ikladnitskiy.lukoilgasStations.model.GasStation;
import com.ikladnitskiy.lukoilgasStations.model.PaymentType;
import com.ikladnitskiy.lukoilgasStations.model.Property;
import com.ikladnitskiy.lukoilgasStations.model.Service;
import com.ikladnitskiy.lukoilgasStations.model.response.CompanyExternal;
import com.ikladnitskiy.lukoilgasStations.model.response.FeatureExternal;
import com.ikladnitskiy.lukoilgasStations.model.response.FuelExternal;
import com.ikladnitskiy.lukoilgasStations.model.response.GasStationInfoResponse;
import com.ikladnitskiy.lukoilgasStations.model.response.PaymentTypeExternal;
import com.ikladnitskiy.lukoilgasStations.model.response.PropertyExternal;
import com.ikladnitskiy.lukoilgasStations.model.response.ServiceExternal;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Преобразователь полученного объекта данных в сущность АЗС.
 */
@Component
public class GasStationConverter {

  public static GasStation convert(GasStationInfoResponse response) {
    GasStation gasStation = new GasStation();
    gasStation.setGasStationId(response.getGasStationId());
    gasStation.setLatitude(response.getInfoExternal().getLatitude());
    gasStation.setLongitude(response.getInfoExternal().getLongitude());
    gasStation.setStationNumber(response.getInfoExternal().getStationNumber());
    gasStation.setRegionId(response.getInfoExternal().getRegionId());
    gasStation.setFederalLineId(response.getInfoExternal().getFederalLineId());
    gasStation.setManagerFullName(response.getInfoExternal().getManagerFullName());
    gasStation.setEmail(response.getInfoExternal().getEmail());
    gasStation.setPhone(response.getInfoExternal().getPhone());
    gasStation.setFax(response.getInfoExternal().getFax());
    gasStation.setGasStationStatus(response.getInfoExternal().getGasStationStatus());
    gasStation.setName(response.getInfoExternal().getName());
    gasStation.setDisplayName(response.getInfoExternal().getDisplayName());
    gasStation.setAddress(response.getInfoExternal().getAddress());
    gasStation.setTwentyFourHour(response.getInfoExternal().getTwentyFourHour());
    gasStation.setHasStory(response.getInfoExternal().getHasStore());
    gasStation.setSellsOil(response.getInfoExternal().getSellsOil());

    gasStation.setCompany(convert(response.getInfoExternal().getCompany()));
    gasStation.setServiceList(response.getInfoExternal().getServices()
        .stream()
        .map(GasStationConverter::convert)
        .collect(Collectors.toList()));
    gasStation.setPaymentTypeList(response.getInfoExternal().getPaymentTypes()
        .stream()
        .map(GasStationConverter::convert)
        .collect(Collectors.toList()));
    gasStation.setFeatureList(response.getInfoExternal().getFeatures()
        .stream()
        .map(GasStationConverter::convert)
        .collect(Collectors.toList()));
    gasStation.setPropertyList(response.getInfoExternal().getProperties()
        .stream().map(GasStationConverter::convert)
        .collect(Collectors.toList()));
    gasStation.setFuelList(response.getFuels()
        .stream()
        .map(GasStationConverter::convert)
        .collect(Collectors.toList()));
    return gasStation;
  }

  private static Company convert(CompanyExternal response) {
    Company company = new Company();
    company.setCompanyId(response.getCompanyId());
    company.setAddress(response.getAddress());
    company.setName(response.getName());
    company.setPhone(response.getPhone());
    company.setFax(response.getFax());
    company.setEmail(response.getEmail());
    company.setDatabaseHomePageUrl(response.getDatabaseHomePageUrl());
    company.setHomePageUrl(response.getHomePageUrl());
    return company;
  }

  private static Service convert(ServiceExternal response) {
    Service service = new Service();
    service.setServiceId(response.getServiceId());
    service.setName(response.getName());
    service.setServiceGroupId(response.getServiceGroupId());
    return service;
  }

  private static PaymentType convert(PaymentTypeExternal response) {
    PaymentType paymentType = new PaymentType();
    paymentType.setPaymentTypeId(response.getPaymentTypeId());
    paymentType.setName(response.getName());
    return paymentType;
  }

  private static Feature convert(FeatureExternal response) {
    Feature feature = new Feature();
    feature.setFeatureId(response.getFeatureId());
    feature.setName(response.getName());
    return feature;
  }

  private static Property convert(PropertyExternal response) {
    Property property = new Property();
    property.setPropertyId(response.getPropertyId());
    property.setName(response.getName());
    return property;
  }

  private static Fuel convert(FuelExternal response) {
    Fuel fuel = new Fuel();
    fuel.setFuelId(response.getFuelId());
    fuel.setName(response.getName());
    fuel.setPrice(response.getPrice());
    fuel.setDisplayPrice(response.getDisplayPrice());
    fuel.setCompanyPrice(response.getCompanyPrice());
    fuel.setCompanyPriceType(response.getCompanyPriceType());
    fuel.setCurrencyIsoCode(response.getCurrencyIsoCode());
    fuel.setCurrencyDisplayName(response.getCurrencyDisplayName());
    return fuel;
  }
}
