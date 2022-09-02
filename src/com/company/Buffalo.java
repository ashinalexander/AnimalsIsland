package com.company;

import static com.company.Island.ISLAND;

// Класс "Буйвол"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейс "Травоядное" (Herbivorous)
public class Buffalo extends Animal implements Herbivorous{
    public Buffalo(int areaId, int currentAreaId, boolean initFlag) {
        super(areaId, currentAreaId, initFlag);
    }

    @Override
    public void run() {
        ISLAND.areas.get(currentAreaId).appendFinishedObjects();
    }
}
