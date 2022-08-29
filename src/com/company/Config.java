package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config implements Serializable {
    private static final long serialVersionUID = 1L; //переменная для сериализации
    private static final String configPath = "config.dat"; //файл хранения текущей конфигурации
    public static final Config CONFIG = new Config(); //константа кофигурации для создания Singleton

    private Config() {
    } //приватный конструктор - закрыли создание объекта конфигурации через new

    public static Config getInstance() {
        return CONFIG;
    } // создание объекта через метод, возвращающий константу

    //префикс имени локации
    private String areaNamePrefix = "Area";
    private int areasCount[] = {2, 2}; //колличество локаций на острове
//    private int areasCount[] = {100, 20}; //колличество локаций на острове

    // типы объектов животных и растений
    private String[] liveObjectsTypes = {"Plant", "Wolf", "Boa", "Fox", "Bear", "Eagle", "Horse", "Deer",
            "Rabbit", "Mouse", "Goat", "Sheep", "Boar", "Buffalo", "Duck", "Caterpillar"};
    // наименования титипов объектов животных и растений
    private String[] objectsTypesNames = {"Растениe", "Волк", "Удав", "Лиса", "Медведь", "Орел",
            "Лошадь", "Олень", "Кролик", "Мышь", "Коза", "Овца", "Кабан", "Буйвол", "Утка", "Гусеница"};
    private String[] objectsTypesPsevdographic = {"\uD83C\uDF31", "\uD83D\uDC3A", "\uD83D\uDC0D", "\uD83E\uDD8A", "\uD83D\uDC3B", "\uD83E\uDD85",
            "\uD83D\uDC0E", "\uD83E\uDD8C", "\uD83D\uDC07", "\uD83D\uDC01", "\uD83D\uDC10", "\uD83D\uDC11", "\uD83D\uDC17", "\uD83D\uDC03", "\uD83E\uDD86", "\uD83D\uDC1B"};

    // веса животных и растений
    private double[] weights = {1, 50, 15, 8, 500, 6, 400, 300, 2, 0.05, 60, 70, 400, 700, 1, 0.01};
    // максимального количества каждого типа в локации
    private int[] objectsMaxCountsOnArea = {200, 30, 30, 30, 5, 20, 20, 20, 150, 500, 140, 140, 50, 10, 200, 1000};
    //    //массив значений максимального количества каждого типа на острове
//    private int[] objectsMaxCountsOnIsland = {areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[0],
//            areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[1],
//            areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[2],
//            areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[3],
//            areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[4],
//            areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[5],
//            areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[6],
//            areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[7],
//            areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[8],
//            areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[9],
//            areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[10],
//            areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[11],
//            areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[12],
//            areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[13],
//            areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[14],
//            areasCount[0]*areasCount[1]*objectsMaxCountsOnArea[15]};
    // массив значений максимального количества перемещений между локациями за один ход для кажддого типа
    private int[] maxMoveSpeeds = {0, 3, 1, 2, 2, 3, 4, 4, 2, 1, 3, 3, 2, 3, 4, 0};
    // массив значений максимального количества потребляемой еды для кажддого типа
    private double[] maxFoodInAnimals = {0, 8, 3, 2, 80, 1, 60, 50, 0.45, 0.01, 10, 15, 50, 100, 0.15, 0.01};
    // массив процентных значений поедания между различными объектами
    private int[][] eatingProbabilities = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //"Растениe"
            {0, 0, 0, 0, 0, 0, 10, 15, 60, 80, 60, 70, 15, 10, 40, 0}, //"Волк"
            {0, 0, 0, 15, 0, 0, 0, 0, 20, 40, 0, 0, 0, 0, 10, 0}, //"Удав"
            {0, 0, 0, 0, 0, 0, 0, 0, 70, 90, 0, 0, 0, 0, 60, 40}, //"Лиса"
            {0, 0, 80, 0, 0, 0, 40, 80, 80, 90, 70, 70, 50, 20, 10, 0}, //"Медведь"
            {0, 0, 0, 10, 0, 0, 0, 0, 90, 90, 0, 0, 0, 0, 80, 0}, //"Орел"
            {100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //"Лошадь"
            {100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //"Олень"
            {100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //"Кролик"
            {100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90}, //"Мышь"
            {100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //"Коза"
            {100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //"Овца"
            {100, 0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 90}, //"Кабан"
            {100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //"Буйвол"
            {100, 0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 90}, //"Утка"
            {100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}; //"Гусеница"

//  количество приплода при размножении
//  private int[] maxNumbersOfChilds = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
//  период размножения
    private int[] breedingPeriods = {3, 45, 9, 60, 365, 182, 365, 365, 8, 3, 182, 365, 73, 365, 33, 2};
//  флаги необходимости пары для размножения
//  private boolean[] breedingPairFlagsArray = {false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false};
//  количество еды, расходуемой животным за ход
    private double[] starvingsInStep = {0, 0.8, 0.3, 0.2, 8, 0.1, 6, 5, 0.045, 0.001, 1, 1.5, 5, 10, 0.015, 0.001};

    public String getAreaNamePrefix() {
        return areaNamePrefix;
    }

    public int[] getAreasCount() {
        return areasCount;
    }

    public void setAreasCount(int areasCount1, int areasCount2) {
        this.areasCount[0] = areasCount1;
        this.areasCount[1] = areasCount2;
    }
    //передаем количество видов объектов
    public int getliveObjectsCount() {
        return liveObjectsTypes.length;
    }

    public String[] getLiveObjectsTypes() {
        return liveObjectsTypes;
    }

    public String getLiveObjectsTypes(int liveObjectTypeId) {
        return liveObjectsTypes[liveObjectTypeId];
    }

    public double getWeight(int liveObjectTypeId) {
        return weights[liveObjectTypeId];
    }

    public void setWeight(int liveObjectTypeId, double weight) {
        this.weights[liveObjectTypeId] = weight;
    }

    public int[] getObjectMaxCountOnArea() {
        return objectsMaxCountsOnArea;
    }

    public int getObjectMaxCountOnArea(int liveObjectTypeId) {
        return objectsMaxCountsOnArea[liveObjectTypeId];
    }

    public void setObjectMaxCountOnArea(int liveObjectTypeId, int objectMaxCountOnArea) {
        this.objectsMaxCountsOnArea[liveObjectTypeId] = objectMaxCountOnArea;
    }

//    public synchronized int[] getObjectsMaxCountOnIsland() {
//        return objectsMaxCountsOnIsland;
//    }
//    public int getObjectsMaxCountOnIsland(int liveObjectTypeId) {
//        return objectsMaxCountsOnIsland[liveObjectTypeId];
//    }
//
//    public void setObjectsMaxCountOnIsland(int liveObjectTypeId, int objectMaxCountOnIsland) {
//        this.objectsMaxCountsOnIsland[liveObjectTypeId] = objectMaxCountOnIsland;
//    }

    public int getMaxMoveSpeed(int liveObjectTypeId) {
        return maxMoveSpeeds[liveObjectTypeId];
    }

    public void setMaxMoveSpeed(int liveObjectTypeId, int maxMoveSpeed) {
        this.maxMoveSpeeds[liveObjectTypeId] = maxMoveSpeed;
    }

    public double getMaxFoodInAnimal(int liveObjectTypeId) {
        return maxFoodInAnimals[liveObjectTypeId];
    }

    public void setMaxFoodInAnimal(int liveObjectTypeId, double maxFoodInAnimal) {
        this.maxFoodInAnimals[liveObjectTypeId] = maxFoodInAnimal;
    }

    public int getEatingProbability(int liveObject1TypeId, int liveObject2TypeId) {
        return eatingProbabilities[liveObject1TypeId][liveObject2TypeId];
    }

    public void setEatingProbability(int liveObject1TypeId, int liveObject2TypeId, int eatingProbability) {
        this.eatingProbabilities[liveObject1TypeId][liveObject2TypeId] = eatingProbability;
    }

    public int getLiveObjectTypeId(String className) {
        int result = -1;
        for (int i = 0; i < liveObjectsTypes.length; i++) {
            if (className.equalsIgnoreCase(liveObjectsTypes[i])) {
                result = i;
            }
        }
        return result;
    }

    public String[] getObjectsTypesNames() {
        return objectsTypesNames;
    }

    public String getObjectTypeName(int liveObjectTypeId) {
        return objectsTypesNames[liveObjectTypeId];
    }

    public void setObjectTypeName(int liveObjectTypeId, String objectTypeName) {
        this.objectsTypesNames[liveObjectTypeId] = objectTypeName;
    }

//    public int getMaxNumberOfChild(int liveObjectTypeId) {
//        return maxNumbersOfChilds[liveObjectTypeId];
//    }
//
//    public void setMaxNumberOfChild(int liveObjectTypeId, int maxNumberOfChild) {
//        this.maxNumbersOfChilds[liveObjectTypeId] = maxNumberOfChild;
//    }

    public int getBreedingPeriod(int liveObjectTypeId) {
        return breedingPeriods[liveObjectTypeId];
    }

    public void setBreedingPeriod(int liveObjectTypeId, int breedingPeriod) {
        this.breedingPeriods[liveObjectTypeId] = breedingPeriod;
    }

//    public boolean getBreedingPairFlag(int liveObjectTypeId) {
//        return breedingPairFlagsArray[liveObjectTypeId];
//    }
//
//    public void setBreedingPairFlag(int liveObjectTypeId, boolean breedingPairFlag) {
//        this.breedingPairFlagsArray[liveObjectTypeId] = breedingPairFlag;
//    }

    public double getStarvingInStep(int liveObjectTypeId) {
        return starvingsInStep[liveObjectTypeId];
    }

    public void setStarvingInStep(int liveObjectTypeId, double starvingInStep) {
        this.starvingsInStep[liveObjectTypeId] = starvingInStep;
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setPsevdographics() {
        this.objectsTypesNames = this.objectsTypesPsevdographic;
    }

    public void saveConfig() {
        //переменные исходящего потока для сериализации
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(configPath); //запись потока бит в файл
            oos = new ObjectOutputStream(fos); //перевод объекта в поток бит
            //сохранение значений объекта конфигурации в поток
            oos.writeObject(this);
            oos.flush();
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл для сохранениня конфигурации не найден");
        } catch (IOException e) {
            System.out.println("Ошибка ввода\\вывода при сохранениии конфигурации в файл");
        }
    }

    public static void loadConfig() {
        if (Files.exists(Path.of(configPath))) {
            Config config = null;
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(configPath); //считываем данные из файла
                ois = new ObjectInputStream(fis); //перевод бит в объект
                //считывваем значения конфигурации из файла и создаем объект конфигурации
                config = (Config) ois.readObject();
                ois.close();
                fis.close();
            } catch (IOException e) {
                System.out.println("Ошибка ввода\\вывода при чтении конфигурации из файла");
            } catch (ClassNotFoundException e) {
                System.out.println("Класс конфигурации не найден");
            }
            CONFIG.areasCount = config.areasCount;
//            CONFIG.objectsMaxCountsOnIsland = config.objectsMaxCountsOnIsland;
            CONFIG.liveObjectsTypes = config.liveObjectsTypes;
            CONFIG.objectsTypesNames = config.objectsTypesNames;
            CONFIG.weights = config.weights;
            CONFIG.objectsMaxCountsOnArea = config.objectsMaxCountsOnArea;
            CONFIG.maxMoveSpeeds = config.maxMoveSpeeds;
            CONFIG.maxFoodInAnimals = config.maxFoodInAnimals;
            CONFIG.eatingProbabilities = config.eatingProbabilities;
//            CONFIG.maxNumbersOfChilds = config.maxNumbersOfChilds;
            CONFIG.breedingPeriods = config.breedingPeriods;
//            CONFIG.breedingPairFlagsArray = config.breedingPairFlagsArray;
            CONFIG.starvingsInStep = config.starvingsInStep;
        }
    }
}