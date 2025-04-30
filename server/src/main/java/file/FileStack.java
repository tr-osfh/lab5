package file;

import java.util.Stack;

/**
 * Класс FileStack предоставляет функциональность для работы со стеком файлов.
 * Используется для хранения имен файлов в порядке их добавления.
 */
public class FileStack {

    private static Stack<String> fileStack = new Stack<>();

    /**
     * Конструктор класса FileStack.
     */
    public FileStack() {
    }

    /**
     * Возвращает текущий стек файлов.
     * @return Стек, содержащий имена файлов.
     */
    public static Stack<String> getFileStack() {
        return fileStack;
    }

    /**
     * Добавляет имя файла в стек.
     * @param filename Имя файла для добавления в стек.
     */
    public static void addFile(String filename) {
        fileStack.push(filename);
    }

    /**
     * Удаляет последнее добавленное имя файла из стека.
     */
    public static void removeFile() {
        fileStack.pop();
    }
}