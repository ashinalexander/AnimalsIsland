package com.company;

import static com.company.Island.ISLAND;

//класс для растений
public class Plant extends LiveObject {
    public Plant(int areaId, int currentAreaId, boolean initFlag) {
        super(areaId, currentAreaId, initFlag);
    }

    @Override
    public void run() {
        ISLAND.areas.get(currentAreaId).appendFinishedObjects();
    }
}
