package com.ikladnitskiy.lukoilgasStations.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
}
