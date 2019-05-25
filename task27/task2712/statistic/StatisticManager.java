package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticManager() {
    }

    public void register(EventDataRow data){
        statisticStorage.put(data);
    }

    private class StatisticStorage{
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }

        public StatisticStorage(){
            for (int i = 0; i < EventType.values().length; i ++){
                storage.put(EventType.values()[i], new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data){
            storage.get(data.getType()).add(data);
        }
    }
}
