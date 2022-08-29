package com.company;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static com.company.Config.CONFIG;
import static com.company.Island.ISLAND;
import static com.company.Log.LOG;

public class Area implements Runnable {
    // индекс локации (для доступа)
    private int areaId;
    // координаты локации X и Y (для навигации животных)
    private int areaX;
    private int areaY;
    //наименование локации
    private String areaName;
    //массив с макс значениями популяций в локации
    private int[] objectsMaxCountsOnArea;
    //массив с живыми объектами в локации
    ArrayList<LiveObject> objectsInArea;
    //массив с потоками живых объектов в локации
    ArrayList<Thread> objectsThreadsInArea;
    //массив с текущими популяциями объектов на локации (не ID) для проверки при создании и перемещении
    private int[] currentObjectsInAreaCounts;
    //массив с популяциями животных на локации при первичной инициализации (заполняется рандомом)
    private int[] initialObjectsInAreaCounts;
    //индекс создаваемого объекта на локации
    //используется для доступа к объекту в массиве
    private int liveObjectId;
    private String[] objectsTypesNames;
    //колличество видов объектов на острове
    private int liveObjectsCount;
    boolean initFlag;

    Area(int areaId, int areaX, int areaY) {
        // индекс локации (для доступа)
        this.areaId = areaId;
        // координаты локации X и Y (для навигации животных)
        this.areaX = areaX;
        this.areaY = areaY;
        //наименование локации
        this.areaName = CONFIG.getAreaNamePrefix() + "[" + areaX + "." + areaY + "]";
        //колличество видов объектов на острове
        this.liveObjectsCount = CONFIG.getliveObjectsCount();
        //массив с макс значениями популяций в локации
        this.objectsMaxCountsOnArea = CONFIG.getObjectMaxCountOnArea();
        //массив с живыми объектами в локации
        this.objectsInArea = new ArrayList<>();
        //массив с потоками живых объектов в локации
        this.objectsThreadsInArea = new ArrayList<>();
        //массив с текущими популяциями объектов на локации (не ID) для проверки при создании и перемещении
        this.currentObjectsInAreaCounts = new int[CONFIG.getliveObjectsCount()];
        //массив с популяциями животных на локации при первичной инициализации (заполняется рандомом)
        this.initialObjectsInAreaCounts = new int[CONFIG.getliveObjectsCount()];
        //индекс создаваемого объекта на локации
        //используется для доступа к объекту в массиве
        this.liveObjectId = -1;
        //наименование типов животных и растений
        this.objectsTypesNames = CONFIG.getObjectsTypesNames();

        //записываем соообщение о создании локации

        synchronized (LOG) {
            LOG.addToLog(areaName + " : локация создана");
        }

        //создаем изначальные популяции рандомом
        for (int liveObjectTypeId = 0; liveObjectTypeId < liveObjectsCount; liveObjectTypeId++) {
            initialObjectsInAreaCounts[liveObjectTypeId] = ThreadLocalRandom.current().nextInt(0, objectsMaxCountsOnArea[liveObjectTypeId] + 1);
            if ((initialObjectsInAreaCounts[liveObjectTypeId]) > objectsMaxCountsOnArea[liveObjectTypeId])
                this.initialObjectsInAreaCounts[liveObjectTypeId] = objectsMaxCountsOnArea[liveObjectTypeId];
            for (int liveObject = 0; liveObject < initialObjectsInAreaCounts[liveObjectTypeId] - 1; liveObject++)
                createNewLiveObject(liveObjectTypeId, true);
        }
    }


    @Override
    public void run() {
        return;
    }

    //предоставление текущей популяции в локации
    //используется для проверок при создании нового объекта или перемещении
    public int getCurrentObjectsInAreaCounts(int liveObjectTypeId) {
        return currentObjectsInAreaCounts[liveObjectTypeId];
    }

    //инкремент текущего значения популяции в локации
    //используется при создании нового объекта или перемещении
    public void appendCurrentObjectsInAreaCount(int liveObjectTypeId) {
        this.currentObjectsInAreaCounts[liveObjectTypeId]++;
    }

    //декремент текущего значения популяции в локации
    //используется при удалении объекта или перемещении
    public void subtractCurrentObjectsInAreaCount(int liveObjectTypeId) {
        this.currentObjectsInAreaCounts[liveObjectTypeId]--;
    }

    public String getAreaName() {
        return areaName;
    }

    public int getLiveObjectId() {
        return liveObjectId;
    }

    public void appendLiveObjectId() {
        this.liveObjectId++;
    }

    public int getAreaX() {
        return areaX;
    }

    public int getAreaY() {
        return areaY;
    }

    //проверка на то, что макс. популяция для объекта в локации не достигнута
    public boolean isCreateAvaibleInArea(int liveObjectTyepId) {
        if ((currentObjectsInAreaCounts[liveObjectTyepId] + 1) <= objectsMaxCountsOnArea[liveObjectTyepId])
            return true;
        else {
            synchronized (LOG) { //чтобы нити не мешали друг другу писать в лог
                //записываем соообщение в лог
                LOG.addToLog(areaName + " : достигнута максимальная популяция " + CONFIG.getObjectTypeName(liveObjectTyepId) + " в локации");
            }
            return false;
        }
    }
    //создание нового объекта
    public void createNewLiveObject(int liveObjectTypeId, boolean initFlag) {
        if (isCreateAvaibleInArea(liveObjectTypeId)) {
            ISLAND.appendCountForName(liveObjectTypeId); //увеличиваем счетчик для именования
            ISLAND.appendCurrentObjectsTotalCounts(liveObjectTypeId); //увеличиваем общую популяцию на острове
            currentObjectsInAreaCounts[liveObjectTypeId]++;
            liveObjectId = objectsInArea.size();
            switch (liveObjectTypeId) {
                case 0 -> objectsInArea.add(new Plant(areaId,liveObjectId, initFlag));
                case 1 -> objectsInArea.add(new Wolf(areaId,liveObjectId, initFlag));
                case 2 -> objectsInArea.add(new Boa(areaId,liveObjectId, initFlag));
                case 3 -> objectsInArea.add(new Fox(areaId,liveObjectId, initFlag));
                case 4 -> objectsInArea.add(new Bear(areaId,liveObjectId, initFlag));
                case 5 -> objectsInArea.add(new Eagle(areaId,liveObjectId, initFlag));
                case 6 -> objectsInArea.add(new Horse(areaId,liveObjectId, initFlag));
                case 7 -> objectsInArea.add(new Deer(areaId,liveObjectId, initFlag));
                case 8 -> objectsInArea.add(new Rabbit(areaId,liveObjectId, initFlag));
                case 9 -> objectsInArea.add(new Mouse(areaId,liveObjectId, initFlag));
                case 10 -> objectsInArea.add(new Goat(areaId,liveObjectId, initFlag));
                case 11 -> objectsInArea.add(new Sheep(areaId,liveObjectId, initFlag));
                case 12 -> objectsInArea.add(new Boar(areaId,liveObjectId, initFlag));
                case 13 -> objectsInArea.add(new Buffalo(areaId,liveObjectId, initFlag));
                case 14 -> objectsInArea.add(new Duck(areaId,liveObjectId, initFlag));
                case 15 -> objectsInArea.add(new Caterpillar(areaId,liveObjectId, initFlag));
            }
            objectsThreadsInArea.add(new Thread(objectsInArea.get(liveObjectId)));
        }
    }
}


