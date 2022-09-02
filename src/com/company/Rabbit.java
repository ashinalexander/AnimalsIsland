package com.company;

import static com.company.Island.ISLAND;

// Класс "Кролик"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейс "Травоядное" (Herbivorous)
public class Rabbit extends Animal implements Herbivorous{
    public Rabbit(int areaId, int currentAreaId, boolean initFlag) {
        super(areaId, currentAreaId, initFlag);
    }

    @Override
    public void run() {
        ISLAND.areas.get(currentAreaId).appendFinishedObjects();
    }
}
