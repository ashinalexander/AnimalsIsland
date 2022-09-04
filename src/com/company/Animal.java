package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.company.Config.*;
import static com.company.Config.CONFIG;
import static com.company.Island.ISLAND;

//абстрактный класс для всех видов животных
public abstract class Animal extends LiveObject {
    protected int avaibleGoingsOnStep; //количество перемещений за ход;
    protected int maxGoing; // количество шагов на ходу имитации
    protected int moveDirection; //направление движения на ходу игры
    ArrayList<String> directionArray;
    //идентификатор будущей локации (для перемещения)
    protected int futureAreaId;
    protected int realPastAreaId; // id реальной локации из которой будет перемещен объект
    //координаты будущей локации (для перемещения)
    protected boolean moveFlag; //флаг того, что объект должен быть перемещен
    protected int currentAreaId; //id текущей локации

    protected double maxFoodInAnimal; //максимальное количество еды в животном
    protected double starvingInStep; //количество еды, расходуемой животным за ход
    protected double foodInAnimal; //количество еды в животном
    protected final int[] eatingProbabilitiy; //проценты съедания

    //флаг возможности передвижения
    protected boolean goingFlag;
    //флаг доступности функции "съесть"
    protected boolean eatingFlag;
    //    флаг возможности возможности размножения
    protected boolean breedingFlag;
    // массив с обектами пригодными для питания
    protected ArrayList<Integer> foodArray; //список ID объектов, которые можно съесть
    protected ArrayList<String> avaibleActions; //список с доступными действиями для объекта
    protected double currentFoodInAnimal; //количество пиши в животном
    protected double starvingsInStep; //коэффициент пищи, расходуемой за ход

    protected ArrayList<Integer> breedingArray; //список ID объектов - пар для размножения
    protected int breedingPeriod; //периодичность размножения
    protected int currentBreedingPeriod; //количество ходов с момента размножения
    protected int breedingPair; //id пары для размножения
    protected int currentObjectId; //id объекта в текущей локации

    public Animal(int areaId, int currentliveObjectId, boolean initFlag) {
//        super(areaId, currentliveObjectId, initFlag);
        //идентификатор типа объекта (животного или растения)
        this.liveObjectTypeId = CONFIG.getLiveObjectTypeId(this.getClass().getSimpleName());
        //наименование типа объекта (животного или растения)
        this.objectTypeName = CONFIG.getObjectTypeName(this.liveObjectTypeId);
        //наименование объекта
        this.objectName = new String(objectTypeName + "[" + ISLAND.getCountForName(this.liveObjectTypeId) + "]");
        //вес объекта
        this.weight = CONFIG.getWeight(this.liveObjectTypeId);
        //максимум объектов данного типа в локации
        this.maxThisObjectsInArea = CONFIG.getObjectMaxCountOnArea(this.liveObjectTypeId);
        //период размножения
        this.breedingPeriod = CONFIG.getBreedingPeriod(this.liveObjectTypeId);
        // количество локаций на острове в массиве [x,y]
        this.areasCount = CONFIG.getAreasCount();
        //id в массиве текущей локации (для доступа к объекту)
        this.currentLiveObjectId = currentliveObjectId;
        //идентификатор локации в которой находится объект
        this.currentAreaId = areaId;
        //флаг того, объект создается при инициализации или нет
        this.initFlag = initFlag;
        //если объект создан при инициализации, то у него период размножения будет задана случайно
        Random random = new Random();
        if (initFlag == true) {
            this.currentBreedingPeriod = random.nextInt(0, CONFIG.getBreedingPeriod(this.liveObjectTypeId) + 1);
//                    ThreadLocalRandom.current().
//                    nextInt(0, CONFIG.getBreedingPeriod(this.liveObjectTypeId) + 1);
        } else {
            this.currentBreedingPeriod = 0;
        }
        //флаг того, что объект был съеден
        this.wasEaten = false;
        //флаг того, что объект должен быть удален
        this.moveFlag = false;
        this.deleteFlag = false;
        this.initFlag = initFlag;
        this.avaibleGoingsOnStep = CONFIG.getMaxMoveSpeed(liveObjectTypeId);
        this.maxFoodInAnimal = CONFIG.getMaxFoodInAnimal(liveObjectTypeId); //максимально потребляемое количество пищи
        this.starvingInStep = CONFIG.getStarvingInStep(liveObjectTypeId); //коэффициент пищи, расходуемой за ход
        this.futureAreaId = -1;

        if (initFlag == true) {
            this.currentBreedingPeriod = ThreadLocalRandom.current().
                    nextInt(0, this.breedingPeriod + 1);
            this.currentFoodInAnimal = ThreadLocalRandom.current().
                    nextDouble(this.starvingsInStep * 3, this.maxFoodInAnimal);
        } else {
            this.currentBreedingPeriod = 0;
            this.currentFoodInAnimal = this.maxFoodInAnimal / 2;
        }
        //проценты съедания других объектов
        this.eatingProbabilitiy = CONFIG.getEatingProbability(liveObjectTypeId);

        //координаты локации
        int tmpAreaID = -1;
        for (int y = 0; y < areasCount[0]; y++) {
            for (int x = 0; x < areasCount[1]; x++) {
                tmpAreaID++;
                if (tmpAreaID == this.currentAreaId) {
                    this.currentAreaX = x;
                    this.currentAreaY = y;
                }
            }
        }
        this.avaibleActions = new ArrayList<>(); //список с доступными действиями для объекта
        this.foodArray = new ArrayList<>(); //список ID объектов, которые можно съесть
        this.breedingArray = new ArrayList<>(); //список ID объектов - пар для размножения
    }


