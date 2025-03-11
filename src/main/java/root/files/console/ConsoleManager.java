package root.files.console;

import root.files.seClasses.BrightColor;
import root.files.seClasses.DragonType;
import root.files.seClasses.NaturalColor;

public class ConsoleManager {
    private static ReadController rc = new ReadController();
    public String readName(){
        rc.printLine("Введите имя: ");
        String name;
        for (;;){
            name = rc.readLine();
            System.out.println();
            if (name.isEmpty() || name.isBlank()){
                rc.printLine("Строка не может быть пустой! Введите имя: ");
            } else {
                return name;
            }
        }
    }

    public Float readCoordinateX() {
        rc.printLine("Введите координату X: ");
        Float coordinateX;

        for (;;) {
            try {
                coordinateX = rc.readFloat();
                if (coordinateX == null) {
                    rc.printLine("Строка не может быть пустой. Введите координату X: ");
                }else{
                    return coordinateX;
                }
            } catch (NumberFormatException e) {
                rc.printLine("Число введено неверно. Введите координату X: ");
            }
        }
    }

    public Integer readCoordinateY(){
        rc.printLine("Введите координату Y: ");
        Integer coordinateY;
        for (;;){
            try {
                coordinateY = rc.readInteger();
                if (coordinateY == null) {
                    rc.printLine("Строка не может быть пустой. Введите координату Y: ");
                } else {
                    return coordinateY;
                }
            } catch (NumberFormatException e) {
                rc.printLine("Число введено неверно. Введите координату Y: ");
            }
        }
    }

    public Long readAge(){
        rc.printLine("Введите возраст дракона: ");
        Long age;
        for (;;){
            try {
                age = rc.readLong();
                if (age == null) {
                    return null;
                } else if (age <= 0) {
                    rc.printLine("Возраст должен быть больше 0. Введите возраст дракона: ");
                } else {
                    return age;
                }
            } catch (NumberFormatException e) {
                rc.printLine("Число введено неверно. Введите возраст дракона: ");
            }
        }
    }

    public String readDescription(){
        rc.printLine("Введите описание дракона: ");
        String line = rc.readLine();
        return line;
    }

    public Long readWeight() {
        rc.printLine("Введите вес дракона: ");
        Long weight;
        for (;;) {
            try {
                weight = rc.readLong();
                if (weight == null) {
                    return weight;
                } else if (weight < 0) {
                    rc.printLine("Вес должен быть больше 0. Введите вес дракона: ");
                } else {
                    return weight;
                }
            } catch (NumberFormatException e) {
                rc.printLine("Число введено неверно. Введите вес дракона: ");
            }
        }
    }

    public String readPassportID(){
        rc.printLine("Введите идентификатор человека: ");
        String passportId = null;
        for (;;){
            passportId = rc.readLine();
            if (passportId.isEmpty()){
                System.out.println("Идентификатор не может быть пустым. Введите идентификатор человека: ");
            } else {
                return passportId;
            }
        }
    }

    public DragonType readType() {
        String type;
        for (; ; ) {
            rc.printLine("Выбирите один из трех типов (WATER, UNDERGROUND, AIR). Введите тип дракона: ");
            type = rc.readLine();
            System.out.println();
            switch (type) {
                case "WATER":
                    return DragonType.WATER;
                case "UNDERGROUND":
                    return DragonType.UNDERGROUND;
                case "AIR":
                    return DragonType.AIR;
                default:
                    rc.printLine("Проверьте введенные данные.");
            }
        }
    }

    public BrightColor readBrightColor(){
        String color;
        for (;;) {
            rc.printLine("Выберите один из 5 цветов глаз (GREEN, BLACK, BLUE, YELLOW, ORANGE) :");
            color = rc.readLine();
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
                    rc.printLine("Проверьте введенные данные.");
            }
        }
    }

    public NaturalColor readNaturalColor(){
        String color;
        for (;;) {
            rc.printLine("Выберите один из 5 цветов волос (RED, BLACK, YELLOW, WHITE, BROWN): ");
            color = rc.readLine();
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
                    rc.printLine("Проверьте введенные данные.");
            }
        }
    }

    public int readLocationX(){
        rc.printLine("Введите координату X: ");
        Integer x;
        for (;;){
            try{
                x = rc.readInteger();
                System.out.println();
                if (x == null){
                    rc.printLine("Строка не может быть пустой. Введите координату X: ");
                } else {
                    return x;
                }
            } catch (NumberFormatException e) {
                rc.printLine("Число введено неверно. Введите координату X: ");
            }
        }
    }

    public Integer readLocationY(){
        rc.printLine("Введите координату Y: ");
        Integer y;
        for (;;){
            try {
                y = rc.readInteger();
                System.out.println();
                if (y == null){
                    rc.printLine("Строка не может быть пустой. Введите координату Y: ");
                } else {
                    return y;
                }
            } catch (NumberFormatException e) {
                rc.printLine("Число введено неверно. Введите координату Y: ");
            }
        }
    }

    public double readLocationZ(){
        rc.printLine("Введите координату Z: ");
        Double z;
        for (;;){
            try {
                z = rc.readDouble();
                System.out.println();
                if (z == null){
                    rc.printLine("Строка не может быть пустой. Введите координату Z: ");
                } else {
                    return z;
                }
            } catch (NumberFormatException e) {
                rc.printLine("Число введено неверно. Введите координату Z: ");
            }
        }
    }

    public boolean readChoice(String param){
        String yn;
        for (;;) {
            rc.printLine("Вы хотите добавить " + param + "? y/n: ");
            yn = rc.readLine();
            if (yn.equals("y")){
                return true;
            } else if (yn.equals("n")) {
                return false;
            } else {
                System.out.println("Проврьте введенные данные.");
            }
        }
    }


    public String readLocationName(){
        rc.printLine("Введите название локации: ");
        String name = rc.readLine();
        if (name.isEmpty()){
            return null;
        }
        else {
            return name;
        }
    }

    public long readId(){
        rc.printLine("Введите id дракона: ");
        Long id = null;
        while (true) {
            try {
                id = rc.readLong();
                if (id == null) {
                    rc.printLine("Строка не может быть пустой. Введите id дракона: ");
                } else {
                    return id;
                }
            } catch (NumberFormatException e) {
                rc.printLine("Число введено неверно. Введите id дракона: ");
            }
        }
    }

    public String readLink(){
        rc.printLine("Введите ссылку на скрипт: ");
        String line = rc.readLine();
        return line;
    }

    public String readNamePart(){
        String name;
        for (;;){
            rc.printLine("Введите подстроку: ");
            name = rc.readLine();
            if (name.isEmpty() || name.isBlank()){
                System.out.println("Строка не может быть пустым!");
            } else {
                return name;
            }
        }
    }

    public String[] readCommand(){
        rc.printLine("Введите команду: \n");
        String command = rc.readLine();
        String[] args = command.split(" ");
        return args;
    }

    public void printLine(Object str){
        rc.printLine(str);
    }


}
