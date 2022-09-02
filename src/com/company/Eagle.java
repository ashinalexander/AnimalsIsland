package com.company;

import static com.company.Island.ISLAND;

// класс "Орел"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейс "Хищник"(Predator)
public class Eagle extends Animal implements Predator{
    public Eagle(int areaId, int currentAreaId, boolean initFlag) {
        super(areaId, currentAreaId, initFlag);
    }

    @Override
    public void run() {
        ISLAND.areas.get(currentAreaId).appendFinishedObjects();
    }
}
