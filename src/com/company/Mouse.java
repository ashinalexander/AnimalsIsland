package com.company;
// Класс "Мышь"
// наследуется от класса "Животное" (Animal)
// имплементирует интерфейс "Травоядное" (Herbivorous)
public class Mouse extends Animal implements Herbivorous, Predator{
    public Mouse(int areaId, int currentAreaId, boolean initFlag) {
        super(areaId, currentAreaId, initFlag);
    }

    @Override
    public void run() {

    }
}
