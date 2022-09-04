package com.company;

import static com.company.Island.ISLAND;

//класс для растений
public class Plant extends LiveObject {
    public Plant(int areaId, int currentAreaId, boolean initFlag) {
        super(areaId, currentAreaId, initFlag);
    }

    @Override
    public void run() {
        if (this.deleteFlag) return;
        //перед очередным ходом увеличиваем количество ходов с момента размножения
        //первым будет значение хода = 1
        this.currentBreedingPeriod++;
        //проверки на действия
        if (this.currentBreedingPeriod >= this.breedingPeriod) breed();
    }
}
