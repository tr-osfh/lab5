package root.files.console;
import root.files.seClasses.*;

import root.files.console.ConsoleManager;

public class DragonManager {
    ConsoleManager cm;
    public DragonManager(ConsoleManager cm) {
        this.cm = cm;
    }

    public Dragon setDragon() {
        String name = cm.readName();

        Float coordinateX = cm.readCoordinateX();
        Integer coordinateY = cm.readCoordinateY();

        Long age = cm.readAge();
        String description = cm.readDescription();
        Long weight = cm.readWeight();
        DragonType type = cm.readType();

        if (cm.readChoice("убийцу")){
            String killerName = cm.readName();
            String killerPassportId = cm.readPassportID();
            BrightColor killerEyeColor = cm.readBrightColor();
            NaturalColor killerHairColor = cm.readNaturalColor();

            int locationX = cm.readLocationX();
            Integer locationY = cm.readLocationY();
            double locationZ = cm.readLocationZ();
            String locationName = cm.readLocationName();

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
