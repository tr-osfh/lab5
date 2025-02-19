package root.files.file;

import root.files.seClasses.*;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

import static root.files.console.Reader.*;
import static root.files.console.Reader.readName;

public class FileWriterManager {
    private final String fileName;

    public FileWriterManager(String fileName){
        this.fileName = fileName;
    }

    public void saveCSV(PriorityQueue<Dragon> dragons) {
        try {
            FileOutputStream stream = new FileOutputStream(this.fileName);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            try {
                writer.write(
                        "id," +
                                "name," +
                                "coordinateX," +
                                "coordinateY," +
                                "age," +
                                "description," +
                                "weight," +
                                "type," +
                                "killerID," +
                                "killerName," +
                                "killerEyeColor," +
                                "killerHairColor," +
                                "locationX," +
                                "locationY," +
                                "locationZ," +
                                "locationName\n"
                );

                for (Dragon dragon : dragons) {
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
                }
            } catch (IOException e) {
                System.out.println("Невозможно записать в файл.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public PriorityQueue<Dragon> loadCSV(){
        File file = new File(fileName);
        PriorityQueue<Dragon> dragons = new PriorityQueue<Dragon>();
        try {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()){
                scanner.nextLine();
            }
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] values = line.split(",");

                // надо добавить валидатор
                if (values.length == 15){

                    String name = values[0];

                    Float coordinateX = Float.valueOf(values[1]);
                    Integer coordinateY = Integer.valueOf(values[2]);

                    Long age = Long.valueOf(values[2]);
                    String description = values[2];
                    Long weight = Long.valueOf(values[2]);
                    DragonType type = DragonType.valueOf(values[2]);

                    // person подумай еще 5 раз, это поле может быть пустым
                    String killerName = values[2];
                    BrightColor killerEyeColor = BrightColor.ValueOf(values[2]);
                    NaturalColor killerHairColor = NaturalColor.ValueOf(values[2]);

                    //location тут обратит внимание на имя
                    int locationX = Integer.parseInt(values[2]);
                    Integer locationY = Integer.valueOf(values[2]);
                    double locationZ = Double.parseDouble(values[2]);
                    String locationName = values[2];


                    Dragon dragon = new Dragon(
                            name,
                            new Coordinates(coordinateX, coordinateY),
                            age,
                            description,
                            weight,
                            type,
                            new Person(killerName, killerEyeColor, killerHairColor, new Location(locationX,locationY,locationZ, locationName))
                    );
                    dragons.add(dragon);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return dragons;
    }
}
