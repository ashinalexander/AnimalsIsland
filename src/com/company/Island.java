package com.company;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import static com.company.Config.CONFIG;
import static com.company.Log.LOG;

public class Island implements Runnable {
    //public class Island implements Runnable {
    //Singleton - в программе предусмотрено существование только одного острова
    public static final Island ISLAND = new Island();
    // количество локаций на острове (в массиве [x,y])
    private final int[] areasCount;

    //общее количество локаций на островe
    private final int areasTotalCount;
    // типы объектов животных и растений
    private final String[] liveObjectsTypes;
    // наименования титипов объектов животных и растений (может использоваться псевдографика)
    private String[] objectsTypesNames;
    //массив для локаций
    ArrayList<Area> areas;
    //аналог хода игры (может изменяться в зависимости от условий выхода)
    private int step;
    //выполняющийся ход игры (используется для вывода текущего хода на екран)
    private int currentStep;
    //переменная используется в условии выхода из основного цикла имитации
    private int exitStep;
    //колличество шагов имитации (изменяется в конфигурации)
    final int finalStep;
    //индекс создаваемой зоны
    private int areaId;
    //текущее количество объектов на острове (не индекс)
    private long[] currentObjectsTotalCounts;
    //общее количество когда-либо созданных объектов по типам - для именования объектов (не индекс)
    private long[] countsForNames;
    //колличество видов объектов на острове
    private int liveObjectsCount;
    //пул потоков
    private ExecutorService aThreads;
    //строка для сбора статистики по острову
    private String islandStat;
    //строка для сбора статистики по локациям
    private String areasStat;
    //флаги остановки имитации stopIslandFlags[0] -остановка по ходу, stopIslandFlags[1] -остановка по вымиранию
    private final boolean[] stopIslandFlags;
    //колличество локаций, завершивших ход
    private int finishedAreas;
    //количество зверей на острове используется для проверки на вымирание
    private long animalPopulationCountOnIsland;

    public static Island getInstance() {
        return ISLAND;
    }

    private Island() {
        // типы объектов животных и растений
        this.liveObjectsTypes = CONFIG.getLiveObjectsTypes();
        //колличество видов объектов на острове
        this.liveObjectsCount = CONFIG.getliveObjectsCount();
        //текущее количество объектов на острове (не индекс)
        this.currentObjectsTotalCounts = new long[liveObjectsCount];
        //общее количество когда-либо созданных объектов по типам - для именования объектов (не индекс)
        this.countsForNames = new long[liveObjectsCount];
        //наименование типов животных и растений
        this.objectsTypesNames = CONFIG.getObjectsTypesNames();
        // количество локаций на острове в массиве [x,y]
        this.areasCount = CONFIG.getAreasCount();
        //общее количество локаций на островe
        this.areasTotalCount = areasCount[0] * areasCount[1];
        //массив для локаций
        this.areas = new ArrayList<>(this.areasTotalCount);
        //пул потоков
        this.aThreads = Executors.newFixedThreadPool(this.areasTotalCount);
        //аналог хода игры (может изменяться в зависимости от условий выхода)
        step = 0;
        //текущий ход игры для вывода на экран
        currentStep = 0;
        //переменная используется в условии выхода по количеству шагов
        this.exitStep = 0;
        //колличество шагов имитации (изменяется в конфигурации)
        finalStep = CONFIG.getFinalStep();
        //индекс локации (для доступа)
        this.areaId = -1;
        //Cтроки для статистики
        this.islandStat = new String();
        this.areasStat = new String();
        //флаги остановки имитации stopIslandFlags[0] -остановка по ходу, stopIslandFlags[1] -остановка по вымиранию
        this.stopIslandFlags = CONFIG.getStopOptions();

    }

