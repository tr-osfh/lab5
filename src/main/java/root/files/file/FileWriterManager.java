package root.files.file;

import root.files.commands.Command;
import root.files.seClasses.*;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

import static root.files.console.Reader.*;
import static root.files.console.Reader.readName;

public class FileWriterManager {
    private final String fileName;
    Parser parser = new Parser();

    public FileWriterManager(String fileName){
        this.fileName = fileName;
    }

    public void saveCSV(PriorityQueue<Dragon> dragons) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(this.fileName))) {
             System.out.println("Сохраняем " + dragons.size() + " драконов.");
             for (Dragon dragon : dragons) {
                if (dragon.getKiller() != null) {
                    writer.write(
                            dragon.getId() + "," +
                                    dragon.getName() + "," +
                                    dragon.getCoordinates().getX() + "," +
                                    dragon.getCoordinates().getY() + "," +
                                    dragon.getAge() + "," +
                                    dragon.getDescription() + "," +
                                    dragon.getWeight() + "," +
                                    dragon.getType() + "," +
                                    dragon.getKiller().getPassportID() + "," +
                                    dragon.getKiller().getName() + "," +
                                    dragon.getKiller().getEyeColor() + "," +
                                    dragon.getKiller().getHairColor() + "," +
                                    dragon.getKiller().getLocation().getX() + "," +
                                    dragon.getKiller().getLocation().getY() + "," +
                                    dragon.getKiller().getLocation().getZ() + "," +
                                    dragon.getKiller().getLocation().getName() + "\n"
                    );
                } else {
                    writer.write(
                            dragon.getId() + "," +
                                    dragon.getName() + "," +
                                    dragon.getCoordinates().getX() + "," +
                                    dragon.getCoordinates().getY() + "," +
                                    dragon.getAge() + "," +
                                    dragon.getDescription() + "," +
                                    dragon.getWeight() + "," +
                                    dragon.getType() + "\n"
                    );
                }
            }
        } catch (IOException e) {
            System.out.println("Невозможно записать в файл: " + e.getMessage());
        }
    }

    public ArrayList<String> loadScript(){
        ArrayList<String> commands = new ArrayList<>();
        for (;;){
            String link = readLink();
            File file = new File(link);
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    commands.add(line);
                    System.out.println("Загружен скрипт из " + commands.size() + " команд.");
                }
                return commands;
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден.");;
            }
        }
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
