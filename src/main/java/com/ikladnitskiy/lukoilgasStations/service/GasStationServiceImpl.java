package com.ikladnitskiy.lukoilgasStations.service;

import com.ikladnitskiy.lukoilgasStations.model.GasStation;
import com.ikladnitskiy.lukoilgasStations.repository.GasStationRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для получения информации об АЗС.
 */
@Slf4j
@Service
public class GasStationServiceImpl implements GasStationService {

  private CollectorService collectorService;
  private GasStationRepository repository;

  @Autowired
  public GasStationServiceImpl(CollectorService collectorService, GasStationRepository repository) {
    this.collectorService = collectorService;
    this.repository = repository;
  }

  /**
   * Метод инициализирует базу данных и размещает в ней двенадцать записей с информацией об АЗС,
   * полученных с сервера данных.
   */
  @PostConstruct
  public void setUpData() {
    log.info("Создание таблиц в базе данных...");
    repository.createTables();
    try {
      List<Integer> gasStationIds = collectorService.getGasStationIdList();
      gasStationIds.stream().limit(12).forEach(id -> {
        try {
          GasStation station = collectorService.getGasStation(id);
          repository.saveGasStation(station);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      });
    } catch (Exception ex) {
      log.error("Произошла ошибка предзагрузки данных!" + "\n" + ex.getMessage());
    }
  }

  /**
   * Метод возвращает количество АЗС, хранящихся в базе данных.
   */
  @Override
  public Integer getCountGasStation() {
    return repository.getCountGasStations();
  }

  /**
   * Метод возвращает список ID сущностей GasStation, хранящихся в базе
   * данных.
   */
  @Override
  public List<Integer> getGasStationNumberList() {
    return repository.getGasStationIdsList();
  }

  /**
   * Метод возвращает сущность GasStation, хранящуюся в базе данных по ее ID.
   */
  @Override
  public GasStation getGasStationById(Integer id) {
    return repository.getGasStationById(id);
  }

  /**
   * Метод загружает с сервера данных всю имеющуюся информацию об АЗС и сохраняет в базу данных.
   * Ввиду большого объема данных и линейности алгоритма загрузки и сохранения данных - полная
   * загрузка может занять длительное время.
   */
  @Override
  public void refreshData() throws Exception {
    repository.createTables();
    List<Integer> gasStationIds = collectorService.getGasStationIdList();
    gasStationIds.forEach(id -> {
      try {
        GasStation station = collectorService.getGasStation(id);
        repository.saveGasStation(station);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });
  }
}