    @Override
    public void run() {
        if (deleteFlag) return;
        this.eatingFlag = false;
        this.breedingFlag = false;
        this.goingFlag = false;
        //перед очередным ходом увеличиваем количество ходов с момента размножения
        //первым будет значение хода = 1
        this.currentBreedingPeriod++;
        //проверки на действия
        checkAvaibleAction();

        if ((currentBreedingPeriod < breedingPeriod) || (ISLAND.getArea(currentAreaId).
                getCurrentObjectsInAreaCounts(liveObjectTypeId) < 2)) breedingFlag = false;

        //отчитываемся о выполнении действия
//        ISLAND.getArea(currentAreaId).appendFinishedObjects();
    }

    private void checkAvaibleAction() {
        //проверяем, что наступил период размножения
        if (this.currentBreedingPeriod >= this.breedingPeriod) this.breedingFlag = true;
        //проходимся по всем объектам в своей локации
        for (int i = 0; i <= ISLAND.getArea(currentAreaId).getLiveObjectId(); i++)
            //проверяем, что объект доступен - не переместился и не умер
            if ((ISLAND.getArea(currentAreaId).getObject(i).isDeleteFlag() == false)
                    && (ISLAND.getArea(currentAreaId).getObject(i).isMoveFlag() == false)) {
                //если объект можно съесть
                if (eatingProbabilitiy[ISLAND.getArea(currentAreaId).getObject(i).getLiveObjectTypeId()] > 0) {
                    this.eatingFlag = true;
                    this.avaibleActions.add("eat");
                    this.foodArray.add(i);
                }
                //проверяем доступность размножения
                if (breedingFlag)                   //наступил период размножения
                    if (i != this.currentObjectId)  //проверяющий и проверяемый объекты различны
                        // проверяемый объект того же типа, что проверяющий
                        if (ISLAND.getArea(currentAreaId).getObject(i).getLiveObjectTypeId() == this.liveObjectTypeId)                                    //у проверяемого объекта наступил период размножения
                            if (ISLAND.getArea(currentAreaId).getObject(i).getCurrentBreedingPeriod() >= breedingPeriod) {
                                this.breedingFlag = true;
                                this.avaibleActions.add("breed");
                                this.breedingArray.add(i);
                            }
            }
        //проверяем на доступность перемещений
        if ((areasCount[0] != 0) || (areasCount[1] != 0)) {
            this.goingFlag = true;
            this.avaibleActions.add("going");
        }
    }

    // "Двигаться"
    private void going() {
        moveFlag = true;
        this.realPastAreaId = this.currentAreaId; //сохранили id для передачи методу перемещения в ISLAND
        this.directionArray = new ArrayList<>(); //список доступных направлений
        //выбираем сколько ходов из возможных сделает объект
        this.maxGoing = ThreadLocalRandom.current().nextInt(1, avaibleGoingsOnStep + 1);
        for (int currentGoing = 0; currentGoing < this.maxGoing; currentGoing++) {
            if (this.currentAreaX > 0) directionArray.add("left");
            if (this.currentAreaX < areasCount[0] - 1) directionArray.add("rigth");
            if (this.currentAreaY > 0) directionArray.add("up");
            if (this.currentAreaY < areasCount[1] - 1) directionArray.add("down");
            //выбираем направление движения из доступных
            moveDirection = ThreadLocalRandom.current().nextInt(0, directionArray.size());
            int tmpAreaID = 0;
            switch (directionArray.get(moveDirection)) {
                case "left" -> {
                    for (int y = 0; y < this.areasCount[1]; y++)
                        for (int x = 0; x < this.areasCount[0]; x++) {
                            tmpAreaID++;
                            if ((x == this.currentAreaX - 1) && (y == this.currentAreaY))
                                this.futureAreaId = tmpAreaID;
                        }
                }
                case "rigth" -> {
                    for (int y = 0; y < this.areasCount[1]; y++)
                        for (int x = 0; x < this.areasCount[0]; x++) {
                            tmpAreaID++;
                            if ((x == this.currentAreaX + 1) && (y == this.currentAreaY))
                                this.futureAreaId = tmpAreaID;
                        }
                }
                case "up" -> {
                    for (int y = 0; y < this.areasCount[1]; y++)
                        for (int x = 0; x < this.areasCount[0]; x++) {
                            tmpAreaID++;
                            if ((x == this.currentAreaX) && (y == this.currentAreaY - 1))
                                this.futureAreaId = tmpAreaID;
                        }
                }
                case "down" -> {
                    for (int y = 0; y < this.areasCount[1]; y++)
                        for (int x = 0; x < this.areasCount[0]; x++) {
                            tmpAreaID++;
                            if ((x == this.currentAreaX) && (y == this.currentAreaY + 1))
                                this.futureAreaId = tmpAreaID;
                        }
                }
            }
            System.out.println(this.objectName + " : Изменил локацию c " +
                    ISLAND.getArea(currentAreaId).getAreaName() + " на " +
                    ISLAND.getArea(futureAreaId).getAreaName());
            this.currentAreaId = futureAreaId;
        }
        // убавляем количество еды внутри животного
        this.currentFoodInAnimal -= starvingInStep;
        this.directionArray.clear();
        deleteFlag = true;
        ISLAND.getArea(futureAreaId).moveObject(currentObjectId, realPastAreaId, futureAreaId);
    }

    public void toBreed() {
        // "Размножаться"
    }

    public void selectDirectionToMove() {
        // "Выбрать направление для движения"
    }

    public void toStarvingDie() {
        // "Умереть от голода"
    }

    public void toEaten() {
        System.out.println(objectName + "-> съеден");
        // "Быть съеденным"
    }
}
