package com.company;

import static com.company.Island.ISLAND;

// класс "Медведь"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейс "Хищник"(Predator)
public class Bear extends Animal implements Predator {
    public Bear(int areaId, int currentliveObjectId, boolean initFlag) {
        super(areaId, currentliveObjectId, initFlag);
    }
}

