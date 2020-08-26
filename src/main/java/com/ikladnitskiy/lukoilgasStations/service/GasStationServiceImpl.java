package com.ikladnitskiy.lukoilgasStations.service;

import com.ikladnitskiy.lukoilgasStations.model.GasStation;
import com.ikladnitskiy.lukoilgasStations.repository.GasStationRepository;
import java.util.List;
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
   * Метод возвращает количество АЗС, хранящихся в базе данных.
   */
  @Override
  public Integer getCountGasStation() {
    return repository.getCountGasStations();
  }

  /**
   * Метод возвращает номерной список (являющихся ID) сущностей GasStation, хранящихся в базе
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
   * Метод загружает с сервера данных информацию о двенадцати АЗС и сохраняет их в базу данных.
   * Добавлен для случаев, когда нет необходимости загружать весь объем данных об АЗС.
   */
  @Override
  public void getTwelveRecords() throws Exception {
    repository.createTables();
    List<Integer> gasStationIds = collectorService.getGasStationIdList();
    gasStationIds.stream().limit(12).forEach(id -> {
      try {
        GasStation station = collectorService.getGasStation(id);
        repository.saveGasStation(station);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });
  }

  /**
   * Метод загружает с сервера данных всю имеющуюся информацию об АЗС и сохраняет в базу данных.
   * Ввиду большого объема данных и линейности алгоритма загрузки и сохранения данных - полная
   * загрузка может занять длительное время. Для загрузки части данных предпочтительнее использовать
   * другой метод {@see getTwelveRecords}.
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
