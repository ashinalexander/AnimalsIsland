package com.company;

import static com.company.Island.ISLAND;

// класс "Удав"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейс "Хищник"(Predator)
public class Boa extends Animal implements Predator{
    public Boa(int areaId, int currentliveObjectId, boolean initFlag) {
        super(areaId, currentliveObjectId, initFlag);
    }
}
