package com.company;
// класс "Орел"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейс "Хищник"(Predator)
public class Eagle extends Animal implements Predator{
    public Eagle(int areaId, int currentAreaId, boolean initFlag) {
        super(areaId, currentAreaId, initFlag);
    }

    @Override
    public void run() {

    }
}
