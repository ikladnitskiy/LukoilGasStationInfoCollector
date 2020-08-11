package com.ikladnitskiy.lukoilgasStations.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Часть объекта ответа сервера данных, содержащий информацию о реализуемом топливе на АЗС.
 *
 * @see GasStationInfoResponse
 */
@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class FuelResp {

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
