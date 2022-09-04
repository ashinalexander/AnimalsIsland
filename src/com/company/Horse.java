package com.company;

import static com.company.Island.ISLAND;

// Класс "Лошадь"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейс "Травоядное" (Herbivorous)
public class Horse extends Animal implements Herbivorous{
    public Horse(int areaId, int currentliveObjectId, boolean initFlag) {
        super(areaId, currentliveObjectId, initFlag);
    }

    @Override
    public void run() {
//        ISLAND.getArea(currentAreaId).appendFinishedObjects();
    }
}
