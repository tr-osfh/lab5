package root.files.console;

import root.files.seClasses.BrightColor;
import root.files.seClasses.DragonType;
import root.files.seClasses.NaturalColor;

import static root.files.console.ReadWriteManager.*;

public class Reader {
    public static String readName(){
        System.out.println("Введите имя: ");
        String name;
        for (;;){
            name = readLine();
            if (name.isEmpty() || name.isBlank()){
                System.out.println("Строка не может быть пустой! Введите имя дракона: ");
            } else {
                return name;
            }
        }
    }

    public static Float readCoordinateX() {
        System.out.println("Введите координату X: ");
        Float coordinateX;

        for (;;) {
            try {
                coordinateX = readFloat();
                if (coordinateX == null) {
                    System.out.println("Строка не может быть пустой. Введите координату X: ");
                }else{
                    return coordinateX;
                }
            } catch (NumberFormatException e) {
                System.out.println("Число введено неверно. Введите координату X: ");
            }
        }
    }

    public static Integer readCoordinateY(){
        System.out.println("Введите координату Y: ");
        Integer coordinateY;
        for (;;){
            try {
                coordinateY = readInteger();
                if (coordinateY == null) {
                    System.out.println("Строка не может быть пустой. Введите координату Y: ");
                } else {
                    return coordinateY;
                }
            } catch (NumberFormatException e) {
                System.out.println("Число введено неверно. Введите координату Y: ");
            }
        }
    }

    public static Long readAge(){
        System.out.println("Введите возраст дракона: ");
        Long age;
        for (;;){
            try {
                age = readLong();
                if (age == null) {
                    return age;
                } else if (age < 0) {
                    System.out.println("Возраст должен быть больше 0. Введите возраст дракона: ");
                } else {
                    return age;
                }
            } catch (NumberFormatException e) {
                System.out.println("Число введено неверно. Введите возраст дракона: ");
            }
        }
    }

    public static String readDescription(){
        System.out.println("Введите описание дракона: ");
        return readLine();
    }

    public static Long readWeight() {
        System.out.println("Введите вес дракона: ");
        Long weight = null;
        while (true) {
            try {
                weight = readLong();  // Читаем ввод
                if (weight == null) {
                    System.out.println("Строка не может быть пустой. Введите вес дракона: ");
                } else if (weight < 0) {
                    System.out.println("Вес должен быть больше 0. Введите вес дракона: ");
                } else {
                    return weight;  // Возвращаем вес, если всё корректно
                }
            } catch (NumberFormatException e) {
                System.out.println("Число введено неверно. Введите вес дракона: ");
            }
        }
    }



    public static DragonType readType() {
        String type;
        System.out.println("Введите тип дракона: ");
        for (; ; ) {
            type = readLine();
            switch (type) {
                case "WATER":
                    return DragonType.WATER;
                case "UNDERGROUND":
                    return DragonType.UNDERGROUND;
                case "AIR":
                    return DragonType.AIR;
                default: System.out.println("Выбирите один из трех типов (WATER, UNDERGROUND, AIR). Введите тип дракона: ");
            }
        }
    }

    public static BrightColor readBrightColor(){
        System.out.println("Введите цвет глаз: ");
        String color;
        for (;;) {
            color = readLine();
            if (color.isEmpty())
                System.out.println("Цвет глаз не может быть пустым. Введите цвет глаз: ");
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
                        System.out.println("Выберите один из 5 типов \n    (GREEN\n" +
                                "    BLACK\n" +
                                "    BLUE\n" +
                                "    YELLOW\n" +
                                "    ORANGE)");
                }
            }
        }
    }

    public static NaturalColor readNaturalColor(){
        System.out.println("Введите цвет волос");
        String color;
        for (;;) {
            color = readLine();
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
                    System.out.println("Выберите один из 5 типов \n" + "    RED,\n" +
                            "    BLACK,\n" +
                            "    YELLOW,\n" +
                            "    WHITE,\n" +
                            "    BROWN; \n");
            }
        }
    }

    public static int readLocationX(){
        System.out.println("Введите координату X: ");
        Integer x;
        for (;;){
            try{
                x = readInteger();
                if (x == null){
                    System.out.println("Строка не может быть пустой. Введите координату X: ");
                } else {
                    return x;
                }
            } catch (NumberFormatException e) {
                System.out.println("Число введено неверно. Введите координату X: ");
            }
        }
    }

    public static Integer readLocationY(){
        System.out.println("Введите координату Y: ");
        Integer y;
        for (;;){
            try {
                y = readInteger();
                if (y == null){
                    System.out.print("Строка не может быть пустой. Введите координату Y: ");
                } else {
                    return y;
                }
            } catch (NumberFormatException e) {
                System.out.println("Число введено неверно. Введите координату Y: ");
            }

        }
    }

    public static double readLocationZ(){
        System.out.println("Введите координату Z: ");
        Double z;
        for (;;){
            z = readDouble();
            if (z == null){
                System.out.println("Строка не может быть пустой. Введите координату Z: ");
            } else {
                return z;
            }
        }

    }
}
