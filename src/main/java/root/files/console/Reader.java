package root.files.console;

import root.files.seClasses.BrightColor;
import root.files.seClasses.DragonType;
import root.files.seClasses.NaturalColor;

import static root.files.console.ReadController.*;

public class Reader {
    public static String readName(){
        System.out.print("Введите имя: ");
        String name;
        for (;;){
            name = readLine();
            System.out.println();
            if (name.isEmpty() || name.isBlank()){
                System.out.print("Строка не может быть пустой! Введите имя: ");
            } else {
                return name;
            }
        }
    }

    public static Float readCoordinateX() {
        System.out.print("Введите координату X: ");
        Float coordinateX;

        for (;;) {
            try {
                coordinateX = readFloat();
                System.out.println();
                if (coordinateX == null) {
                    System.out.print("Строка не может быть пустой. Введите координату X: ");
                }else{
                    return coordinateX;
                }
            } catch (NumberFormatException e) {
                System.out.print("Число введено неверно. Введите координату X: ");
            }
        }
    }

    public static Integer readCoordinateY(){
        System.out.print("Введите координату Y: ");
        Integer coordinateY;
        for (;;){
            try {
                coordinateY = readInteger();
                System.out.println();
                if (coordinateY == null) {
                    System.out.print("Строка не может быть пустой. Введите координату Y: ");
                } else {
                    return coordinateY;
                }
            } catch (NumberFormatException e) {
                System.out.print("Число введено неверно. Введите координату Y: ");
            }
        }
    }

    public static Long readAge(){
        System.out.print("Введите возраст дракона: ");
        Long age;
        for (;;){
            try {
                age = readLong();
                System.out.println();
                if (age == null) {
                    return null;
                } else if (age <= 0) {
                    System.out.print("Возраст должен быть больше 0. Введите возраст дракона: ");
                } else {
                    return age;
                }
            } catch (NumberFormatException e) {
                System.out.print("Число введено неверно. Введите возраст дракона: ");
            }
        }
    }

    public static String readDescription(){
        System.out.print("Введите описание дракона: ");
        String line = readLine();
        System.out.println();
        return line;
    }

    public static Long readWeight() {
        System.out.print("Введите вес дракона: ");
        Long weight = null;
        for (;;) {
            try {
                weight = readLong();
                System.out.println();
                if (weight == null) {
                    System.out.print("Строка не может быть пустой. Введите вес дракона: ");
                } else if (weight < 0) {
                    System.out.print("Вес должен быть больше 0. Введите вес дракона: ");
                } else {
                    return weight;
                }
            } catch (NumberFormatException e) {
                System.out.print("Число введено неверно. Введите вес дракона: ");
            }
        }
    }



    public static DragonType readType() {
        String type;
        for (; ; ) {
            System.out.print("Выбирите один из трех типов (WATER, UNDERGROUND, AIR). Введите тип дракона: ");
            type = readLine();
            System.out.println();
            switch (type) {
                case "WATER":
                    return DragonType.WATER;
                case "UNDERGROUND":
                    return DragonType.UNDERGROUND;
                case "AIR":
                    return DragonType.AIR;
                default:
                    System.out.println("Проверьте введенные данные.");
            }
        }
    }

    public static BrightColor readBrightColor(){
        String color;
        for (;;) {
            System.out.print("Выберите один из 5 цветов глаз (GREEN, BLACK, BLUE, YELLOW, ORANGE) :");
            color = readLine();
            System.out.println();
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
                    System.out.println("Проверьте введенные данные.");
            }
        }
    }

    public static NaturalColor readNaturalColor(){
        String color;
        for (;;) {
            System.out.print("Выберите один из 5 цветов волос (RED, BLACK, YELLOW, WHITE, BROWN): ");
            color = readLine();
            System.out.println();
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
                    System.out.println("Проверьте введенные данные.");
            }
        }
    }

    public static int readLocationX(){
        System.out.print("Введите координату X: ");
        Integer x;
        for (;;){
            try{
                x = readInteger();
                System.out.println();
                if (x == null){
                    System.out.print("Строка не может быть пустой. Введите координату X: ");
                } else {
                    return x;
                }
            } catch (NumberFormatException e) {
                System.out.print("Число введено неверно. Введите координату X: ");
            }
        }
    }

    public static Integer readLocationY(){
        System.out.print("Введите координату Y: ");
        Integer y;
        for (;;){
            try {
                y = readInteger();
                System.out.println();
                if (y == null){
                    System.out.print("Строка не может быть пустой. Введите координату Y: ");
                } else {
                    return y;
                }
            } catch (NumberFormatException e) {
                System.out.print("Число введено неверно. Введите координату Y: ");
            }
        }
    }

    public static double readLocationZ(){
        System.out.print("Введите координату Z: ");
        Double z;
        for (;;){
            try {
                z = readDouble();
                System.out.println();
                if (z == null){
                    System.out.print("Строка не может быть пустой. Введите координату Z: ");
                } else {
                    return z;
                }
            } catch (NumberFormatException e) {
                System.out.print("Число введено неверно. Введите координату Z: ");
            }
        }
    }

    public static boolean readChoice(String param){
        String yn;
        for (;;) {
            System.out.print("Вы хотите добавить " + param + "? y/n: ");
            yn = readLine();
            System.out.println();
            if (yn.equals("y")){
                return true;
            } else if (yn.equals("n")) {
                return false;
            } else {
                System.out.println("Проврьте введенные данные.");
            }
        }
    }


    public static String readLocationName(){
        System.out.print("Введите название локации: ");
        String name = readLine();
        System.out.println();
        if (name.isEmpty()){
            return null;
        }
        else {
            return name;
        }
    }

    public static long readId(){
        System.out.print("Введите id дракона: ");
        Long id = null;
        System.out.println();
        while (true) {
            try {
                id = readLong();
                if (id == null) {
                    System.out.print("Строка не может быть пустой. Введите id дракона: ");
                } else {
                    return id;
                }
            } catch (NumberFormatException e) {
                System.out.print("Число введено неверно. Введите id дракона: ");
            }
        }
    }

    public static String readLink(){
        System.out.print("Введите ссылку на скрипт: ");
        String line = readLine();
        System.out.println();
        return line;
    }

    public static String readNamePart(){
        String name;
        for (;;){
            System.out.print("Введите подстроку: ");
            name = readLine();
            System.out.println();
            if (name.isEmpty() || name.isBlank()){
                System.out.println("Строка не может быть пустой!");
            } else {
                return name;
            }
        }
    }


}
