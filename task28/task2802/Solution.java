package com.javarush.task.task28.task2802;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/
public class Solution {

    public static void main(String[] args) {
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }

    public static class AmigoThreadFactory implements ThreadFactory{
        private static AtomicInteger A = new AtomicInteger(0);
        private final AtomicInteger B = new AtomicInteger(0);
        private ThreadGroup tg = Thread.currentThread().getThreadGroup();
        private final int C = A.incrementAndGet();

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(tg, r);
            t.setDaemon(false);
            t.setPriority(Thread.NORM_PRIORITY);
            t.setName(Thread.currentThread().getThreadGroup().getName() + "-pool-" + C + "-thread-" + B.incrementAndGet());
            return t;
        }
    }
}
