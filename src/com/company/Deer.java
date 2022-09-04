package com.company;

import static com.company.Island.ISLAND;

// Класс "Олень"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейс "Травоядное" (Herbivorous)
public class Deer extends Animal implements Herbivorous{
    public Deer(int areaId, int currentliveObjectId, boolean initFlag) {
        super(areaId, currentliveObjectId, initFlag);
    }

    @Override
    public void run() {
//        ISLAND.getArea(currentAreaId).appendFinishedObjects();
    }
}
