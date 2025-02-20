package root.files.console;
import root.files.seClasses.*;

import static root.files.console.Reader.*;

public class DragonManager {

    public DragonManager() {
    }

    public Dragon setDragon() {
        String name = readName();

        Float coordinateX = readCoordinateX();
        Integer coordinateY = readCoordinateY();

        Long age = readAge();
        String description = readDescription();
        Long weight = readWeight();
        DragonType type = readType();

        if (readChoice("убийцу")){
            String killerName = readName();
            BrightColor killerEyeColor = readBrightColor();
            NaturalColor killerHairColor = readNaturalColor();

            //location тут обратит внимание на имя
            int locationX = readLocationX();
            Integer locationY = readLocationY();
            double locationZ = readLocationZ();
            String locationName = readLocationName();

            return new Dragon(
                    name,
                    new Coordinates(coordinateX, coordinateY),
                    age,
                    description,
                    weight,
                    type,
                    new Person(killerName, killerEyeColor, killerHairColor, new Location(locationX,locationY,locationZ, locationName))
            );
        }
        // person подумай еще 5 раз, это поле может быть пустым


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
