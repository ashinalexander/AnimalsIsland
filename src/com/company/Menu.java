package com.company;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Menu {
    Scanner console = new Scanner(System.in);
    String thisChoise;
    String[] splitChoise;

    Menu() {
        mainMenu("main");
    }

    public void mainMenu(String prevChoise) {
        thisChoise = "main";
        System.out.print("""
                Главное меню
                start - начать имитацию
                conf - меню конфигурации
                prev - предыдущее меню
                """);
        while (thisChoise.equals("main")) {
            System.out.print(thisChoise + " >");
            splitChoise = console.nextLine().split(" ");
            if (splitChoise.length == 1) {
                switch (splitChoise[0]) {
                    case "conf" -> choiceMenu("conf", thisChoise);
                    case "prev" -> choiceMenu(prevChoise, thisChoise);
                    case "start" -> thisChoise = "start";
                    default -> System.out.println("Команда не допустима");
                }
            }
        }
    }

    public void confMenu(String prevChoise) {
        thisChoise = "conf";
        System.out.print("""
                Настройки
                show - показать все переменные
                <variable> - управление переменной
                prev - переход в предыдущее меню
                main - переход в главное меню
                save - сохранение текущих настроек в файл
                """);
        while (thisChoise.equals("conf")) {
            System.out.print(thisChoise + " >");
            splitChoise = console.nextLine().split(" ");
            if (splitChoise.length == 1) {
                choiceMenu(splitChoise[0], thisChoise);
                switch (splitChoise[0]) {
                    case "show" -> show(thisChoise);
                    case "prev" -> choiceMenu(prevChoise, thisChoise);
                    case "save" -> Config.CONFIG.saveConfig(); //сохранение измненного дефолта в файл
                    default -> System.out.println("Команда не допустима");
                }
            }
        }
    }

    public void areasCountMenu(String prevChoise) {
        thisChoise = "areasCount";
        System.out.print(thisChoise + "\n" +
                "show- показать значение \"" + thisChoise + "\"\n" +
                "set <value> <value> - изменить значение (X*Y) \"" + thisChoise + "\"\n" +
                "prev - предыдущее меню\n" +
                "conf - меню конфигурации\n" +
                "main - главное меню\n");
        while (thisChoise.equals("areasCount")) {
            System.out.print(thisChoise + " >");
            splitChoise = console.nextLine().split(" ");
            switch (splitChoise.length) {
                case 1 -> {
                    choiceMenu(splitChoise[0], thisChoise);
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise);
                        case "prev" -> choiceMenu(prevChoise, thisChoise); // возврат в предыдущее меню
                        default -> System.out.println("Команда не допустима");
                    }
                }
                case 3 -> {
                    switch (splitChoise[0]) {
                        case "set" -> set(thisChoise, splitChoise[1],splitChoise[2]);
                    }
                }
            }
        }
    }

    public void objectsMaxCountsOnIslandMenu(String prevChoise) {
        thisChoise = "objectsMaxCountsOnIsland";
        System.out.print(thisChoise + "\n" +
                "show - показать все переменные \"" + thisChoise + "\"\n" +
                "show all - показать значение всех переменных \"" + thisChoise + "\"\n" +
                "show <value> - показать значение переменной <value> \"" + thisChoise + "\"\n" +
                "set <value> - изменить значение \"" + thisChoise + "\"\n" +
                "prev - предыдущее меню\n" +
                "conf - меню конфигурации\n" +
                "main - главное меню\n");
        while (thisChoise.equals("objectsMaxCountsOnIsland")) {
            System.out.print(thisChoise + " >");
            splitChoise = console.nextLine().split(" ");
            switch (splitChoise.length) {
                case 1 -> {
                    choiceMenu(splitChoise[0], thisChoise);
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise);
                        case "prev" -> choiceMenu(prevChoise, thisChoise); // возврат в предыдущее меню
                    }
                }
                case 2 -> {
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise, splitChoise[1]);
                    }
                }
                case 3 -> {
                    switch (splitChoise[0]) {
                        case "set" -> set(thisChoise, splitChoise[1], splitChoise[2]);
                    }
                }
                default -> System.out.println("Команда не допустима");
            }
        }
    }

    public void objectsTypesNamesMenu(String prevChoise) {
        thisChoise = "objectsTypesNames";
        System.out.print(thisChoise + "\n" +
                "show - показать все переменные \"" + thisChoise + "\"\n" +
                "show all - показать значение всех переменных \"" + thisChoise + "\"\n" +
                "show <value> - показать значение переменной <value> \"" + thisChoise + "\"\n" +
                "set psevdo - установить псевдографику \"" + thisChoise + "\"\n" +
                "set <value> - изменить значение \"" + thisChoise + "\"\n" +
                "prev - предыдущее меню\n" +
                "conf - меню конфигурации\n" +
                "main - главное меню\n");
        while (thisChoise.equals("objectsTypesNames")) {
            System.out.print(thisChoise + " >");
            splitChoise = console.nextLine().split(" ");
            switch (splitChoise.length) {
                case 1 -> {
                    choiceMenu(splitChoise[0], thisChoise);
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise);
                        case "prev" -> choiceMenu(prevChoise, thisChoise); // возврат в предыдущее меню
                    }
                }
                case 2 -> {
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise, splitChoise[1]);
                        case "prev" -> choiceMenu(prevChoise, thisChoise); // возврат в предыдущее меню
                        case "set" -> set(thisChoise, splitChoise[1]);
                    }
                }
                case 3 -> {
                    switch (splitChoise[0]) {
                        case "set" -> set(thisChoise, splitChoise[1], splitChoise[2]);
                    }
                }
                default -> System.out.println("Команда не допустима");
            }
        }
    }

    public void weightsMenu(String prevChoise) {
        thisChoise = "weights";
        System.out.print(thisChoise + "\n" +
                "show - показать все переменные \"" + thisChoise + "\"\n" +
                "show all - показать значение всех переменных \"" + thisChoise + "\"\n" +
                "show <value> - показать значение переменной <value> \"" + thisChoise + "\"\n" +
                "set <value> - изменить значение \"" + thisChoise + "\"\n" +
                "prev - предыдущее меню\n" +
                "conf - меню конфигурации\n" +
                "main - главное меню\n");
        while (thisChoise.equals("weights")) {
            System.out.print(thisChoise + " >");
            splitChoise = console.nextLine().split(" ");
            switch (splitChoise.length) {
                case 1 -> {
                    choiceMenu(splitChoise[0], thisChoise);
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise);
                        case "prev" -> choiceMenu(prevChoise, thisChoise); // возврат в предыдущее меню
                    }
                }
                case 2 -> {
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise, splitChoise[1]);
                    }
                }
                case 3 -> {
                    switch (splitChoise[0]) {
                        case "set" -> set(thisChoise, splitChoise[1], splitChoise[2]);
                    }
                }
                default -> System.out.println("Команда не допустима");
            }
        }
    }

    public void objectsMaxCountsOnAreaMenu(String prevChoise) {
        thisChoise = "objectsMaxCountsOnArea";
        System.out.print(thisChoise + "\n" +
                "show - показать все переменные \"" + thisChoise + "\"\n" +
                "show all - показать значение всех переменных \"" + thisChoise + "\"\n" +
                "show <value> - показать значение переменной <value> \"" + thisChoise + "\"\n" +
                "set <value> - изменить значение \"" + thisChoise + "\"\n" +
                "prev - предыдущее меню\n" +
                "conf - меню конфигурации\n" +
                "main - главное меню\n");
        while (thisChoise.equals("objectsMaxCountsOnArea")) {
            System.out.print(thisChoise + " >");
            splitChoise = console.nextLine().split(" ");
            switch (splitChoise.length) {
                case 1 -> {
                    choiceMenu(splitChoise[0], thisChoise);
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise);
                        case "prev" -> choiceMenu(prevChoise, thisChoise); // возврат в предыдущее меню
                    }
                }
                case 2 -> {
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise, splitChoise[1]);
                    }
                }
                case 3 -> {
                    switch (splitChoise[0]) {
                        case "set" -> set(thisChoise, splitChoise[1], splitChoise[2]);
                    }
                }
                default -> System.out.println("Команда не допустима");
            }
        }
    }

    public void maxMoveSpeedsMenu(String prevChoise) {
        thisChoise = "maxMoveSpeeds";
        System.out.print(thisChoise + "\n" +
                "show - показать все переменные \"" + thisChoise + "\"\n" +
                "show all - показать значение всех переменных \"" + thisChoise + "\"\n" +
                "show <value> - показать значение переменной <value> \"" + thisChoise + "\"\n" +
                "set <value> - изменить значение \"" + thisChoise + "\"\n" +
                "prev - предыдущее меню\n" +
                "conf - меню конфигурации\n" +
                "main - главное меню\n");
        while (thisChoise.equals("maxMoveSpeeds")) {
            System.out.print(thisChoise + " >");
            splitChoise = console.nextLine().split(" ");
            switch (splitChoise.length) {
                case 1 -> {
                    choiceMenu(splitChoise[0], thisChoise);
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise);
                        case "prev" -> choiceMenu(prevChoise, thisChoise); // возврат в предыдущее меню
                    }
                }
                case 2 -> {
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise, splitChoise[1]);
                    }
                }
                case 3 -> {
                    switch (splitChoise[0]) {
                        case "set" -> set(thisChoise, splitChoise[1], splitChoise[2]);
                    }
                }
                default -> System.out.println("Команда не допустима");
            }
        }
    }

    public void maxFoodInAnimalsMenu(String prevChoise) {
        thisChoise = "maxFoodInAnimals";
        System.out.print(thisChoise + "\n" +
                "show - показать все переменные \"" + thisChoise + "\"\n" +
                "show all - показать значение всех переменных \"" + thisChoise + "\"\n" +
                "show <value> - показать значение переменной <value> \"" + thisChoise + "\"\n" +
                "set <value> - изменить значение \"" + thisChoise + "\"\n" +
                "prev - предыдущее меню\n" +
                "conf - меню конфигурации\n" +
                "main - главное меню\n");
        while (thisChoise.equals("maxFoodInAnimals")) {
            System.out.print(thisChoise + " >");
            splitChoise = console.nextLine().split(" ");
            switch (splitChoise.length) {
                case 1 -> {
                    choiceMenu(splitChoise[0], thisChoise);
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise);
                        case "prev" -> choiceMenu(prevChoise, thisChoise); // возврат в предыдущее меню
                    }
                }
                case 2 -> {
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise, splitChoise[1]);
                    }
                }
                case 3 -> {
                    switch (splitChoise[0]) {
                        case "set" -> set(thisChoise, splitChoise[1], splitChoise[2]);
                    }
                }
                default -> System.out.println("Команда не допустима");
            }
        }
    }

    public void eatingProbabilitiesMenu(String prevChoise) {
        thisChoise = "eatingProbabilities";
        System.out.print(thisChoise + "\n" +
                "show - показать все переменные \"" + thisChoise + "\"\n" +
                "show all- показать все вероятности поедания\n" +
                "show <variable> each- показать для <variable> вероятности поедания объектов\n" +
                "show each <variable> - показать для <variable> вероятности быть съеденным объектами\n" +
                "set <variable1> <variable1> <value> - изменить вероятность поедания объектом <variable1> объекта <variable1>\n" +
                "prev - предыдущее меню\n" +
                "conf - меню конфигурации\n" +
                "main - главное меню\n");
        while (thisChoise.equals("eatingProbabilities")) {
            System.out.print(thisChoise + " >");
            splitChoise = console.nextLine().split(" ");
            switch (splitChoise.length) {
                case 1 -> {
                    choiceMenu(splitChoise[0], thisChoise);
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise);
                        case "prev" -> choiceMenu(prevChoise, thisChoise); // возврат в предыдущее меню
                    }
                }
                case 2 -> {
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise, splitChoise[1]);
                    }
                }
                case 3 -> {
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise, splitChoise[1], splitChoise[2]);
                    }
                }
                case 4 -> {
                    switch (splitChoise[0]) {
                        case "set" -> set(thisChoise, splitChoise[1], splitChoise[2], splitChoise[3]);
                    }
                }
                default -> System.out.println("Команда не допустима");
            }
        }
    }

    public void maxNumbersOfChildsMenu(String prevChoise) {
        thisChoise = "maxNumbersOfChilds";
        System.out.print(thisChoise + "\n" +
                "show - показать все переменные \"" + thisChoise + "\"\n" +
                "show all - показать значение всех переменных \"" + thisChoise + "\"\n" +
                "show <value> - показать значение переменной <value> \"" + thisChoise + "\"\n" +
                "set <value> - изменить значение \"" + thisChoise + "\"\n" +
                "prev - предыдущее меню\n" +
                "conf - меню конфигурации\n" +
                "main - главное меню\n");
        while (thisChoise.equals("maxNumbersOfChilds")) {
            System.out.print(thisChoise + " >");
            splitChoise = console.nextLine().split(" ");
            switch (splitChoise.length) {
                case 1 -> {
                    choiceMenu(splitChoise[0], thisChoise);
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise);
                        case "prev" -> choiceMenu(prevChoise, thisChoise); // возврат в предыдущее меню
                    }
                }
                case 2 -> {
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise, splitChoise[1]);
                    }
                }
                case 3 -> {
                    switch (splitChoise[0]) {
                        case "set" -> set(thisChoise, splitChoise[1], splitChoise[2]);
                    }
                }
                default -> System.out.println("Команда не допустима");
            }
        }
    }

    public void breedingPeriodsMenu(String prevChoise) {
        thisChoise = "breedingPeriods";
        System.out.print(thisChoise + "\n" +
                "show - показать все переменные \"" + thisChoise + "\"\n" +
                "show all - показать значение всех переменных \"" + thisChoise + "\"\n" +
                "show <value> - показать значение переменной <value> \"" + thisChoise + "\"\n" +
                "set <value> - изменить значение \"" + thisChoise + "\"\n" +
                "prev - предыдущее меню\n" +
                "conf - меню конфигурации\n" +
                "main - главное меню\n");
        while (thisChoise.equals("breedingPeriods")) {
            System.out.print(thisChoise + " >");
            splitChoise = console.nextLine().split(" ");
            switch (splitChoise.length) {
                case 1 -> {
                    choiceMenu(splitChoise[0], thisChoise);
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise);
                        case "prev" -> choiceMenu(prevChoise, thisChoise); // возврат в предыдущее меню
                    }
                }
                case 2 -> {
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise, splitChoise[1]);
                    }
                }
                case 3 -> {
                    switch (splitChoise[0]) {
                        case "set" -> set(thisChoise, splitChoise[1], splitChoise[2]);
                    }
                }
                default -> System.out.println("Команда не допустима");
            }
        }
    }

    public void breedingPairFlagsMenu(String prevChoise) {
        thisChoise = "breedingPairFlags";
        System.out.print(thisChoise + "\n" +
                "show - показать все переменные \"" + thisChoise + "\"\n" +
                "show all - показать значение всех переменных \"" + thisChoise + "\"\n" +
                "show <value> - показать значение переменной <value> \"" + thisChoise + "\"\n" +
                "set <value> - изменить значение \"" + thisChoise + "\"\n" +
                "prev - предыдущее меню\n" +
                "conf - меню конфигурации\n" +
                "main - главное меню\n");
        while (thisChoise.equals("breedingPairFlags")) {
            System.out.print(thisChoise + " >");
            splitChoise = console.nextLine().split(" ");
            switch (splitChoise.length) {
                case 1 -> {
                    choiceMenu(splitChoise[0], thisChoise);
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise);
                        case "prev" -> choiceMenu(prevChoise, thisChoise); // возврат в предыдущее меню
                    }
                }
                case 2 -> {
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise, splitChoise[1]);
                    }
                }
                case 3 -> {
                    switch (splitChoise[0]) {
                        case "set" -> set(thisChoise, splitChoise[1], splitChoise[2]);
                    }
                }
                default -> System.out.println("Команда не допустима");
            }
        }
    }

    public void starvingsInStepMenu(String prevChoise) {
        thisChoise = "starvingsInStep";
        System.out.print(thisChoise + "\n" +
                "show - показать все переменные \"" + thisChoise + "\"\n" +
                "show all - показать значение всех переменных \"" + thisChoise + "\"\n" +
                "show <value> - показать значение переменной <value> \"" + thisChoise + "\"\n" +
                "set <value> - изменить значение \"" + thisChoise + "\"\n" +
                "prev - предыдущее меню\n" +
                "conf - меню конфигурации\n" +
                "main - главное меню\n");
        while (thisChoise.equals("starvingsInStep")) {
            System.out.print(thisChoise + " >");
            splitChoise = console.nextLine().split(" ");
            switch (splitChoise.length) {
                case 1 -> {
                    choiceMenu(splitChoise[0], thisChoise);
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise);
                        case "prev" -> choiceMenu(prevChoise, thisChoise); // возврат в предыдущее меню
                    }
                }
                case 2 -> {
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise, splitChoise[1]);
                    }
                }
                case 3 -> {
                    switch (splitChoise[0]) {
                        case "set" -> set(thisChoise, splitChoise[1], splitChoise[2]);
                    }
                }
                default -> System.out.println("Команда не допустима");
            }
        }
    }

    //метод выбора пункта меню для перехода
    public void choiceMenu(String toMenu, String fromMenu) {
        switch (toMenu) {
            case "areasCount" -> areasCountMenu(fromMenu);
            case "objectsMaxCountsOnIsland" -> objectsMaxCountsOnIslandMenu(fromMenu);
            case "objectsTypesNames" -> objectsTypesNamesMenu(fromMenu);
            case "weights" -> weightsMenu(fromMenu);
            case "objectsMaxCountsOnArea" -> objectsMaxCountsOnAreaMenu(fromMenu);
            case "maxMoveSpeeds" -> maxMoveSpeedsMenu(fromMenu);
            case "maxFoodInAnimals" -> maxFoodInAnimalsMenu(fromMenu);
            case "eatingProbabilities" -> eatingProbabilitiesMenu(fromMenu);
            case "maxNumbersOfChilds" -> maxNumbersOfChildsMenu(fromMenu);
            case "breedingPeriods" -> breedingPeriodsMenu(fromMenu);
            case "breedingPairFlags" -> breedingPairFlagsMenu(fromMenu);
            case "starvingsInStep" -> starvingsInStepMenu(fromMenu);
            case "conf" -> confMenu(fromMenu);
            case "main" -> mainMenu(fromMenu);
        }
    }

    private void set(String thisChoise, String value) {
        switch (thisChoise) {
            case "objectsTypesNames" -> {
                if (value.equals("psevdo")) Config.CONFIG.setPsevdographics();
            }
        }
    }

    private void set(String thisChoise, String variable, String value) {
        int id = Config.CONFIG.getLiveObjectTypeId(variable);
        if ((id > -1)||(thisChoise.equals("areasCount"))) {
            switch (thisChoise) {
                case "areasCount" -> {
                    int intValue1 =0;
                    int intValue2 =0;
                    boolean flag = false;
                    if (isInt(variable)) {
                        intValue1 = Integer.parseInt(variable);
                        if((intValue1 >= 0)&&(intValue1 <= Integer.MAX_VALUE))
                            flag=true;
                    }
                    if (isInt(value)) {
                        intValue2 = Integer.parseInt(value);
                        if((intValue2 >= 0)&&(intValue2 <= Integer.MAX_VALUE))
                            flag=true;
                    }
                    if(flag){
                        Config.CONFIG.setAreasCount(intValue1, intValue2);
                    } else
                        System.out.println("<value> - целое число от 0 до " + Integer.MAX_VALUE);
                }

//                case "objectsMaxCountsOnIsland" -> {
//                    if (isInt(value))
//                        Config.CONFIG.setObjectsMaxCountOnIsland(id, Integer.parseInt(value));
//                    else
//                        System.out.println("<value> - целое число от 0 до " + Integer.MAX_VALUE);
//                }
                case "objectsTypesNames" -> Config.CONFIG.setObjectTypeName(id, value);

                case "weights" -> {
                    if (isDouble(value))
                        Config.CONFIG.setWeight(id, Double.parseDouble(value));
                    else
                        System.out.println("<value> - число с плавающей запятой");
                }
                case "objectsMaxCountsOnArea" -> {
                    if (isInt(value))
                        Config.CONFIG.setObjectMaxCountOnArea(id, Integer.parseInt(value));
                    else
                        System.out.println("<value> - целое число от 0 до " + Integer.MAX_VALUE);
                }
                case "maxMoveSpeeds" -> {
                    if (isInt(value))
                        Config.CONFIG.setMaxMoveSpeed(id, Integer.parseInt(value));
                    else
                        System.out.println("<value> - целое число от 0 до " + Integer.MAX_VALUE);
                }
                case "maxFoodInAnimals" -> {
                    if (isDouble(value))
                        Config.CONFIG.setMaxFoodInAnimal(id, Double.parseDouble(value));
                    else
                        System.out.println("<value> - число с плавающей запятой");
                }
//                case "maxNumbersOfChilds" -> {
//                    if (isInt(value))
//                        Config.CONFIG.setMaxNumberOfChild(id, Integer.parseInt(value));
//                    else
//                        System.out.println("<value> - целое число от 0 до " + Integer.MAX_VALUE);
//                }
                case "breedingPeriods" -> {
                    if (isInt(value)) {
                        int intValue = Integer.parseInt(value);
                        if (intValue >= 0) {
                            Config.CONFIG.setBreedingPeriod(id, intValue);
                        } else
                            System.out.println("<value> - целое положительное число");
                    }
                }
//                case "breedingPairFlags" -> {
//                    if (isBoolean(value))
//                        Config.CONFIG.setBreedingPairFlag(id, Boolean.parseBoolean(value));
//                    else
//                        System.out.println("<value> - принимает значение \"true\" и \"false\"");
//                }
                case "starvingsInStep" ->{
                    if (isDouble(value))
                        Config.CONFIG.setStarvingInStep(id, Double.parseDouble(value));
                    else
                        System.out.println("<value> - число с плавающей запятой");
                }
            }
        }
    }

    private void set(String thisChoise, String variable1, String variable2, String value) {
        int id1 = Config.CONFIG.getLiveObjectTypeId(variable1);
        int id2 = Config.CONFIG.getLiveObjectTypeId(variable2);
        if ((id1 > -1) && (id2 > -1)) {
            switch (thisChoise) {
                case "eatingProbabilities" -> {
                    if (isInt(value)) {
                        int intValue = Integer.parseInt(value);
                        if ((intValue >= 0) && (intValue <= 100))
                            Config.CONFIG.setEatingProbability(id1, id2, intValue);
                        else
                            System.out.println("<value> - целое число от 0 до 100");
                    }
                }
            }
        }
    }

    private void show(String thisChoise) {
        switch (thisChoise) {
            case "conf" -> showVariables();
            case "areasCount" -> {
                int[] values =Config.CONFIG.getAreasCount();
                System.out.println(thisChoise + " = " + values[0] + " Х "+ values[1]);
            }
            default -> showLiveObjects();
        }
    }

    private void show(String thisChoise, String variable) {
        boolean flag = false;
        int idBegin1 = 0, idBegin2, idEnd = Config.CONFIG.getliveObjectsCount() - 1;
        if (variable.equals("all")) flag = true;
        else if (Config.CONFIG.getLiveObjectTypeId(variable) > -1) {
            idBegin1 = Config.CONFIG.getLiveObjectTypeId(variable);
            idEnd = idBegin1;
            flag = true;
        }
        if (flag) {
            while (idBegin1 <= idEnd) {
                switch (thisChoise) {
//                    case "objectsMaxCountsOnIsland" -> System.out.println(Config.CONFIG.getLiveObjectsTypes(idBegin1) + " = " + Config.CONFIG.getObjectsMaxCountOnIsland(idBegin1));
                    case "objectsTypesNames" -> System.out.println(Config.CONFIG.getLiveObjectsTypes(idBegin1) + " = " + Config.CONFIG.getObjectTypeName(idBegin1));
                    case "weights" -> System.out.println(Config.CONFIG.getLiveObjectsTypes(idBegin1) + " = " + Config.CONFIG.getWeight(idBegin1));
                    case "objectsMaxCountsOnArea" -> System.out.println(Config.CONFIG.getLiveObjectsTypes(idBegin1) + " = " + Config.CONFIG.getObjectMaxCountOnArea(idBegin1));
                    case "maxMoveSpeeds" -> System.out.println(Config.CONFIG.getLiveObjectsTypes(idBegin1) + " = " + Config.CONFIG.getMaxMoveSpeed(idBegin1));
                    case "maxFoodInAnimals" -> System.out.println(Config.CONFIG.getLiveObjectsTypes(idBegin1) + " = " + Config.CONFIG.getMaxFoodInAnimal(idBegin1));
//                    case "maxNumbersOfChilds" -> System.out.println(Config.CONFIG.getLiveObjectsTypes(idBegin1) + " = " + Config.CONFIG.getMaxNumberOfChild(idBegin1));
                    case "breedingPeriods" -> System.out.println(Config.CONFIG.getLiveObjectsTypes(idBegin1) + " = " + Config.CONFIG.getBreedingPeriod(idBegin1));
//                    case "breedingPairFlags" -> System.out.println(Config.CONFIG.getLiveObjectsTypes(idBegin1) + " = " + Config.CONFIG.getBreedingPairFlag(idBegin1));
                    case "starvingsInStep" -> System.out.println(Config.CONFIG.getLiveObjectsTypes(idBegin1) + " = " + Config.CONFIG.getStarvingInStep(idBegin1));
                    case "eatingProbabilities" -> {
                        for (idBegin2 = 0; idBegin2 <= idEnd; idBegin2++) {
                            System.out.println("probability " +
                                    Config.CONFIG.getLiveObjectsTypes(idBegin1) + " eat " +
                                    Config.CONFIG.getLiveObjectsTypes(idBegin2) + " = " +
                                    Config.CONFIG.getEatingProbability(idBegin1, idBegin2) + "%");
                        }
                    }
                }
                idBegin1++;
            }
        } else System.out.println("Параметр " + variable + " не распознан");
    }

    private void show(String thisChoise, String variable1, String variable2) {
        int idBegin1 = 0, idBegin2 = 0, idEnd1 = 0, idEnd2 = 0;

        boolean flag = false;
        if ((variable1.equals("each")) && (Config.CONFIG.getLiveObjectTypeId(variable2) > -1)) {
            idEnd1 = Config.CONFIG.getliveObjectsCount() - 1;
            idBegin2 = Config.CONFIG.getLiveObjectTypeId(variable2);
            idEnd2 = idBegin2;
            flag = true;
        } else if ((variable2.equals("each")) && (Config.CONFIG.getLiveObjectTypeId(variable1) > -1)) {
            idEnd2 = Config.CONFIG.getliveObjectsCount() - 1;
            idBegin1 = Config.CONFIG.getLiveObjectTypeId(variable1);
            idEnd1 = idBegin1;
            flag = true;
        } else if ((Config.CONFIG.getLiveObjectTypeId(variable1) > -1) && (Config.CONFIG.getLiveObjectTypeId(variable2) > -1)) {
            idBegin1 = Config.CONFIG.getLiveObjectTypeId(variable1);
            idEnd1 = idBegin1;
            idBegin2 = Config.CONFIG.getLiveObjectTypeId(variable2);
            idEnd2 = idBegin2;
            flag = true;
        }
        if (flag) {
            for (int i = idBegin1; i <= idEnd1; i++) {
                for (int j = idBegin2; j <= idEnd2; j++) {
                    switch (thisChoise) {
                        case "eatingProbabilities" -> System.out.println("probability " +
                                Config.CONFIG.getLiveObjectsTypes(i) + " eat " +
                                Config.CONFIG.getLiveObjectsTypes(j) + " = " +
                                Config.CONFIG.getEatingProbability(i, j) + "%");
                    }
                }
            }
        } else System.out.println("Совокупность параметров " + variable1 + " " + variable2 + " не распознана");
    }

    private void showVariables() {
        for (Field field : Config.CONFIG.getClass().getDeclaredFields()) {
            if ((field.getModifiers() == Modifier.PRIVATE) && (!(field.getName().equals("liveObjectsTypes"))) && (!(field.getName().equals("objectsTypesPsevdographic")))&& (!(field.getName().equals("areaNamePrefix"))))
                System.out.println(field.getName());
        }
    }

    private void showLiveObjects() {
        for (String valueOfLiveObjectsTypes : Config.CONFIG.getLiveObjectsTypes())
            System.out.println(valueOfLiveObjectsTypes.toLowerCase());
    }

    private boolean isBoolean(String str) {
        if ((str == null) || (str.equals(""))) {
            System.out.println("Устанавлевоме значение отсутствует");
            return false;
        }
        try {
            boolean booleanValue = Boolean.parseBoolean(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Устанавлевоме значение не допустимо");
        }
        return false;
    }

    private boolean isInt(String str) {
        if ((str == null) || (str.equals(""))) {
            System.out.println("Устанавлевоме значение отсутствует");
            return false;
        }
        try {
            int intValue = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Устанавлевоме значение не допустимо");
        }
        return false;
    }

    private boolean isDouble(String str) {
        if ((str == null) || (str.equals(""))) {
            System.out.println("Устанавлевоме значение отсутствует");
            return false;
        }
        try {
            double doubleValue = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Устанавлевоме значение не допустимо");
        }
        return false;
    }
}
