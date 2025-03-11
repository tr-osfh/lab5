package root.files.file;

import root.files.commands.CommandManager;
import root.files.seClasses.BrightColor;
import root.files.seClasses.DragonType;
import root.files.seClasses.NaturalColor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScriptReaderManager {
    private final Scanner scanner;

    public ScriptReaderManager(String filename) throws FileNotFoundException {
        this.scanner = new Scanner(new File(filename));
    }

    public Scanner getScanner(){
        return this.scanner;
    }

    public String[] readCmdArg() throws IllegalArgumentException{
        String[] cmdArg = scanner.nextLine().trim().split(" ");
        String cmd = cmdArg[0].trim();
        if (CommandManager.getCommands().containsKey(cmd)){
            if (
                cmd.equals("update") ||
                cmd.equals("remove_by_id") ||
                cmd.equals("execute_script") ||
                cmd.equals("filter_contains_name") ||
                cmd.equals("filter_starts_with_name")
            ){
                if (cmdArg.length == 2){
                    return new String[]{cmd, cmdArg[1].trim()};
                } else {
                    if (cmdArg.length == 1){
                        return new String[]{cmd};
                    } else {
                        throw new IllegalArgumentException("");
                    }
                }
            } else {
                throw new IllegalArgumentException("");
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String readName() throws IllegalArgumentException{
        String name = scanner.nextLine();
        if (name.isEmpty() || name.isBlank()){
            throw new IllegalArgumentException();
        } else {
            return name;
        }
    }

    public Float readCoordinateX() throws IllegalArgumentException{
        String x = scanner.nextLine();
        if (x.isEmpty() || x.isBlank()){
            throw new IllegalArgumentException();
        } else {
            return Float.valueOf(x.trim());
        }
    }

    public Integer readCoordinateY() throws IllegalArgumentException{
        String y = scanner.nextLine();
        if (y.isEmpty() || y.isBlank()){
            throw new IllegalArgumentException();
        } else {
            return Integer.valueOf(y.trim());
        }
    }

    public Long readAge() throws IllegalArgumentException{
        String age = scanner.nextLine();
        if (age.isBlank() || age.isEmpty()) {
            return null;
        } else if (Long.valueOf(age) <= 0){
            throw new IllegalArgumentException();
        } else {
            return Long.valueOf(age);
        }
    }

    public String readDescription(){
        return scanner.nextLine();
    }

    public Long readWeight() throws IllegalArgumentException{
        String weight = scanner.nextLine();
        if (weight.isBlank() || weight.isEmpty()) {
            return null;
        } else if (Long.valueOf(weight) <= 0){
            throw new IllegalArgumentException();
        } else {
            return Long.valueOf(weight);
        }
    }

    public String readPassportID() throws IllegalArgumentException{
        String id = scanner.nextLine();
        if (id.isEmpty() || id.isBlank()){
            throw new IllegalArgumentException();
        } else {
            return id;
        }
    }

    public DragonType readType() throws IllegalArgumentException{
        String type = scanner.nextLine();
        return switch (type) {
            case "WATER" -> DragonType.WATER;
            case "UNDERGROUND" -> DragonType.UNDERGROUND;
            case "AIR" -> DragonType.AIR;
            default -> throw new IllegalArgumentException();
        };
    }

    public BrightColor readBrightColor() throws IllegalArgumentException{
        String color = scanner.nextLine();
        return switch (color) {
            case "GREEN" -> BrightColor.GREEN;
            case "BLACK" -> BrightColor.BLACK;
            case "BLUE" -> BrightColor.BLUE;
            case "YELLOW" -> BrightColor.YELLOW;
            case "ORANGE" -> BrightColor.ORANGE;
            default -> throw new IllegalArgumentException();
        };
    }

    public NaturalColor readNaturalColor() throws IllegalArgumentException{
        String color = scanner.nextLine();
        return switch (color) {
            case "BLACK" -> NaturalColor.BLACK;
            case "RED" -> NaturalColor.RED;
            case "YELLOW" -> NaturalColor.YELLOW;
            case "WHITE" -> NaturalColor.WHITE;
            case "BROWN" -> NaturalColor.BROWN;
            default -> throw new IllegalArgumentException();
        };
    }

    public int readLocationX() throws IllegalArgumentException{
        String x = scanner.nextLine();
        if (x.isEmpty() || x.isBlank()){
            throw new IllegalArgumentException();
        } else {
            return Integer.parseInt(x.trim());
        }
    }

    public Integer readLocationY() throws IllegalArgumentException{
        String y = scanner.nextLine();
        if (y.isEmpty() || y.isBlank()){
            throw new IllegalArgumentException();
        } else {
            return Integer.valueOf(y);
        }
    }

    public double readLocationZ()throws IllegalArgumentException{
        String z = scanner.nextLine();
        if (z.isBlank() || z.isEmpty()){
            throw new IllegalArgumentException();
        } else {
            return Double.parseDouble(z.trim());
        }
    }

    public boolean readChoice()throws IllegalArgumentException{
        String yn = scanner.nextLine();
        return switch (yn) {
            case "y" -> true;
            case "n" -> false;
            default -> throw new IllegalArgumentException();
        };
    }

    public String readLocationName(){
        String name = scanner.nextLine();
        if (name.isEmpty() || name.isBlank()){
            return null;
        } else {
            return name;
        }
    }

}
