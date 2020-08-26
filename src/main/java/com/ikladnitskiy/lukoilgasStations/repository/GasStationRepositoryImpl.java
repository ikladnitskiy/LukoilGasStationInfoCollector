package com.ikladnitskiy.lukoilgasStations.repository;

import com.ikladnitskiy.lukoilgasStations.model.Company;
import com.ikladnitskiy.lukoilgasStations.model.Feature;
import com.ikladnitskiy.lukoilgasStations.model.Fuel;
import com.ikladnitskiy.lukoilgasStations.model.GasStation;
import com.ikladnitskiy.lukoilgasStations.model.PaymentType;
import com.ikladnitskiy.lukoilgasStations.model.Property;
import com.ikladnitskiy.lukoilgasStations.model.Service;
import com.ikladnitskiy.lukoilgasStations.repository.util.JdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Реализация методов для манипулирования данными в базе данных.
 */
@Slf4j
@Repository
public class GasStationRepositoryImpl implements GasStationRepository {

  /**
   * Данный метод выполняет полученный SQL-скрипт и создает тадлицы в базе данных.
   *
   * @see JdbcUtils#getInitSql()
   */
  @Override
  public void createTables() {
    try (Connection conn = JdbcUtils.getConnection();
        Statement stmt = conn.createStatement()) {
      String sql = JdbcUtils.getInitSql();
      stmt.executeUpdate(sql);
    } catch (ClassNotFoundException ex) {
      log.error("Ошибка соединения с базой данных: {}", ex.getMessage());
    } catch (SQLException ex) {
      log.error("Произошла ошибка при обработке запроса к базе данных: {}", ex.getMessage());
    }
  }

  /**
   * Метод сохраняет сущность GasStation в базу данных.
   */
  @Override
  public void saveGasStation(GasStation station) {
    try (Connection conn = JdbcUtils.getConnection();
        Statement stmt = conn.createStatement()) {
      conn.setAutoCommit(false);
      JdbcUtils.toPrepareStatement(stmt, station);
      stmt.executeBatch();
      conn.commit();
      log.info("АЗС ID: {} успешно сохранена.", station.getGasStationId());
    } catch (ClassNotFoundException ex) {
      log.error("Ошибка соединения с базой данных: {}", ex.getMessage());
    } catch (SQLException ex) {
      log.error("Произошла ошибка при обработке запроса к базе данных: {}", ex.getMessage());
    }
  }

  /**
   * Метод возвращает количество записей GasStation в базе данных.
   */
  @Override
  public Integer getCountGasStations() {
    final String GET_COUNT_SQL = "SELECT COUNT(*) AS COUNT FROM stations";
    try (Connection conn = JdbcUtils.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(GET_COUNT_SQL)) {
      if (rs.last()) {
        return rs.getInt(1);
      }
    } catch (ClassNotFoundException ex) {
      log.error("Ошибка соединения с базой данных: {}", ex.getMessage());
    } catch (SQLException ex) {
      log.error("Произошла ошибка при обработке запроса к базе данных: {}", ex.getMessage());
    }
    return null;
  }

  /**
   * Метод возвращает список ID сохраненных в базе данных сущностей GasStation.
   */
  @Override
  public List<Integer> getGasStationIdsList() {
    final String GET_STATION_IDS_SQL = "SELECT id FROM stations";
    List<Integer> idList = new ArrayList<>();
    try (Connection conn = JdbcUtils.getConnection();
        Statement stmt = conn.createStatement()) {

      ResultSet rs = stmt.executeQuery(GET_STATION_IDS_SQL);
      while (rs.next()) {
        idList.add(rs.getInt(1));
      }
    } catch (ClassNotFoundException ex) {
      log.error("Ошибка соединения с базой данных: {}", ex.getMessage());
    } catch (SQLException ex) {
      log.error("Произошла ошибка при обработке запроса к базе данных: {}", ex.getMessage());
    }
    return idList;
  }

