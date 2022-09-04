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
    ArrayList<Integer> directionArray; //массив с id локаций, доступных для перемещения
    //идентификатор будущей локации (для перемещения)
    protected int futureAreaId;
    protected int currentAreaId; //id текущей локации

    protected double maxFoodInAnimal; //максимальное количество еды в животном
    protected double starvingInStep; //количество еды, расходуемой животным за ход

    protected final int[] eatingProbabilitiy; //проценты съедания
    // массив с обектами пригодными для питания
    protected ArrayList<Integer> foodArray = new ArrayList<>(); //список ID объектов, которые можно съесть
    protected ArrayList<String> avaibleActions = new ArrayList<>(); //список с доступными действиями для объекта
    protected double currentFoodInAnimal; //количество пиши в животном
    protected double starvingsInStep; //коэффициент пищи, расходуемой за ход

    protected ArrayList<Integer> breedingArray = new ArrayList<>();
    ; //список ID объектов - пар для размножения
    protected int breedingPeriod; //периодичность размножения
    protected int currentBreedingPeriod; //количество ходов с момента размножения
    protected int breedingPair; //id пары для размножения
    protected int currentObjectId; //id объекта в текущей локации

    public Animal(int areaId, int currentliveObjectId, boolean initFlag) {
        this.directionArray = new ArrayList<>();
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
            this.currentBreedingPeriod = ThreadLocalRandom.current().
                    nextInt(0, CONFIG.getBreedingPeriod(this.liveObjectTypeId) + 1);
        } else {
            this.currentBreedingPeriod = 0;
        }
        //флаг того, что объект был съеден
        this.wasEaten = false;
        //флаг того, что объект должен быть удален
        this.deleteFlag = false;
        this.initFlag = initFlag;
        this.avaibleGoingsOnStep = CONFIG.getMaxMoveSpeed(liveObjectTypeId);
        this.maxFoodInAnimal = CONFIG.getMaxFoodInAnimal(liveObjectTypeId); //максимально потребляемое количество пищи
        this.starvingInStep = CONFIG.getStarvingInStep(liveObjectTypeId); //коэффициент пищи, расходуемой за ход

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
        this.avaibleActions = new ArrayList<>(); //список с доступными действиями для объекта
        this.foodArray = new ArrayList<>(); //список ID объектов, которые можно съесть
        this.breedingArray = new ArrayList<>(); //список ID объектов - пар для размножения
    }

    @Override
    public void run() {
        if (deleteFlag) return;
        //перед очередным ходом увеличиваем количество ходов с момента размножения
        //первым будет значение хода = 1
        this.currentBreedingPeriod++;
        //проверки на действия
        checkAvaibleAction();
        if (!avaibleActions.isEmpty())
            if (avaibleActions.get(0).equals("toGo")) toGo();
    }

    private void checkAvaibleAction() {
//        //    флаг возможности возможности размножения
//        boolean breedingFlag = false;
//        //проверяем, что наступил период размножения
//        if (this.currentBreedingPeriod >= this.breedingPeriod) breedingFlag = true;
//        //проходимся по всем объектам в своей локации
//        for (int i = 0; i <= ISLAND.getArea(currentAreaId).getLiveObjectId(); i++) {
//            //проверяем, что объект с которым собираемся провести действие не мертв
//            if ((ISLAND.getArea(currentAreaId).getObject(i).isDeleteFlag() == false)) {
//                //если объект можно съесть
//                if (eatingProbabilitiy[ISLAND.getArea(currentAreaId).getObject(i).getLiveObjectTypeId()] > 0) {
//                    this.avaibleActions.add("eat");
//                    this.foodArray.add(i);
//                }
//                //проверяем доступность размножения
//                if (breedingFlag)                   //наступил период размножения
//                    if (i != this.currentObjectId)  //проверяющий и проверяемый объекты различны
//                        // проверяемый объект того же типа, что проверяющий
//                        if (ISLAND.getArea(currentAreaId).getObject(i).getLiveObjectTypeId() == this.liveObjectTypeId)                                    //у проверяемого объекта наступил период размножения
//                            if (ISLAND.getArea(currentAreaId).getObject(i).getCurrentBreedingPeriod() >= breedingPeriod) {
//                                breedingFlag = true;
//                                this.avaibleActions.add("breed");
//                                this.breedingArray.add(i);
//                            }
//            }
//        }
        //проверяем на доступность перемещений
        //получаем список всех доступных локаций в пределах количества шагов объекта
        this.directionArray.add(this.currentAreaId); // в массив добавляем текущую локацию
        // действия производим количество раз равное максимально доступному количеству перемещений объекта за 1 ход
        for (int currentGoing = 0; currentGoing < this.avaibleGoingsOnStep; currentGoing++) {
            this.directionArray.add(currentAreaId); // в массив добавляем текущую локацию
            for (int i = 0; i < this.directionArray.size(); i++) { //проходим по массиву
                int areaIdDirectionArray = -1;                            // проходим по всем локациям
                for (int currentAreaY = 0; currentAreaY < this.areasCount[1]; currentAreaY++)
                    for (int currentAreaX = 0; currentAreaX < this.areasCount[0]; currentAreaX++) {
                        areaIdDirectionArray++; // высчитываем id локации
                        if (areaIdDirectionArray == this.directionArray.get(i)) { //если проверяемая локация - это локация в массиве
                            int nearlyAreaID = -1; //проходим по всем локациям
                            boolean inDirectionArray = false;
                            for (int y = 0; y < this.areasCount[1]; y++)
                                for (int x = 0; x < this.areasCount[0]; x++) {
                                    nearlyAreaID++; //высчитываем id локации
                                    //если локация находится слева
                                    if ((x == currentAreaX - 1) && (y == currentAreaY)) {
                                        //проходим по массиву
                                        for (int j = 0; j < directionArray.size(); j++) {
                                            if (directionArray.get(j) == nearlyAreaID) {   //если проверяемая локация есть
                                                inDirectionArray = true;               //устанавливаем флаг
                                            }
                                        }
                                        //если локации нет в массиве то добавляем ее
                                        if (!inDirectionArray) directionArray.add(nearlyAreaID);
                                    }
                                }
                        }
                    }
            }
        }
        directionArray.remove(0); //удаляем локацию в которой находимся
        //удаляем локации в которых достигнут лимит популяции
        for (int i = directionArray.size() - 1; i >= 0; i--) {
            if (ISLAND.getArea(directionArray.get(i)).getCurrentObjectsInAreaCounts(liveObjectTypeId) >=
                    ISLAND.getArea(directionArray.get(i)).getObjectsMaxCountsInArea(liveObjectTypeId)) {
                directionArray.remove(i);
            }
        }
        if (!directionArray.isEmpty()) {
            this.avaibleActions.add("toGo");
        }
    }

    // "Двигаться"
    private void toGo() {
        //случайным образом выбираем локацию для перемещения из доступных
        int i = ThreadLocalRandom.current().
                nextInt(0, directionArray.size());
        this.futureAreaId = directionArray.get(i);
        //выводим сообщение о перемещении
        System.out.println(this.objectName + " : Изменил локацию c " +
                ISLAND.getArea(currentAreaId).getAreaName() + " на " +
                ISLAND.getArea(futureAreaId).getAreaName());
        //убавляем количество еды внутри животного
        this.currentFoodInAnimal -= starvingInStep;
        //чистим за собой массив
        this.directionArray.clear();
        this.avaibleActions.clear();
//        this.deleteFlag= true;
        ISLAND.getArea(futureAreaId).moveObject(this.currentObjectId, this.currentAreaId, this.futureAreaId);
    }

    // "Размножаться"
    public void toBreed() {

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
