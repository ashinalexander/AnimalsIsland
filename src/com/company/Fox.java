package com.company;

import static com.company.Island.ISLAND;

// класс "Лиса"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейс "Хищник"(Predator)
public class Fox extends Animal implements Predator{
    public Fox(int areaId, int currentAreaId, boolean initFlag) {
        super(areaId, currentAreaId, initFlag);
    }

    @Override
    public void run() {
        ISLAND.areas.get(currentAreaId).appendFinishedObjects();
    }
}
