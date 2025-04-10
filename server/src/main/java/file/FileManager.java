package file;

import collection.Validator;
import seClasses.Dragon;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.PriorityQueue;

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
        try (OutputStream outputStream = new FileOutputStream(this.fileName);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {

            System.out.println("Сохраняем " + dragons.size() + " драконов.");
            for (Dragon dragon : dragons) {
                if (validator.getValid(dragon) != null) {
                    writer.write(parser.parseDragonToLine(dragon));
                    writer.newLine();
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
        PriorityQueue<Dragon> dragons = new PriorityQueue<>();

        try (InputStream inputStream = new FileInputStream(this.fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
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

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + e.getMessage(), e);
        }

        System.out.println("Загружена коллекция из " + dragons.size() + " драконов.");
        return dragons;
    }
}