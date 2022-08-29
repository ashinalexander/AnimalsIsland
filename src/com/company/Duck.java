package com.company;
// Класс "Буйвол"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейсы "Травоядное" (Herbivorous) и "Хищник"(Predator)
public class Duck extends Animal implements Herbivorous, Predator{
    public Duck(int areaId, int currentAreaId, boolean initFlag) {
        super(areaId, currentAreaId, initFlag);
    }

    @Override
    public void run() {

    }
}
