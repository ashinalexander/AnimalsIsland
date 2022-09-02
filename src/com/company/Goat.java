package com.company;

import static com.company.Island.ISLAND;

// Класс "Коза"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейс "Травоядное" (Herbivorous)
public class Goat extends Animal implements Herbivorous{
    public Goat(int areaId, int currentAreaId, boolean initFlag) {
        super(areaId, currentAreaId, initFlag);
    }

    @Override
    public void run() {
        ISLAND.areas.get(currentAreaId).appendFinishedObjects();
    }
}
