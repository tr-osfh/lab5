package root.files.console;

import root.files.seClasses.BrightColor;
import root.files.seClasses.DragonType;
import root.files.seClasses.NaturalColor;

import static root.files.console.ReadWriteManager.*;

public class Reader {
    public static String readName(){
        System.out.print("\n Введите имя дракона: ");
        String name = readLine();
        for (;;){
            if (name.isEmpty() || name.isBlank()){
                System.out.print("\n Строка не может быть пустой! Введите имя дракона: ");
            } else {
                return name;
            }
        }
    }

    public static Float readCoordinateX(){
        System.out.println("\n Введите координату X: ");
        Float coordinateX = readFloat();
        for (;;){
            if (coordinateX == null){
                System.out.print("\n Строка не может быть пустой. Введите координату X: ");
            } else {
                return coordinateX;
            }
        }
    }

    public static Integer readCoordinateY(){
        System.out.print("\n Введите координату Y: ");
        Integer coordinateY = readInteger();
        for (;;){
            if (coordinateY == null){
                System.out.print("\n Строка не может быть пустой. Введите координату Y: ");
            } else {
                return coordinateY;
            }
        }
    }

    public static Long readAge(){
        System.out.print("\n Введите возраст дракона: ");
        Long age = readLong();
        for (;;){
            if (age == null) {
                return age;
            } else if (age < 0){
                System.out.print("\n Возраст должен быть больше 0. Введите возраст дракона: ");
            } else {
                return age;
            }
        }
    }

    public static String readDescription(){
        System.out.print("\n Введите описание дракона: ");
        return readLine();
    }

    public static Long readWeight(){
        System.out.print("\n Введите вес дракона: ");
        Long weight = readLong();
        for (;;){
            if (weight == null) {
                return weight;
            } else if (weight < 0){
                System.out.print("\n Вес должен быть больше 0. Введите возраст дракона: ");
            } else {
                return weight;
            }
        }
    }

    public static DragonType readType() {
        String type = readLine();
        for (; ; ) {
            switch (type) {
                case "WATER":
                    return DragonType.WATER;
                case "UNDERGROUND":
                    return DragonType.UNDERGROUND;
                case "AIR":
                    return DragonType.AIR;
                default:
                    System.out.print("\n Выбирите один из трех типов (WATER, UNDERGROUND, AIR). Введите тип дракона: ");
            }

        }
    }

    public static BrightColor readBrightColor(){
        System.out.print("\n Введите цвет глаз: ");
        String color = readLine();
        for (;;) {
            if (color.isEmpty())
                System.out.print("\n Цвет глаз не может быть пустым. Введите цвет глаз: ");
            else{
                switch (color){
                    case "GREEN":
                        return BrightColor.GREEN;
                    case "BLACK":
                        return BrightColor.BLACK;
                    case "BLUE":
                        return BrightColor.BLUE;
                    case "YELLOW":
                        return BrightColor.YELLOW;
                    case "ORANGE":
                        return BrightColor.ORANGE;
                    default:
                        System.out.print("\n Выберите один из 5 типов \n    (GREEN\n" +
                                "    BLACK\n" +
                                "    BLUE\n" +
                                "    YELLOW\n" +
                                "    ORANGE)");
                }
            }
        }
    }

    public static NaturalColor readNaturalColor(){
        System.out.print("\n Введите цвет волос");
        String color = readLine();
        for (;;) {
            switch (color){
                case "BLACK":
                    return NaturalColor.BLACK;
                case "RED":
                    return NaturalColor.RED;
                case "YELLOW":
                    return NaturalColor.YELLOW;
                case "WHITE":
                    return NaturalColor.WHITE;
                case "BROWN":
                    return NaturalColor.BROWN;
                default:
                    System.out.print("\n Выберите один из 5 типов \n" + "    RED,\n" +
                            "    BLACK,\n" +
                            "    YELLOW,\n" +
                            "    WHITE,\n" +
                            "    BROWN; \n");
            }
        }
    }

    public static int readLocationX(){
        System.out.println("\n Введите координату X: ");
        Integer x = readInteger();
        for (;;){
            if (x == null){
                System.out.print("\n Строка не может быть пустой. Введите координату X: ");
            } else {
                return x;
            }
        }
    }

    public static Integer readLocationY(){
        System.out.println("\n Введите координату Y: ");
        Integer y = readInteger();
        for (;;){
            if (y == null){
                System.out.print("\n Строка не может быть пустой. Введите координату Y: ");
            } else {
                return y;
            }
        }
    }

    public static double readLocationZ(){
        System.out.println("\n Введите координату Z: ");
        Double z = readDouble();
        for (;;){
            if (z == null){
                System.out.print("\n Строка не может быть пустой. Введите координату Z: ");
            } else {
                return z;
            }
        }

    }
}
