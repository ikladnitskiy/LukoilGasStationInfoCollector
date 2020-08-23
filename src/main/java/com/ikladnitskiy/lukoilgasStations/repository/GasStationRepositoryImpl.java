package com.ikladnitskiy.lukoilgasStations.repository;

import com.ikladnitskiy.lukoilgasStations.model.GasStation;
import com.ikladnitskiy.lukoilgasStations.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
}


