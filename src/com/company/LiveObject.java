package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.company.Config.CONFIG;
import static com.company.Island.ISLAND;
import static com.company.Log.LOG;

public abstract class LiveObject implements Runnable {
    //идентификатор типа объекта (животного или растения)
    protected int liveObjectTypeId;
    //наименование типа объекта (животного или растения)
    protected String objectTypeName;
    //наименование объекта
    protected String objectName;
    //вес объекта
    protected double weight;
    //максимум объектов данного типа в локации
    protected int maxThisObjectsInArea;
    //период размножения
    protected int breedingPeriod;
    // количество ходов с момента размножения
    protected int currentBreedingPeriod;
    //id в массиве текущей локации (для доступа к объекту)
    protected int currentLiveObjectId;
    // количество локаций на острове в массиве [x,y]
    protected int[] areasCount;
    //идентификатор локации в которой находится объект
    protected int currentAreaId;
    //координаты локации в которой находится объект
    protected int currentAreaX;
    protected int currentAreaY;
    //флаг инициализации (выставляется при создании объекта)
    protected boolean initFlag;
    //флаг того, что объект был съеден
    protected boolean wasEaten;
    //идентификатор объекта, который съел
    protected int killerId;
    //флаг того, что объект должен быть удален
    protected boolean deleteFlag;
    protected boolean moveFlag;

    public LiveObject(int areaId, int currentliveObjectId, boolean initFlag) {
        //идентификатор типа объекта (животного или растения)
        this.liveObjectTypeId = CONFIG.getLiveObjectTypeId(this.getClass().getSimpleName());
        //наименование типа объекта (животного или растения)
        this.objectTypeName = CONFIG.getObjectTypeName(this.liveObjectTypeId);
        //наименование объекта
        this.objectName = new String(objectTypeName + "[" + ISLAND.getCountForName(this.liveObjectTypeId) + "]");
        //вес объекта
        this.weight = CONFIG.getWeight(this.liveObjectTypeId);
        //максимум объектов данного типа в локации
        this.maxThisObjectsInArea = CONFIG.getObjectMaxCountOnArea(this.liveObjectTypeId);
        //период размножения
        this.breedingPeriod = CONFIG.getBreedingPeriod(this.liveObjectTypeId);
        // количество локаций на острове в массиве [x,y]
        this.areasCount = CONFIG.getAreasCount();
        //id в массиве текущей локации (для доступа к объекту)
        this.currentLiveObjectId = currentliveObjectId;
        //идентификатор локации в которой находится объект
        this.currentAreaId = areaId;
        //флаг того, объект создается при инициализации или нет
        this.initFlag = initFlag;
        //если объект создан при инициализации, то у него период размножения будет задана случайно
        Random random = new Random();
        if (initFlag == true) {
            this.currentBreedingPeriod = ThreadLocalRandom.current().
                    nextInt(0, CONFIG.getBreedingPeriod(this.liveObjectTypeId) + 1);
        } else {
            this.currentBreedingPeriod = 0;
        }
        //флаг того, что объект был съеден
        this.wasEaten = false;
        //флаг того, что объект должен быть удален
        this.deleteFlag = false;
    }

    protected LiveObject() {
    }

    //размножаться
    protected void breed() {
        ISLAND.getArea(currentAreaId).createObject(liveObjectTypeId, false);
//        System.out.println(objectName + " : Создал новый объект");
    }

    //быть съеденным
    public void beEaten(int killerId) {
        if (wasEaten) {
            //сообщение о том, что съели
            LOG.addToLog(objectName + " : был съеден объектом " + ISLAND.getArea(currentAreaId).getObject(killerId));
            deleteFlag = true;
        }
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public int getLiveObjectTypeId() {
        return liveObjectTypeId;
    }

    public String getObjectTypeName() {
        return objectTypeName;
    }

    public double getWeight() {
        return weight;
    }

    public int getCurrentBreedingPeriod() {
        return currentBreedingPeriod;
    }

    public int getCurrentLiveObjectId() {
        return currentLiveObjectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setCurrentLiveObjectId(int currentLiveObjectId) {
        this.currentLiveObjectId = currentLiveObjectId;
    }

    public boolean isMoveFlag() {
        return moveFlag;
    }


}
