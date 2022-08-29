package com.company;

import java.util.concurrent.ThreadLocalRandom;

import static com.company.Config.CONFIG;
import static com.company.Island.ISLAND;

//абстрактный класс для всех видов животных
public abstract class Animal extends LiveObject {
    protected int maxMoveSpeed; //максимальное количество локаций которое животное может преодолеть за один ход
    protected int moveSpeed; //скорость на ходу игры
    protected int moveDirection; //направление движения на ходу игры
    //идентификатор будущей локации (для перемещения)
    protected int futureAreaId;
    //координаты будущей локации (для перемещения)
    protected int futureAreaX;
    protected int futureAreaY;

    protected double maxFoodInAnimal; //максимальное количество еды в животном
    protected double starvingInStep; //количество еды, расходуемой животным за ход
    protected double foodInAnimal; //количество еды в животном

    public Animal(int areaId, int currentAreaId, boolean initFlag) {
        super(areaId, currentAreaId, initFlag);
        this.maxMoveSpeed = Config.CONFIG.getMaxMoveSpeed(liveObjectTypeId);
        this.maxFoodInAnimal = Config.CONFIG.getMaxFoodInAnimal(liveObjectTypeId);
        this.starvingInStep = Config.CONFIG.getStarvingInStep(liveObjectTypeId);
    }

    public void toBreed() {
        // "Размножаться"
    }

    public void selectDirectionToMove() {
        // "Выбрать направление для движения"
    }

    public void toMove() {
        // "Двигаться"
    }

    public void toStarvingDie() {
        // "Умереть от голода"
    }

    public void toEaten() {
        System.out.println(objectName + "-> съеден");
        // "Быть съеденным"
    }
}
