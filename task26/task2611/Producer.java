package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            int i = 1;
            while (true) {
                StringBuffer sb = new StringBuffer("Some text for ");
                map.put(i + "", sb.append(i).toString());
                i++;
                Thread.sleep(500);
            }
        } catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " thread was terminated");
        }
    }
}
