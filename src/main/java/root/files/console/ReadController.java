package root.files.console;

import java.util.Objects;
import java.util.Scanner;

public class ReadController {

    public Integer readInteger() {
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

    public Float readFloat() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            return Float.valueOf(input.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public Long readLong() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            return null;
        }
        try {
            return Long.valueOf(input.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public Double readDouble() {
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



    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().replace(',', '.').trim();
    }

    public void printLine(Object str){
        System.out.print(str);
    }
}