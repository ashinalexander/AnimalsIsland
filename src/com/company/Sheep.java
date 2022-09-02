package com.company;

import static com.company.Island.ISLAND;

// Класс "Овца"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейс "Травоядное" (Herbivorous)
public class Sheep extends Animal implements Herbivorous{
    public Sheep(int areaId, int currentAreaId, boolean initFlag) {
        super(areaId, currentAreaId, initFlag);
    }

    @Override
    public void run() {
        ISLAND.areas.get(currentAreaId).appendFinishedObjects();
    }
}
