package com.company;

public interface configInterface {
    public int getAreasCount();
    public void setAreasCount(int areasCount);
    public int getliveObjectsCount();
    public double getWeight(int liveObjectTypeId);
    public int getMaxThisObjectInArea(int liveObjectTypeId);
    public int getObjectMaxCountOnIsland(int liveObjectTypeId);
    public int getMaxMoveSpeed(int liveObjectTypeId);
    public double getMaxFoodInAnimal(int liveObjectTypeId);
    public String getObjectTypeName(int liveObjectTypeId);
    public int getMaxNumberOfChild(int liveObjectTypeId);
    public int getBreedingPeriod(int liveObjectTypeId);
    public boolean getBreedingPairFlag(int liveObjectTypeId);
    public double getStarvingInStep(int liveObjectTypeId);
    public String getConfigPath();


}
