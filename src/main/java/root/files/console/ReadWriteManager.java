package root.files.console;

import root.files.exeptions.FormatException;

import java.awt.*;
import java.util.Scanner;

public class ReadWriteManager {

    public static Integer readInteger() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            if (input.isEmpty()) {
                return null;
            }
            return Integer.valueOf(input.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }

    }

    public static Float readFloat() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            return Float.valueOf(input.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public static Long readLong() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            return null;  // Если строка пустая, возвращаем null
        }
        try {
            return Long.valueOf(input.trim());  // Пытаемся преобразовать строку в число
        } catch (NumberFormatException e) {
            throw new NumberFormatException();  // Бросаем исключение, если формат неверный
        }
    }

    public static Double readDouble() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            return null;
        }
        try {
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException e){
            throw new NumberFormatException();
        }
    }



    public static String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}