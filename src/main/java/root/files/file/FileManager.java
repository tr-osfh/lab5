package root.files.file;

import root.files.seClasses.*;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FileManager {
    private final String fileName;
    Parser parser = new Parser();

    public FileManager(String fileName){
        this.fileName = fileName;
    }

    public void saveCSV(PriorityQueue<Dragon> dragons) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(this.fileName))) {
             System.out.println("Сохраняем " + dragons.size() + " драконов.");
             for (Dragon dragon : dragons) {
                 writer.write(parser.parseDragonToLine(dragon));
            }
        } catch (IOException e) {
            System.out.println("Невозможно записать в файл: " + e.getMessage());
        }
    }

    public ArrayList<String> loadScript(String link) {
        ArrayList<String> commands = new ArrayList<>();
        File file = new File(link);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.contains(link)){
                    commands.add(line);
                }
            }
            System.out.println("Загружен скрипт из " + commands.size() + " команд.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
            System.out.println(e.getStackTrace());
        }
        return commands;
    }

    public PriorityQueue<Dragon> loadCSV(){
        File file = new File(this.fileName);
        PriorityQueue<Dragon> dragons = new PriorityQueue<Dragon>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                dragons.add(parser.parseLineToDragon(line));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Загружена коллекция из " + dragons.size() + " драконов.");
        return dragons;
    }
}
