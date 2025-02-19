package root.files;

import root.files.collection.CollectionManagerRecever;
import root.files.commands.CommandManager;
import root.files.seClasses.Dragon;

import static root.files.console.Reader.*;

public class Main {
    public static void main(String[] args) {
        CollectionManagerRecever cmr = new CollectionManagerRecever();
        CommandManager cm = new CommandManager(cmr);
        for (;;){
            cm.executeCommand();
        }
    }
}