    @Override
    public void run() {
        Thread log = new Thread(LOG);//создаем объект лога
        log.start();//        активируем лог

//      создаем локации и передаем в них их индексы
        for (int areaX = 0; areaX < areasCount[0]; areaX++) { //индекс расположения локации по оси X
            for (int areaY = 0; areaY < areasCount[1]; areaY++) { //индекс расположения локации по оси Y
                areaId++; //индекс локации (для доступа)
                areas.add(areaId, new Area(areaId, areaX, areaY)); //добавляем новую локацию в массив
            }
        }

        //собираем статистику по острову на основе статистик локаций (на этапе инициализации)
        for (int areaId = 0; areaId < areasTotalCount; areaId++)
            for (int objectTypeId = 0; objectTypeId < liveObjectsCount; objectTypeId++)
                currentObjectsTotalCounts[objectTypeId] += areas.get(areaId).getCurrentObjectsInAreaCounts(objectTypeId);

        //выводим ход имитации
        LOG.addToLog("НАЧАЛЬНАЯ ИНИЦИАЛИЗАЦИЯ");

        //собираем статистику по острову в строку и выводим на экран (на этапе инициализации)
        islandStat = "Island\t\t:";
        for (int objectTypeId = 0; objectTypeId < liveObjectsCount; objectTypeId++)
            islandStat = islandStat + objectsTypesNames[objectTypeId] + " " + currentObjectsTotalCounts[objectTypeId] + "\t\t";
        LOG.addToLog(islandStat);

        //собираем статистику по локациям в строку и выводим на экран
//        for (int areaId = 0; areaId < areasTotalCount; areaId++) {
//            areasStat = areas.get(areaId).getAreaName() + "\t:";
//            for (int objectTypeId = 0; objectTypeId < liveObjectsCount; objectTypeId++) {
//                areasStat = areasStat + objectsTypesNames[objectTypeId] + " " + areas.get(areaId).getCurrentObjectsInAreaCounts(objectTypeId) + "\t\t";
//            }
//            LOG.addToLog(areasStat);
//        }

        // НАЧАЛО ЦИКЛА
        //stopIslandFlags[0] - флаг остановки по количеству шагов
        //если установлен, то присваеваем переменной условия выхода значение из конфигурации
        if (stopIslandFlags[0]) exitStep = finalStep;
        else exitStep = step + 1;
        while (step < exitStep) {
            finishedAreas = 0; //каждый ход значение обнуляется и набирается заново из Areas
            for (areaId = 0; areaId < areasTotalCount; areaId++) {
                aThreads.submit(areas.get(areaId)); //распараллеливаем локации
            }

            //ждем когда отработают все локации
            //если работают, то спим
            while (finishedAreas < areasTotalCount) {
                try {
                    Thread.sleep(areasTotalCount/1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < liveObjectsCount; i++) {
                currentObjectsTotalCounts[i] = 0;
            }
            //собираем статистику по острову на основе статистик локаций (на этапе имитации)
            for (int areaId = 0; areaId < areasTotalCount; areaId++)
                for (int objectTypeId = 0; objectTypeId < liveObjectsCount; objectTypeId++)
                    currentObjectsTotalCounts[objectTypeId] += areas.get(areaId).getCurrentObjectsInAreaCounts(objectTypeId);

            //увеличиваем счетчик ходов
            currentStep++;
            //если флаг выхода по шагам стоит, то увеличиваем технический счетчик ходов
            if (stopIslandFlags[0]) step++;
            //если флаг выхода по вымиранию стоит, то высчитываем значение и меняем условие выхода, на завершенное
            if (stopIslandFlags[1]) {
                for (int objectTypeId = 1; objectTypeId < liveObjectsCount; objectTypeId++)
                    animalPopulationCountOnIsland += currentObjectsTotalCounts[objectTypeId];
                if (animalPopulationCountOnIsland == 0) step = exitStep + 1;
            }

            //выводим ход имитации
//            LOG.addToLog("ХОД " + currentStep);

            //собираем статистику по острову в строку и выводим на экран (в конце каждого шага)
            islandStat = "ХОД " + currentStep + "\t\t:";
            for (int objectTypeId = 0; objectTypeId < liveObjectsCount; objectTypeId++)
                islandStat = islandStat + objectsTypesNames[objectTypeId] + " " + currentObjectsTotalCounts[objectTypeId] + "\t\t";
            LOG.addToLog(islandStat);

//            собираем статистику по локациям в строку и выводим на экран (в конце каждого шага)
//            for (int areaId = 0; areaId < areasTotalCount; areaId++) {
//                areasStat = areas.get(areaId).getAreaName() + "\t:";
//                for (int objectTypeId = 0; objectTypeId < liveObjectsCount; objectTypeId++) {
//                    areasStat = areasStat + objectsTypesNames[objectTypeId] + " " + areas.get(areaId).getCurrentObjectsInAreaCounts(objectTypeId) + "\t\t\t";
//                }
//                LOG.addToLog(areasStat);
//            }

        }
        //завершаем работу фабрик объектов в локациях
        for (Area area:
             areas) {
            area.shutdown();
        }
        //завершаем работу фабрики потоков локаций
        aThreads.shutdownNow();
        //завершаем работу лога
        LOG.shutdown();
    }

    //инкремент текущего количества локаций, завершивших работу
    public synchronized void appendFinishedAreas() {
        this.finishedAreas++;
    }

    //инкремент текущего количества определенных объектов на острове по id типа объекта
    //при создании нового объекта
    public synchronized void appendCurrentObjectsTotalCounts(int liveObjectTypeId) {
        this.currentObjectsTotalCounts[liveObjectTypeId]++;
    }

    //декремент текущего количества определенных объектов на острове по id типа объекта
    //используется при удалении объекта
    public synchronized void subtractCurrentObjectsTotalCounts(int liveObjectTypeId) {
        this.currentObjectsTotalCounts[liveObjectTypeId]--;
    }

    //получение общего числа созданных объектов определенного типа на острове по id
    //используется для именования создаваемых объектов
    public synchronized long getCountForName(int liveObjectTypeId) {
        return countsForNames[liveObjectTypeId];
    }

    //инкремент общего числа созданных объектов определенного типа на острове по id
    //используется для именования создаваемых объектов
    public synchronized void appendCountForName(int liveObjectTypeId) {
        this.countsForNames[liveObjectTypeId]++;
    }

    //предоставленеие количества локаций в Area для вычисления количества создаваемых потоков объектов
    public int getAreasTotalCount() {
        return areasTotalCount;
    }
}