  /**
   * Метод возвращает сущность GasStation из базы данных по ее ID.
   */
  @Override
  public GasStation getGasStationById(Integer id) {
    final String GET_STATION_BY_ID_SQL = "SELECT * FROM stations WHERE id = %d";
    GasStation station = new GasStation();
    try (Connection conn = JdbcUtils.getConnection();
        Statement stmt = conn.createStatement()) {

      station = JdbcUtils.mapGasStation(
          stmt.executeQuery(String.format(GET_STATION_BY_ID_SQL, id)));
      station.setCompany(getCompanyByStationId(stmt, id));
      station.setServiceList(getServiceListByStationId(stmt, id));
      station.setPaymentTypeList(getPaymentTypeListByStationId(stmt, id));
      station.setFeatureList(getFeatureListByStationId(stmt, id));
      station.setPropertyList(getPropertyListByStationId(stmt, id));
      station.setFuelList(getFuelListByStationId(stmt, id));
    } catch (ClassNotFoundException ex) {
      log.error("Ошибка соединения с базой данных: {}", ex.getMessage());
    } catch (SQLException ex) {
      log.error("Произошла ошибка при обработке запроса к базе данных: {}", ex.getMessage());
    }
    return station;
  }

  /**
   * Метод возвращает сущность Company из базы данных, по ID сущности GasStation (принадлежащую
   * ей).
   */
  private Company getCompanyByStationId(Statement stmt, Integer id) throws SQLException {
    final String GET_COMPANY_BY_ID_SQL = "SELECT * FROM companies WHERE station_id = %d";
    return JdbcUtils.mapCompany(stmt.executeQuery(String.format(GET_COMPANY_BY_ID_SQL, id)));
  }

  /**
   * Метод возвращает список сущностей Service из базы данных, по ID сущности GasStation
   * (относящихся к ней).
   */
  private List<Service> getServiceListByStationId(Statement stmt, Integer id) throws SQLException {
    final String GET_SERVICES_BY_ID_SQL = "SELECT * FROM services WHERE station_id = %d";
    return JdbcUtils.mapServices(stmt.executeQuery(String.format(GET_SERVICES_BY_ID_SQL, id)));
  }

  /**
   * Метод возвращает список сущностей PaymentType из базы данных, по ID сущности GasStation
   * (относящихся к ней).
   */
  private List<PaymentType> getPaymentTypeListByStationId(Statement stmt, Integer id)
      throws SQLException {
    final String GET_PAYMENT_TYPE_BY_ID_SQL = "SELECT * FROM payment_types WHERE station_id = %d";
    return JdbcUtils.mapPaymentTypes(
        stmt.executeQuery(String.format(GET_PAYMENT_TYPE_BY_ID_SQL, id)));
  }

  /**
   * Метод возвращает список сущностей Feature из базы данных, по ID сущности GasStation
   * (относящихся к ней).
   */
  private List<Feature> getFeatureListByStationId(Statement stmt, Integer id) throws SQLException {
    final String GET_FEATURE_BY_ID_SQL = "SELECT * FROM features WHERE station_id = %d";
    return JdbcUtils.mapFeatures(stmt.executeQuery(String.format(GET_FEATURE_BY_ID_SQL, id)));
  }

  /**
   * Метод возвращает список сущностей Property из базы данных, по ID сущности GasStation
   * (относящихся к ней).
   */
  private List<Property> getPropertyListByStationId(Statement stmt, Integer id)
      throws SQLException {
    final String GET_PROPERTY_BY_ID_SQL = "SELECT * FROM properties WHERE station_id = %d";
    return JdbcUtils.mapProperties(stmt.executeQuery(String.format(GET_PROPERTY_BY_ID_SQL, id)));
  }

  /**
   * Метод возвращает список сущностей Fuel из базы данных, по ID сущности GasStation (относящихся к
   * ней).
   */
  private List<Fuel> getFuelListByStationId(Statement stmt, Integer id) throws SQLException {
    final String GET_FUEL_BY_ID_SQL = "SELECT * FROM fuels WHERE station_id = %d";
    return JdbcUtils.mapFuels(stmt.executeQuery(String.format(GET_FUEL_BY_ID_SQL, id)));
  }
}
