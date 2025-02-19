package root.files;

import root.files.collection.CollectionManagerRecever;
import root.files.commands.CommandManager;
import root.files.file.FileWriterManager;
import root.files.seClasses.Dragon;

import static root.files.console.Reader.*;

public class Main {
    public static void main(String[] args) {
        FileWriterManager fm = new FileWriterManager(args[0]);
        CollectionManagerRecever cmr = new CollectionManagerRecever();
        CommandManager cm = new CommandManager(cmr);
        cmr.setFileName(args[0]);
        cmr.setDragons(fm.loadCSV());
        for (;;){
            cm.executeCommand();
        }
    }
}