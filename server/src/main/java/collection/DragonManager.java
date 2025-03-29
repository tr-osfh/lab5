package collection;

import file.ScriptReaderManager;
import seClasses.Dragon;

import java.io.Reader;

/**
 * Класс DragonManager управляет созданием объектов типа Dragon.
 * Использует ConsoleManager для получения данных от пользователя.
 */
public class DragonManager {
    Reader read;

    /**
     * Конструктор класса DragonManager.
     */
    public DragonManager(ConsoleManager reader) {
        this.read = reader;
    }

    public DragonManager(ScriptReaderManager reader){
        this.read = reader;
    }

    /**
     * Создает объект Dragon на основе данных, введенных пользователем.
     * @return Объект Dragon с заданными параметрами.
     */
    public Dragon setDragon() {
        String name = read.readName();

        Float coordinateX = read.readCoordinateX();
        Integer coordinateY = read.readCoordinateY();

        Long age = read.readAge();
        String description = read.readDescription();
        Long weight = read.readWeight();
        DragonType type = read.readType();

        if (read.readChoice()){
            String killerName = read.readName();
            String killerPassportId = read.readPassportID();
            BrightColor killerEyeColor = read.readBrightColor();
            NaturalColor killerHairColor = read.readNaturalColor();

            int locationX = read.readLocationX();
            Integer locationY = read.readLocationY();
            double locationZ = read.readLocationZ();
            String locationName = read.readLocationName();

            return new Dragon(
                    name,
                    new Coordinates(coordinateX, coordinateY),
                    age,
                    description,
                    weight,
                    type,
                    new Person(killerName, killerPassportId, killerEyeColor, killerHairColor, new Location(locationX,locationY,locationZ, locationName))
            );
        } else {
            return new Dragon(
                    name,
                    new Coordinates(coordinateX, coordinateY),
                    age,
                    description,
                    weight,
                    type
            );
        }
    }
}