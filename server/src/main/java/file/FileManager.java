package file;


import collection.Validator;
import seClasses.Dragon;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Класс FileManager отвечает за сохранение и загрузку данных о драконах в CSV-файл.
 * Использует Parser для преобразования объектов Dragon в строки и обратно, а также Validator для проверки корректности данных.
 */
public class FileManager {
    private final String fileName;
    Parser parser = new Parser();
    Validator validator = new Validator();

    /**
     * Конструктор класса FileManager.
     * @param fileName Имя файла, с которым будет работать FileManager.
     */
    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Сохраняет коллекцию драконов в CSV-файл.
     * @param dragons Коллекция драконов, которую нужно сохранить.
     */
    public void saveCSV(PriorityQueue<Dragon> dragons) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(this.fileName))) {
            System.out.println("Сохраняем " + dragons.size() + " драконов.");
            for (Dragon dragon : dragons) {
                if (validator.getValid(dragon) != null) {
                    writer.write(parser.parseDragonToLine(dragon));
                }
            }
        } catch (IOException e) {
            System.out.println("Невозможно записать в файл: " + e.getMessage());
        }
    }

    /**
     * Загружает коллекцию драконов из CSV-файла.
     * @return Коллекция драконов, загруженная из файла.
     */
    public PriorityQueue<Dragon> loadCSV() {
        File file = new File(this.fileName);
        PriorityQueue<Dragon> dragons = new PriorityQueue<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Dragon dragon = null;
                try {
                    dragon = validator.getValid(parser.parseLineToDragon(line));
                } catch (Exception e) {
                    System.out.println("В файле невалидные значения");
                }

                if (dragon != null) {
                    dragon = validator.getValidatedId(dragon);
                    dragons.add(dragon);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Загружена коллекция из " + dragons.size() + " драконов.");
        return dragons;
    }
}