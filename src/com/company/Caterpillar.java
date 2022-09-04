package com.company;

import static com.company.Island.ISLAND;

// Класс "Гусеница"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейс "Травоядное" (Herbivorous)
public class Caterpillar extends Animal implements Herbivorous{
    public Caterpillar(int areaId, int currentliveObjectId, boolean initFlag) {
        super(areaId, currentliveObjectId, initFlag);
    }

    @Override
    public void run() {
//        ISLAND.getArea(currentAreaId).appendFinishedObjects();
    }
}
