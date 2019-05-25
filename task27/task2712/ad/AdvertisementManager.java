package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos(){


            List<Advertisement> videos = storage.list();
            if (storage.list().isEmpty())
                throw new NoVideoAvailableException();

            List<Advertisement> bestAds = new Helper().getAds();

            Collections.sort(bestAds, new Comparator<Advertisement>() {
                @Override
                public int compare(Advertisement video1, Advertisement video2) {
                    long dif = video2.getAmountPerOneDisplaying() - video1.getAmountPerOneDisplaying();
                    if (dif == 0) dif = video2.getDuration() - video1.getDuration();
                    return (int) dif;
                }
            });

            long amount = 0;
            int duration = 0;

            for(Advertisement ad: bestAds){
                amount += ad.getAmountPerOneDisplaying();
                duration += ad.getDuration();
            }
        StatisticManager statisticManager = StatisticManager.getInstance();
        statisticManager.register(new VideoSelectedEventDataRow(bestAds, amount, duration));

        for (Advertisement ad : bestAds) {
            ConsoleHelper.writeMessage(ad.getName() + " is displaying... " +
                        ad.getAmountPerOneDisplaying() + ", " +
                        1000 * ad.getAmountPerOneDisplaying() / ad.getDuration());
            ad.revalidate();
        }
        }

        private class Helper {
            private int anInt = 0;
            private int max = 0;
            private int number = 0;
            private List<Advertisement> advertisements1 = new ArrayList<>();
            private List<Advertisement> advertisements = new ArrayList<>();

            public List<Advertisement> getAds() {
                for (Advertisement ad : storage.list()) {
                    if (ad.getDuration() <= timeSeconds && ad.getHits() > 0)
                        advertisements.add(ad);
                }
                if (advertisements.isEmpty()) {
                    throw new NoVideoAvailableException();
                } else getBestAds(new BinaryPattern(advertisements.size()));
                return advertisements1;
            }

            public void getBestAds(BinaryPattern pattern) {
                while (true) {
                    checkAds(pattern.getPattern());
                    if (!pattern.full()) pattern.increment();
                    else break;
                    getBestAds(pattern);
                }
            }

            private void checkAds(int[] pattern) {
                int price = 0;
                int time = 0;
                List<Advertisement> list = new ArrayList<>();
                for (int i = 0; i < advertisements.size(); i++) {
                    price += advertisements.get(i).getAmountPerOneDisplaying() * pattern[i];
                    time += advertisements.get(i).getDuration() * pattern[i];
                    if (pattern[i] == 1) list.add(advertisements.get(i));
                }
                if (time > timeSeconds) return;
                if (!(price > anInt)) {
                    if (!(price == anInt && time > max)) {
                        if (!(price == anInt && time == max && list.size() < number)) {
                            return;
                        }
                    }
                }
                advertisements1 = list;
                anInt = price;
                max = time;
                number = list.size();
            }

            private class BinaryPattern {
                private int length;
                private int count;

                public BinaryPattern(int size) {
                    this.length = size;
                    this.count = 0;
                }

                public int[] getPattern() {
                    String regString = Integer.toBinaryString(count);
                    int dif = length - regString.length();
                    int[] pattern = new int[length];
                    for (int j = dif; j < pattern.length; j++) {
                        if (regString.charAt(j - dif) == '1') pattern[j] = 1;
                        else pattern[j] = 0;
                    }
                    return pattern;
                }

                public void increment() {
                    count++;
                }

                ;

                public boolean full() {
                    return count == (int) Math.pow(2, length) - 1;
                }
            }
        }

}
