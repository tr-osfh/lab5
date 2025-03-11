package root.files.commands;

import root.files.collection.CollectionManager;
import root.files.file.FileStack;
import root.files.file.ScriptReaderManager;
import root.files.seClasses.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ExecuteScriptCommand implements Command{

    private CollectionManager manager;
    private final HashMap<String, Command> commands;

    public ExecuteScriptCommand(CollectionManager manager) {
        this.manager = manager;
        this.commands = CommandManager.getCommands();
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2){
            String link = args[1];
            if (!FileStack.getFileStack().contains(link)) {
                FileStack.addFile(link);
                try {
                    ScriptReaderManager srm = new ScriptReaderManager(link);
                    while (srm.getScanner().hasNextLine()){
                        try {
                            String[] cmdAndArg = srm.readCmdArg();
                            String cmd = cmdAndArg[0];
                            if (commands.containsKey(cmd)) {
                                if (
                                        cmd.equals("add") ||
                                                cmd.equals("update") ||
                                                cmd.equals("add_if_min") ||
                                                cmd.equals("remove_lower")
                                ) {
                                    Dragon dragon;
                                    String name = srm.readName();

                                    Float coordinateX = srm.readCoordinateX();
                                    Integer coordinateY = srm.readCoordinateY();

                                    Long age = srm.readAge();
                                    String description = srm.readDescription();
                                    Long weight = srm.readWeight();
                                    DragonType type = srm.readType();
                                    if (srm.readChoice()) {
                                        String killerName = srm.readName();
                                        String killerPassportId = srm.readPassportID();
                                        BrightColor killerEyeColor = srm.readBrightColor();
                                        NaturalColor killerHairColor = srm.readNaturalColor();

                                        int locationX = srm.readLocationX();
                                        Integer locationY = srm.readLocationY();
                                        double locationZ = srm.readLocationZ();
                                        String locationName = srm.readLocationName();

                                        dragon = new Dragon(
                                                name,
                                                new Coordinates(coordinateX, coordinateY),
                                                age,
                                                description,
                                                weight,
                                                type,
                                                new Person(killerName, killerPassportId, killerEyeColor, killerHairColor, new Location(locationX, locationY, locationZ, locationName))
                                        );
                                    } else {
                                        dragon = new Dragon(
                                                name,
                                                new Coordinates(coordinateX, coordinateY),
                                                age,
                                                description,
                                                weight,
                                                type
                                        );
                                    }

                                    if (dragon != null) { // будет работать когда добавлю валидатор
                                        switch (cmd) {
                                            case "add" -> manager.add(dragon);
                                            case "update" -> manager.updateById(Long.valueOf(cmdAndArg[1]), dragon);
                                            case "add_if_min" -> manager.addIfMin(dragon);
                                            case "remove_lower" -> manager.removeLower(dragon);
                                        }
                                    }
                                } else {
                                    commands.get(cmd).execute(cmdAndArg);
                                }
                            }
                        } catch (IllegalArgumentException | NoSuchElementException ignored) {}
                    }
                } catch (FileNotFoundException e){
                    System.out.println("Файл не найден");
                } finally {
                    FileStack.removeFile();
                }
            }
        }
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же";
    }
}
