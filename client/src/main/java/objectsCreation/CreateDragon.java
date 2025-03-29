package objectsCreation;

import console.ConsoleReader;
import console.ReadController;
import seClasses.Coordinates;
import seClasses.Dragon;
import seClasses.Person;

public class CreateDragon extends Creation<Dragon>{
    private final ReadController rc;

    public CreateDragon(ReadController console){
        this.rc = console;
    }

    @Override
    public Dragon create() {
        ConsoleReader consoleReader = new ConsoleReader(rc);
        if (consoleReader.readChoice()){
            return new Dragon(consoleReader.readName(), readCoordinates(), consoleReader.readAge(), consoleReader.readDescription(), consoleReader.readWeight(), consoleReader.readType(), readPerson());
        }
        return new Dragon(consoleReader.readName(), readCoordinates(), consoleReader.readAge(), consoleReader.readDescription(), consoleReader.readWeight(), consoleReader.readType());
    }
    private Coordinates readCoordinates(){
        return new CreateCoordinates(rc).create();
    }

    private Person readPerson(){
        return new CreatePerson(rc).create();
    }
}
