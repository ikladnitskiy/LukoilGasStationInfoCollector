package com.ikladnitskiy.lukoilgasStations.utils;

import com.ikladnitskiy.lukoilgasStations.model.Company;
import com.ikladnitskiy.lukoilgasStations.model.Feature;
import com.ikladnitskiy.lukoilgasStations.model.Fuel;
import com.ikladnitskiy.lukoilgasStations.model.GasStation;
import com.ikladnitskiy.lukoilgasStations.model.PaymentType;
import com.ikladnitskiy.lukoilgasStations.model.Property;
import com.ikladnitskiy.lukoilgasStations.model.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Утилитный класс для работы с JDBC.
 */
@Slf4j
public class JdbcUtils {

  private static final String JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:~/db";
  private static final String USERNAME = "sa";
  private static final String PASSWORD = "";

  /**
   * Возвращает новый Connection.
   */
  public static Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName(JDBC_DRIVER);
    return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
  }

  /**
   * Возвращает SQL-скрипт создания таблиц в базе данных в виде тексовой строки. SQL-скрипт
   * инициализации находится в файле init.sql и расположен в директории /resources.
   */
  public static String getInitSql() {
    Resource resource = new ClassPathResource("init.sql");
    String line;
    StringBuilder builder = new StringBuilder();
    try (BufferedReader bus = new BufferedReader(
        new InputStreamReader(resource.getInputStream()))) {
      while ((line = bus.readLine()) != null) {
        builder.append(line);
      }
    } catch (IOException ex) {
      log.error("Ошибка чтения файла: {}", ex.getMessage());
    }
    return builder.toString();
  }

  /**
   * Метод формирует SQL-запрос для сохранения сущности GasStation в базу данных.
   */
  public static Statement toPrepareStatement(Statement stmt, GasStation station)
      throws SQLException {
    final String SAVE_GAS_STATION_SQL =
        "INSERT INTO stations "
            + "(id, latitude, longitude, station_number, region_id, federal_line_id, manager_full_name, email, phone, fax, "
            + "station_status, name, display_name, address, twenty_four_hour, has_story, sells_oil) "
            + "VALUES (%d, %s, %s, '%s', %d, %d, '%s', '%s', '%s', '%s', %d, '%s', '%s', '%s', %s, %s, %s);";
    stmt.addBatch(String
        .format(SAVE_GAS_STATION_SQL, station.getGasStationId(), station.getLatitude(),
            station.getLongitude(), station.getStationNumber(), station.getRegionId(),
            station.getFederalLineId(), station.getManagerFullName(), station.getEmail(),
            station.getPhone(), station.getFax(), station.getGasStationStatus(), station.getName(),
            station.getDisplayName(), station.getAddress(), station.getTwentyFourHour(),
            station.getHasStory(), station.getSellsOil()));

    toPrepareServiceStatement(stmt, station.getGasStationId(), station.getServiceList());
    toPreparePaymentTypeStatement(stmt, station.getGasStationId(), station.getPaymentTypeList());
    toPrepareFeatureStatement(stmt, station.getGasStationId(), station.getFeatureList());
    toPreparePropertyStatement(stmt, station.getGasStationId(), station.getPropertyList());
    toPrepareFuelStatement(stmt, station.getGasStationId(), station.getFuelList());
    return stmt;
  }

  private static void toPrepareCompanyStatement(Statement stmt, Integer gasStationId,
      Company company) throws SQLException {
    final String SAVE_COMPANY_SQL = "INSERT INTO companies "
        + "(station_id, company_id, name, phone, fax, email, database_home_page_url, home_page_url) "
        + "VALUES (%d, %d, '%s', '%s', '%s', '%s', '%s', '%s');";
    stmt.addBatch(String.format(SAVE_COMPANY_SQL, gasStationId, company.getCompanyId(),
        company.getName(), company.getPhone(), company.getFax(), company.getEmail(),
        company.getDatabaseHomePageUrl(), company.getHomePageUrl()));
  }

  /**
   * Метод формирует и добавляет в группу SQL-запросов запросы для сохранения сущностей Service в
   * базу данных.
   */
  private static void toPrepareServiceStatement(Statement stmt, Integer gasStationId,
      List<Service> services) throws SQLException {
    final String SAVE_SERVICE_SQL =
        "INSERT INTO services (station_id, service_id, name, service_group_id) VALUES (%d, %d, '%s', %d);";
    for (Service service : services) {
      stmt.addBatch(String
          .format(SAVE_SERVICE_SQL, gasStationId, service.getServiceId(), service.getName(),
              service.getServiceGroupId()));
    }
  }

  /**
   * Метод формирует и добавляет в группу SQL-запросов запросы для сохранения сущностей PaymentType
   * в базу данных.
   */
  private static void toPreparePaymentTypeStatement(Statement stmt, Integer gasStationId,
      List<PaymentType> paymentTypes) throws SQLException {
    final String SAVE_PAYMENT_TYPE_SQL =
        "INSERT INTO payment_types (station_id, payment_type_id, name) VALUES (%d, %d, '%s');";
    for (PaymentType type : paymentTypes) {
      stmt.addBatch(String
          .format(SAVE_PAYMENT_TYPE_SQL, gasStationId, type.getPaymentTypeId(), type.getName()));
    }
  }

  /**
   * Метод формирует и добавляет в группу SQL-запросов запросы для сохранения сущностей Feature в
   * базу данных.
   */
  private static void toPrepareFeatureStatement(Statement stmt, Integer gasStationId,
      List<Feature> features) throws SQLException {
    final String SAVE_FEATURE_SQL =
        "INSERT INTO features (station_id, feature_id, name) VALUES (%d, %d, '%s');";
    for (Feature feature : features) {
      stmt.addBatch(String
          .format(SAVE_FEATURE_SQL, gasStationId, feature.getFeatureId(), feature.getName()));
    }
  }

  /**
   * Метод формирует и добавляет в группу SQL-запросов запросы для сохранения сущностей Property в
   * базу данных.
   */
  private static void toPreparePropertyStatement(Statement stmt, Integer gasStationId,
      List<Property> properties) throws SQLException {
    final String SAVE_PROPERTY_SQL =
        "INSERT INTO properties (station_id, property_id, name) VALUES (%d, %d, '%s');";
    for (Property property : properties) {
      stmt.addBatch(String
          .format(SAVE_PROPERTY_SQL, gasStationId, property.getPropertyId(), property.getName()));
    }
  }

  /**
   * Метод формирует и добавляет в группу SQL-запросов запросы для сохранения сущностей Fuel в базу
   * данных.
   */
  private static void toPrepareFuelStatement(Statement stmt, Integer gasStationId, List<Fuel> fuels)
      throws SQLException {
    final String SAVE_FUEL_SQL = "INSERT INTO fuels "
        + "(station_id, fuel_id, name, price, display_price, company_price, company_price_type, currency_iso_code, currency_display_name) "
        + "VALUES (%d, %d, '%s', '%s', '%s', '%s', '%s', '%s', '%s');";
    for (Fuel fuel : fuels) {
      stmt.addBatch(String.format(SAVE_FUEL_SQL, gasStationId, fuel.getFuelId(), fuel.getPrice(),
          fuel.getName(), fuel.getDisplayPrice(), fuel.getCompanyPrice(),
          fuel.getCompanyPriceType(), fuel.getCurrencyIsoCode(), fuel.getCurrencyDisplayName()));
    }
  }
}
