package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Часть объекта ответа сервера данных, содержащий информацию о реализуемом топливе на АЗС.
 *
 * @see GasStationInfoResponse
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FuelExternal {

  @JsonProperty("GasStationId")
  private Integer gasStationId;
  @JsonProperty("FuelId")
  private Integer fuelId;
  @JsonProperty("Price")
  private String price;
  @JsonProperty("CompanyPrice")
  private String companyPrice;
  @JsonProperty("CompanyPriceType")
  private String companyPriceType;
  @JsonProperty("CurrencyIsoCode")
  private String currencyIsoCode;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("DisplayPrice")
  private String displayPrice;
  @JsonProperty("CurrencyDisplayName")
  private String currencyDisplayName;
}
