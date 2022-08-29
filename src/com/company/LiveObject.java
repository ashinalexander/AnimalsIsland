package com.company;

//import static com.company.Island.ISLAND;

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
    boolean initFlag;

    public LiveObject(int areaId, int currentAreaId, boolean initFlag) {
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
        this.currentLiveObjectId = currentLiveObjectId;
        //идентификатор локации в которой находится объект
        this.currentAreaId = areaId;
        int tmpAreaID = -1;
        for (int x = 0; x < areasCount[0]; x++) {
            for (int y = 0; y < areasCount[1]; y++) {
                tmpAreaID++;
                if (tmpAreaID == this.currentAreaId) {
                    this.currentAreaX = x;
                    this.currentAreaY = y;
                }
            }
        }
        //флаг того, объект создается при инициализации или нет
        this.initFlag = initFlag;
        //если объект создан при инициализации, то у него период размножения будет задан случайно
        if (initFlag == true)
            this.currentBreedingPeriod = ThreadLocalRandom.current().
                    nextInt(0, CONFIG.getBreedingPeriod(this.liveObjectTypeId) + 1);
        else
            this.currentBreedingPeriod = 0;
        synchronized (LOG) { //чтобы нити не мешали друг другу писать в лог
            //записываем соообщение в лог
            LOG.addToLog(objectName + " : создан");
        }
    }

    @Override
    public void run() {

    }

    public void toEaten() {

        // "Быть съеденным"
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


}
