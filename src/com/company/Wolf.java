package com.company;

import static com.company.Island.ISLAND;

// класс "Волк"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейс "Хищник"(Predator)
public class Wolf extends Animal implements Predator{
    public Wolf(int areaId, int currentAreaId, boolean initFlag) {
        super(areaId, currentAreaId, initFlag);
    }

    @Override
    public void run() {
        ISLAND.areas.get(currentAreaId).appendFinishedObjects();
    }
}
