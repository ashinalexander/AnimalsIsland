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
        int sleppy;
        while (viewLog) {

            for (int i = printMessageId; i < islandLog.size(); i++) {
                System.out.println(islandLog.get(i));
                printMessageId++;
                sleppy = 0;
                if (printMessageId == islandLog.size() - 1) {
                    sleppy += 10;
                    try {
                        Thread.sleep(sleppy);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (sleppy >= 500) {
                        System.out.println("Работа логирования завершена");
                        viewLog = false;
                    }
                }
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