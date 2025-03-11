package root.files.file;

import java.util.Stack;

public class FileStack {

    private static Stack<String> fileStack = new Stack<>();

    public FileStack(){
    }

    public static Stack<String> getFileStack(){
        return fileStack;
    }

    public static void addFile(String filename){
        fileStack.push(filename);
    }

    public static void removeFile(){
        fileStack.pop();
    }
}
