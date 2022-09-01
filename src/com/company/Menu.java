package com.company;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

import static com.company.Config.*;

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
                    case "conf" -> {
                        choiceMenu("conf", thisChoise);
                    }
                    case "prev" -> {
                        choiceMenu(prevChoise, thisChoise);
                    }
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
                    case "save" -> CONFIG.saveConfig(); //сохранение измненного дефолта в файл
                    default -> {
                        if (!splitChoise[0].equals("start"))
                            System.out.println("Команда не допустима");
                    }
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
                        default -> {
                            if (!splitChoise[0].equals("start"))
                                System.out.println("Команда не допустима");
                        }
                    }

                }
                case 3 -> {
                    switch (splitChoise[0]) {
                        case "set" -> set(thisChoise, splitChoise[1], splitChoise[2]);
                    }
                }
                default -> System.out.println("areasCount: " + thisChoise + ": Команда не допустима");
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
                        default -> {
                            if (!splitChoise[0].equals("start"))
                                System.out.println("Команда не допустима");
                        }
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
                default -> System.out.println("objectsMaxCountsOnIsland: Команда не допустима");
            }
        }
    }

    public void stopOptionsMenu(String prevChoise) {
        thisChoise = "stopOptions";
        System.out.print(thisChoise + "\n" +
                "show - показать все переменные \"" + thisChoise + "\"\n" +
                "show all - показать значение всех переменных \"" + thisChoise + "\"\n" +
                "show <variable> - показать значение переменной <variable>\n" +
                "set <variable> <value> - изменить значение переменной <variable>\n" +
                "prev - предыдущее меню\n" +
                "conf - меню конфигурации\n" +
                "main - главное меню\n");
        while (thisChoise.equals("stopOptions")) {
            System.out.print(thisChoise + " >");
            splitChoise = console.nextLine().split(" ");
            switch (splitChoise.length) {
                case 1 -> {
                    choiceMenu(splitChoise[0], thisChoise);
                    switch (splitChoise[0]) {
                        case "show" -> show(thisChoise);
                        case "prev" -> choiceMenu(prevChoise, thisChoise); // возврат в предыдущее меню
                        default -> {
                            if (!splitChoise[0].equals("start"))
                                System.out.println("Команда не допустима");
                        }
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
                default -> System.out.println("stopOptions: Команда не допустима");
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
                        default -> {
                            if (!splitChoise[0].equals("start"))
                                System.out.println("Команда не допустима");
                        }
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
                default -> System.out.println("objectsTypesNames: Команда не допустима");
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
                        default -> {
                            if (!splitChoise[0].equals("start"))
                                System.out.println("Команда не допустима");
                        }
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
                default -> System.out.println("weights: Команда не допустима");
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
                        default -> {
                            if (!splitChoise[0].equals("start"))
                                System.out.println("Команда не допустима");
                        }
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
                default -> System.out.println("objectsMaxCountsOnArea: Команда не допустима");
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
                        default -> {
                            if (!splitChoise[0].equals("start"))
                                System.out.println("Команда не допустима");
                        }
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
                default -> System.out.println("maxMoveSpeeds: Команда не допустима");
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
                        default -> {
                            if (!splitChoise[0].equals("start"))
                                System.out.println("Команда не допустима");
                        }
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
                default -> System.out.println("maxFoodInAnimals: Команда не допустима");
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
                        default -> {
                            if (!splitChoise[0].equals("start"))
                                System.out.println("Команда не допустима");
                        }
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
                default -> System.out.println("eatingProbabilities: Команда не допустима");
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
                        default -> {
                            if (!splitChoise[0].equals("start"))
                                System.out.println("Команда не допустима");
                        }
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
                default -> System.out.println("maxNumbersOfChilds: Команда не допустима");
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
                        default -> {
                            if (!splitChoise[0].equals("start"))
                                System.out.println("Команда не допустима");
                        }
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
                default -> System.out.println("breedingPeriods: Команда не допустима");
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
                        default -> {
                            if (!splitChoise[0].equals("start"))
                                System.out.println("Команда не допустима");
                        }
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
                default -> System.out.println("breedingPairFlags: Команда не допустима");
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
                        default -> {
                            if (!splitChoise[0].equals("start"))
                                System.out.println("Команда не допустима");
                        }
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
                default -> System.out.println("starvingsInStep: Команда не допустима");
            }
        }
    }

    //метод выбора пункта меню для перехода
    public void choiceMenu(String toMenu, String fromMenu) {
        switch (toMenu) {
            case "stopOptions" -> stopOptionsMenu(fromMenu);
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
                if (value.equals("psevdo")) CONFIG.setPsevdographics();
            }
        }
    }

    private void set(String thisChoise, String variable, String value) {
        int id = CONFIG.getLiveObjectTypeId(variable);
        if ((id > -1) || (thisChoise.equals("areasCount")||(thisChoise.equals("stopOptions")))) {

            switch (thisChoise) {
                case "areasCount" -> {
                    int intValue1 = 0;
                    int intValue2 = 0;
                    boolean flag = false;
                    if (isInt(variable)) {
                        intValue1 = Integer.parseInt(variable);
                        if (intValue1 >= 0)
                            flag = true;
                    }
                    if (isInt(value)) {
                        intValue2 = Integer.parseInt(value);
                        if (intValue2 >= 0)
                            flag = true;
                    }
                    if (flag) {
                        CONFIG.setAreasCount(intValue1, intValue2);
                    } else
                        System.out.println("Поддерживаются значения от 0 до " + Integer.MAX_VALUE);
                }

                case "stopOptions" -> {
                    switch (variable) {
                        case "finalStep" -> {
                            if (isInt(value)) {
                                int intValue = Integer.parseInt(value);
                                if (intValue >= 0) CONFIG.setFinalStep(intValue);
                            } else System.out.println("Поддерживаются значения от 0 до " + Integer.MAX_VALUE);
                        }
                        case "stopCountFlag" -> {
                            if (isBoolean(value))
                                CONFIG.setStopCountFlag(Boolean.parseBoolean(value));
                            else System.out.println("Поддерживаются значения \"true\" и \"false\"");
                        }
                        case "allDiedFlag" -> {
                            if (isBoolean(value))
                                CONFIG.setAllDiedFlag(Boolean.parseBoolean(value));
                            else System.out.println("Поддерживаются значения \"true\" и \"false\"");
                        }
                    }
                }

//                case "objectsMaxCountsOnIsland" -> {
//                    if (isInt(value))
//                        Config.CONFIG.setObjectsMaxCountOnIsland(id, Integer.parseInt(value));
//                    else
//                        System.out.println("<value> - целое число от 0 до " + Integer.MAX_VALUE);
//                }
                case "objectsTypesNames" -> CONFIG.setObjectTypeName(id, value);

                case "weights" -> {
                    if (isDouble(value))
                        CONFIG.setWeight(id, Double.parseDouble(value));
                    else
                        System.out.println("<value> - число с плавающей запятой");
                }
                case "objectsMaxCountsOnArea" -> {
                    if (isInt(value))
                        CONFIG.setObjectMaxCountOnArea(id, Integer.parseInt(value));
                    else
                        System.out.println("<value> - целое число от 0 до " + Integer.MAX_VALUE);
                }
                case "maxMoveSpeeds" -> {
                    if (isInt(value))
                        CONFIG.setMaxMoveSpeed(id, Integer.parseInt(value));
                    else
                        System.out.println("<value> - целое число от 0 до " + Integer.MAX_VALUE);
                }
                case "maxFoodInAnimals" -> {
                    if (isDouble(value))
                        CONFIG.setMaxFoodInAnimal(id, Double.parseDouble(value));
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
                            CONFIG.setBreedingPeriod(id, intValue);
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
                case "starvingsInStep" -> {
                    if (isDouble(value))
                        CONFIG.setStarvingInStep(id, Double.parseDouble(value));
                    else
                        System.out.println("<value> - число с плавающей запятой");
                }
            }
        }
    }

    private void set(String thisChoise, String variable1, String variable2, String value) {
        int id1 = CONFIG.getLiveObjectTypeId(variable1);
        int id2 = CONFIG.getLiveObjectTypeId(variable2);
        if ((id1 > -1) && (id2 > -1)) {
            switch (thisChoise) {
                case "eatingProbabilities" -> {
                    if (isInt(value)) {
                        int intValue = Integer.parseInt(value);
                        if ((intValue >= 0) && (intValue <= 100))
                            CONFIG.setEatingProbability(id1, id2, intValue);
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
                int[] values = CONFIG.getAreasCount();
                System.out.println(thisChoise + " = " + values[0] + " Х " + values[1]);
            }
            case "stopOptions" -> System.out.println("finalStep\nstopCountFlag\nallDiedFlag");

            default -> showLiveObjects();
        }
    }

    private void show(String thisChoise, String variable) {
        boolean flag = false;
        int idBegin1 = 0, idBegin2, idEnd = CONFIG.getliveObjectsCount() - 1;
        if (variable.equals("all")) flag = true;
        else if (CONFIG.getLiveObjectTypeId(variable) > -1) {
            idBegin1 = CONFIG.getLiveObjectTypeId(variable);
            idEnd = idBegin1;
            flag = true;
        }
        if (flag) {
            while (idBegin1 <= idEnd) {
                switch (thisChoise) {

                    case "stopOptions" -> {
                        idBegin1 = idEnd;
                        System.out.println("finalStep = " + CONFIG.getFinalStep() +
                                "\nstopCountFlag = " + CONFIG.getStopOptions()[0] +
                                "\nallDiedFlag = " + CONFIG.getStopOptions()[1]);
                    }
//                    case "objectsMaxCountsOnIsland" -> System.out.println(Config.CONFIG.getLiveObjectsTypes(idBegin1) + " = " + Config.CONFIG.getObjectsMaxCountOnIsland(idBegin1));
                    case "objectsTypesNames" -> System.out.println(CONFIG.getLiveObjectsTypes(idBegin1) + " = " + CONFIG.getObjectTypeName(idBegin1));
                    case "weights" -> System.out.println(CONFIG.getLiveObjectsTypes(idBegin1) + " = " + CONFIG.getWeight(idBegin1));
                    case "objectsMaxCountsOnArea" -> System.out.println(CONFIG.getLiveObjectsTypes(idBegin1) + " = " + CONFIG.getObjectMaxCountOnArea(idBegin1));
                    case "maxMoveSpeeds" -> System.out.println(CONFIG.getLiveObjectsTypes(idBegin1) + " = " + CONFIG.getMaxMoveSpeed(idBegin1));
                    case "maxFoodInAnimals" -> System.out.println(CONFIG.getLiveObjectsTypes(idBegin1) + " = " + CONFIG.getMaxFoodInAnimal(idBegin1));
//                    case "maxNumbersOfChilds" -> System.out.println(Config.CONFIG.getLiveObjectsTypes(idBegin1) + " = " + Config.CONFIG.getMaxNumberOfChild(idBegin1));
                    case "breedingPeriods" -> System.out.println(CONFIG.getLiveObjectsTypes(idBegin1) + " = " + CONFIG.getBreedingPeriod(idBegin1));
//                    case "breedingPairFlags" -> System.out.println(Config.CONFIG.getLiveObjectsTypes(idBegin1) + " = " + Config.CONFIG.getBreedingPairFlag(idBegin1));
                    case "starvingsInStep" -> System.out.println(CONFIG.getLiveObjectsTypes(idBegin1) + " = " + CONFIG.getStarvingInStep(idBegin1));
                    case "eatingProbabilities" -> {
                        for (idBegin2 = 0; idBegin2 <= idEnd; idBegin2++) {
                            System.out.println("probability " +
                                    CONFIG.getLiveObjectsTypes(idBegin1) + " eat " +
                                    CONFIG.getLiveObjectsTypes(idBegin2) + " = " +
                                    CONFIG.getEatingProbability(idBegin1, idBegin2) + "%");
                        }
                    }
                }
                idBegin1++;
            }
        } else if (thisChoise.equals("stopOptions")) {
            switch (thisChoise) {
                case "stopOptions" -> {
                    switch (variable) {
                        case "all" -> System.out.println("finalStep = " + CONFIG.getFinalStep() +
                                "\nstopCountFlag = " + CONFIG.getStopOptions()[0] +
                                "\nallDiedFlag = " + CONFIG.getStopOptions()[1]);
                        case "finalStep" -> System.out.println("finalStep = " + CONFIG.getFinalStep());
                        case "stopCountFlag" -> System.out.println("stopCountFlag = " + CONFIG.getStopOptions()[0]);
                        case "allDiedFlag" -> System.out.println("allDiedFlag = " + CONFIG.getStopOptions()[1]);
                        default -> System.out.println("show(2).stopOptions:Параметр " + variable + " не распознан");
                    }
                }
            }
        } else System.out.println("show(2).all: Параметр " + variable + " не распознан");
    }

    private void show(String thisChoise, String variable1, String variable2) {
        int idBegin1 = 0, idBegin2 = 0, idEnd1 = 0, idEnd2 = 0;

        boolean flag = false;
        if ((variable1.equals("each")) && (CONFIG.getLiveObjectTypeId(variable2) > -1)) {
            idEnd1 = CONFIG.getliveObjectsCount() - 1;
            idBegin2 = CONFIG.getLiveObjectTypeId(variable2);
            idEnd2 = idBegin2;
            flag = true;
        } else if ((variable2.equals("each")) && (CONFIG.getLiveObjectTypeId(variable1) > -1)) {
            idEnd2 = CONFIG.getliveObjectsCount() - 1;
            idBegin1 = CONFIG.getLiveObjectTypeId(variable1);
            idEnd1 = idBegin1;
            flag = true;
        } else if ((CONFIG.getLiveObjectTypeId(variable1) > -1) && (CONFIG.getLiveObjectTypeId(variable2) > -1)) {
            idBegin1 = CONFIG.getLiveObjectTypeId(variable1);
            idEnd1 = idBegin1;
            idBegin2 = CONFIG.getLiveObjectTypeId(variable2);
            idEnd2 = idBegin2;
            flag = true;
        }
        if (flag) {
            for (int i = idBegin1; i <= idEnd1; i++) {
                for (int j = idBegin2; j <= idEnd2; j++) {
                    switch (thisChoise) {
                        case "eatingProbabilities" -> System.out.println("probability " +
                                CONFIG.getLiveObjectsTypes(i) + " eat " +
                                CONFIG.getLiveObjectsTypes(j) + " = " +
                                CONFIG.getEatingProbability(i, j) + "%");
                    }
                }
            }
        } else System.out.println("Совокупность параметров " + variable1 + " " + variable2 + " не распознана");
    }

    private void showVariables() {
        for (Field field : CONFIG.getClass().getDeclaredFields()) {
            if ((field.getModifiers() == Modifier.PRIVATE) && (!(field.getName().equals("liveObjectsTypes"))) &&
                    (!(field.getName().equals("objectsTypesPsevdographic"))) &&
                    (!(field.getName().equals("areaNamePrefix"))) &&
                    !(field.getName().equals("finalStep")))
                System.out.println(field.getName());
        }
    }

    private void showLiveObjects() {
        for (String valueOfLiveObjectsTypes : CONFIG.getLiveObjectsTypes())
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
