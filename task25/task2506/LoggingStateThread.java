package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread thread;

    public LoggingStateThread(Thread thread){
        this.thread = thread;
        System.out.println(thread.getState());
    }

    @Override
    public void run() {
        State state = null;
        State lState = thread.getState();
        while (lState != State.TERMINATED){
            state = thread.getState();
            if(state != lState){
                System.out.println(state);
                lState = state;
            }
        }
        thread.interrupt();
    }
}
