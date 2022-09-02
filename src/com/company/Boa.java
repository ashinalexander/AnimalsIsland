package com.company;

import static com.company.Island.ISLAND;

// класс "Удав"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейс "Хищник"(Predator)
public class Boa extends Animal implements Predator{
    public Boa(int areaId, int currentAreaId, boolean initFlag) {
        super(areaId, currentAreaId, initFlag);
    }

    @Override
    public void run() {
        ISLAND.areas.get(currentAreaId).appendFinishedObjects();
    }
}
