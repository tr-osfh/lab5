package root.files.console;

import java.util.Scanner;

public class ReadWriteManager {

    public static Integer readInteger() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().isEmpty()) {
            return null;
        }
        try {
            return Integer.valueOf(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

    }

    public static Long readLong() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().isEmpty()) {
            return null;
        }
        try {
            return Long.valueOf(scanner.nextLine().trim());
        } catch (NumberFormatException e){
            throw new RuntimeException(e);
        }

    }

    public static Double readDouble() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().isEmpty()) {
            return null;
        }
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e){
            throw new RuntimeException(e);
        }
    }

    public static Float readFloat(){
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().isEmpty()) {
            return null;
        }
        try {
            return Float.valueOf(scanner.nextLine().trim());
        } catch (NumberFormatException e){
            throw new RuntimeException(e);
        }
    }

    public static String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }


}