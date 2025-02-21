package root.files.file;

import root.files.seClasses.*;

public class Parser {
    public String parseDragonToLine(Dragon dragon){
        String dragonLine;
        if (dragon.getKiller() != null){
            dragonLine = dragon.getId() + "," +
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
                    dragon.getKiller().getLocation().getName() + "\n";
        } else {
            dragonLine = dragon.getId() + "," +
                    dragon.getName() + "," +
                    dragon.getCoordinates().getX() + "," +
                    dragon.getCoordinates().getY() + "," +
                    dragon.getAge() + "," +
                    dragon.getDescription() + "," +
                    dragon.getWeight() + "," +
                    dragon.getType() + "\n";
        }
        return dragonLine;
    }

    public Dragon parseLineToDragon(String line) {
        String[] values = line.split(",");
        Dragon res;
        // надо добавить валидатор
        if (values.length == 16) {
            long id = Long.parseLong(values[0]);

            String name = values[1];

            Float coordinateX = Float.valueOf(values[2]);
            Integer coordinateY = Integer.valueOf(values[3]);

            Long age = Long.valueOf(values[4]);
            String description = values[5];
            Long weight = Long.valueOf(values[6]);
            DragonType type = DragonType.valueOf(values[7]);

            // person подумай еще 5 раз, это поле может быть пустым
            String killerId = values[8];
            String killerName = values[9];
            BrightColor killerEyeColor = BrightColor.ValueOf(values[10]);
            NaturalColor killerHairColor = NaturalColor.ValueOf(values[11]);

            //location тут обратит внимание на имя
            int locationX = Integer.parseInt(values[12]);
            Integer locationY = Integer.valueOf(values[13]);
            double locationZ = Double.parseDouble(values[14]);
            String locationName = values[15];

            Dragon dragon = new Dragon(
                    name,
                    new Coordinates(coordinateX, coordinateY),
                    age,
                    description,
                    weight,
                    type,
                    new Person(killerName, killerEyeColor, killerHairColor, new Location(locationX, locationY, locationZ, locationName))
            );
            dragon.setId(id);
            dragon.getKiller().setPassportID(killerId);

            res = dragon;
        } else if (values.length == 8) {
            long id = Long.parseLong(values[0]);
            String name = values[1];

            Float coordinateX = Float.valueOf(values[2]);
            Integer coordinateY = Integer.valueOf(values[3]);

            Long age = (values[4] == null ? null: Long.valueOf(values[6]));;
            String description = values[5];
            Long weight = (values[6] == null ? null: Long.valueOf(values[6]));
            DragonType type = DragonType.valueOf(values[7]);

            Dragon dragon = new Dragon(
                    name,
                    new Coordinates(coordinateX, coordinateY),
                    age,
                    description,
                    weight,
                    type
            );
            dragon.setId(id);

            res = dragon;
        } else {
            throw new RuntimeException("В файле не валидные значения.");
        }
        return res;
    }
}
