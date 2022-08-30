package com.company;

import java.util.ArrayList;

public class Log implements Runnable {
    //Singleton - в программе предусмотрено существование только одного объекта с логами
    public static final Log LOG = new Log();
    volatile private ArrayList<String> islandLog = new ArrayList<>(); //массив с сообщениями логов
    volatile private int printMessageId = 0; //индекс сообщения которое нужно распечатать следующим

    boolean viewLog = true;

    public static Log getInstance() {
        return LOG;
    }

    private Log() {
    }

    @Override
    public void run() {
        while (viewLog) {

            for (int i = printMessageId; i < islandLog.size(); i++) {
                synchronized (this) {
                    System.out.println(islandLog.get(i));
                }
                printMessageId++;
            }
        }
        return;
    }

    public void stopLog() {
        LOG.viewLog = false;
    }

    public synchronized void addToLog(String logMessage) {
        this.islandLog.add(logMessage);
    }
}