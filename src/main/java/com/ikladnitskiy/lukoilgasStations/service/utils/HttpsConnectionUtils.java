package com.ikladnitskiy.lukoilgasStations.service.utils;

import com.sun.net.ssl.internal.ssl.Provider;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import lombok.extern.slf4j.Slf4j;

/**
 * Утилитный класс для получения соединения с сервисом данных.
 */
@Slf4j
public class HttpsConnectionUtils {

  private static final String HOST_ROLL_STATIONS = "https://auto.lukoil.ru/api/cartography/"
      + "GetCountryDependentSearchObjectData?form=gasStation&country=RU";
  private static final String HOST_STATION =
      "https://auto.lukoil.ru/api/cartography/GetObjects?ids=gasStation";

  /**
   * Метод для получения HttpsURLConnection с данными списка АЗС.
   */
  public static HttpsURLConnection getRollStationsConnection() {
    HttpsURLConnection conn = null;
    try {
      doTrustToCertificates();
      URL url = new URL(HOST_ROLL_STATIONS);
      conn = (HttpsURLConnection) url.openConnection();
      conn.setConnectTimeout(10000);
    } catch (IOException ex) {
      log.info("Ошибка открытия соединения в методе getRollStationConnection: {}", ex.getMessage());
    }
    return conn;
  }

  /**
   * Метод для получения HttpsURLConnection с данными для конкретной АЗС.
   */
  public static HttpsURLConnection getGasStationConnection(Integer numberOfStation) {
    HttpsURLConnection conn = null;
    try {
      doTrustToCertificates();
      URL url = new URL(HOST_STATION + numberOfStation);
      conn = (HttpsURLConnection) url.openConnection();
      conn.setConnectTimeout(10000);
    } catch (IOException ex) {
      log.info("Ошибка открытия соединения в методе getGasStationConnection: {}. Номер АЗС {}",
          ex.getMessage(), numberOfStation);
    }
    return conn;
  }

  /**
   * Закрытие HttpsURLConnection.
   */
  public static void closeConnection(HttpsURLConnection conn) {
    if (conn != null) {
      conn.disconnect();
    }
  }

  /**
   * Метод для подтверждения любого SSL сертификата. Категорически не рекомендуется применять.
   * Использую для того, чтобы не редактировать JAVA_HOME/jre/lib/security/cacerts после замены
   * сертификата.
   */
  private static void doTrustToCertificates() {
    Security.addProvider(new Provider());
    TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {

          @Override
          public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates,
              String s) {

          }

          @Override
          public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates,
              String s) {

          }

          public X509Certificate[] getAcceptedIssuers() {
            return null;
          }
        }
    };
    SSLContext context = null;
    try {
      context = SSLContext.getInstance("SSL");
      context.init(null, trustAllCerts, new SecureRandom());
    } catch (Exception ex) {
      log.info("Ошибка инициализации SSLContext: {}", ex.getMessage());
    }
    HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
    HostnameVerifier hv = (urlHostName, session) -> {
      if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
        log.info("Warning: URL host '" + urlHostName + "' is different to SSLSession host '"
            + session.getPeerHost() + "'.");
      }
      return true;
    };
    HttpsURLConnection.setDefaultHostnameVerifier(hv);
  }
}
