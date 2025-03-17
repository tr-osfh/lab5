package root.files.file;

import root.files.seClasses.*;

/**
 * Класс Parser отвечает за преобразование объектов Dragon в строки и обратно.
 * Используется для сериализации и десериализации данных о драконах.
 */
public class Parser {

    /**
     * Преобразует объект Dragon в строку в формате CSV.
     * @param dragon Объект Dragon для преобразования.
     * @return Строка в формате CSV, представляющая объект Dragon.
     */
    public String parseDragonToLine(Dragon dragon) {
        String dragonLine;
        if (dragon.getKiller() != null) {
            // Если у дракона есть убийца, добавляем все поля, включая информацию об убийце
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
            // Если у дракона нет убийцы, добавляем только основные поля
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

    /**
     * Преобразует строку в формате CSV в объект Dragon.
     * @param line Строка в формате CSV, представляющая объект Dragon.
     * @return Объект Dragon, созданный из строки.
     * @throws RuntimeException Если строка содержит невалидные значения.
     */
    public Dragon parseLineToDragon(String line) {
        String[] values = line.split(",");
        Dragon res;
        try {
            if (values.length == 16) {
                long id = Long.parseLong(values[0]);
                String name = values[1];

                Float coordinateX = Float.valueOf(values[2]);
                Integer coordinateY = Integer.valueOf(values[3]);

                Long age = Long.valueOf(values[4]);
                String description = values[5];
                Long weight = Long.valueOf(values[6]);
                DragonType type = DragonType.valueOf(values[7]);

                String killerId = values[8];
                String killerName = values[9];
                BrightColor killerEyeColor = BrightColor.valueOf(values[10]);
                NaturalColor killerHairColor = NaturalColor.valueOf(values[11]);

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
                        new Person(killerName, killerId, killerEyeColor, killerHairColor, new Location(locationX, locationY, locationZ, locationName))
                );
                dragon.setId(id);
                dragon.getKiller().setPassportID(killerId);

                res = dragon;
            } else if (values.length == 8) {
                // Если строка не содержит информации об убийце
                long id = Long.parseLong(values[0]);
                String name = values[1];

                Float coordinateX = Float.valueOf(values[2]);
                Integer coordinateY = Integer.valueOf(values[3]);

                Long age = (values[4] == null ? null : Long.valueOf(values[4]));
                String description = values[5];
                Long weight = (values[6] == null ? null : Long.valueOf(values[6]));
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
                throw new RuntimeException("В файле невалидные значения.");
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException("В файле невалидные значения.");
        }
    }
}