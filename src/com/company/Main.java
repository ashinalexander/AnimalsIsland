package com.company;

import java.io.Console;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Config.loadConfig(); //загрузка конфига из файла

//        Config config = Config.getInstance(); //получение конфига по умолчанию
        Menu menu = new Menu();
        Config.CONFIG.saveConfig(); //сохранение измненного дефолта в файл

        Thread island = new Thread(Island.getInstance()); //создаем поток острова и сам остров
        island.start(); //активируем работу острова
        try {
            island.join(); //дожидаемся окончания работы острова
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
