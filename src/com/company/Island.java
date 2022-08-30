package com.company;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.company.Config.CONFIG;
import static com.company.Log.LOG;

public class Island implements Runnable {
    //public class Island implements Runnable {
    //Singleton - в программе предусмотрено существование только одного острова
    public static final Island ISLAND = new Island();
    // количество локаций на острове в массиве [x,y]
    private int[] areasCount;
    // типы объектов животных и растений
    private String[] liveObjectsTypes;
    // наименования титипов объектов животных и растений (может использоваться псевдографика)
    private String[] objectsTypesNames;
    //массив для локаций
    ArrayList<Area> areas;
    //массив для потоков локаций
    ArrayList<Thread> areasThreds;
    //выполняющийся ход имитации
    private int step;
    //индекс создаваемой зоны
    private int areaId;
    //текущее количество объектов на острове (не индекс)
    private int[] currentObjectsTotalCounts;
    //общее количество когда-либо созданных объектов по типам - для именования объектов (не индекс)
    private int[] countsForNames;
    //колличество видов объектов на острове
    private int liveObjectsCount;

    public static Island getInstance() {
        return ISLAND;
    }

    private Island() {
        // типы объектов животных и растений
        this.liveObjectsTypes = CONFIG.getLiveObjectsTypes();
        //колличество видов объектов на острове
        this.liveObjectsCount = CONFIG.getliveObjectsCount();
        //текущее количество объектов на острове (не индекс)
        this.currentObjectsTotalCounts = new int[liveObjectsCount];
        //общее количество когда-либо созданных объектов по типам - для именования объектов (не индекс)
        this.countsForNames = new int[liveObjectsCount];
        //наименование типов животных и растений
        this.objectsTypesNames = CONFIG.getObjectsTypesNames();
        // количество локаций на острове в массиве [x,y]
        this.areasCount = CONFIG.getAreasCount();
        //массив для локаций
        this.areas = new ArrayList<>();
        //массив для потоков локаций
        this.areasThreds = new ArrayList<>();
        //выполняющийся ход имитации
        step = 0;
        //индекс локации (для доступа)
        this.areaId = -1;
    }

    @Override
    public void run() {
        Thread log = new Thread(LOG);//создаем объект лога
        log.start();//        активируем лог

//      создаем локации и передаем в них их индексы
        for (int areaX = 0; areaX < areasCount[0]; areaX++) { //индекс расположения локации по оси X
            for (int areaY = 0; areaY < areasCount[1]; areaY++) { //индекс расположения локации по оси Y
                areaId++; //индекс локации (для доступа)
                areas.add(areaId, new Area(areaId, areaX, areaY)); //добавляем новую локацию в массив
                areasThreds.add(new Thread(areas.get(areaId))); //добавляем локации в потоки
            }
        }

        for (int areaId = 0; areaId < areas.size(); areaId++) {
            String str = areas.get(areaId).getAreaName() + " : инициировано ";
            for (int objectTypeId = 0; objectTypeId < liveObjectsCount; objectTypeId++) {
                str = str+ objectsTypesNames[objectTypeId]+" "+areas.get(areaId).getCurrentObjectsInAreaCounts(objectTypeId)+"\t";
            }
            LOG.addToLog(str);
        }
        while (step < 3) { //считаем ходы игры от 0
            step++;
            synchronized (LOG) {//записываем соообщение в лог
                LOG.addToLog("Island : ход имитации "+step);
            }
//            for (areaId = 0; areaId < (areasCount[0]*areasCount[1]); areaId++) {
//                areasThreds.get(areaId).start();
//                try {
//                    areasThreds.get(areaId).join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

        }
    }

    //инкремент текущего количества определенных объектов на острове по id типа объекта
    //при создании нового объекта
    public void appendCurrentObjectsTotalCounts(int liveObjectTypeId) {
        this.currentObjectsTotalCounts[liveObjectTypeId]++;
    }

    //декремент текущего количества определенных объектов на острове по id типа объекта
    //используется при удалении объекта
    public void subtractCurrentObjectsTotalCounts(int liveObjectTypeId) {
        this.currentObjectsTotalCounts[liveObjectTypeId]--;
    }

    //получение общего числа созданных объектов определенного типа на острове по id
    //используется для именования создаваемых объектов
    public int getCountForName(int liveObjectTypeId) {
        return countsForNames[liveObjectTypeId];
    }

    //инкремент общего числа созданных объектов определенного типа на острове по id
    //используется для именования создаваемых объектов
    public void appendCountForName(int liveObjectTypeId) {
        this.countsForNames[liveObjectTypeId]++;
    }
}
