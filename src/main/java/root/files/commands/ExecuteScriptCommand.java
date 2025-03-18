package root.files.commands;

import root.files.collection.CollectionManager;
import root.files.console.Validator;
import root.files.file.FileStack;
import root.files.file.ScriptReaderManager;
import root.files.seClasses.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Команда выполнения скрипта из файла. Читает и исполняет последовательность команд,
 * включая создание объектов Dragon с валидацией. Обрабатывает вложенные скрипты с защитой от рекурсии.
 */
public class ExecuteScriptCommand implements Command {

    private CollectionManager manager;
    private final HashMap<String, Command> commands;
    private Validator validator = new Validator();

    /**
     * Конструктор инициализирует менеджер коллекции и копию регистра команд
     * @param manager Менеджер для доступа к методам работы с коллекцией
     */
    public ExecuteScriptCommand(CollectionManager manager) {
        this.manager = manager;
        this.commands = CommandManager.getCommands();
    }

    /**
     * @param args Аргументы команды (требуется ровно 1 аргумент - путь к скрипту)
     * @throws FileNotFoundException Если файл скрипта не существует
     */
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
                                    // ... (логика создания Dragon)
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
            } else {
                System.out.println("Рекурсия невозможна.");
            }
        }
    }

    /**
     * Возвращает описание команды для справки
     * @return Форматированная строка с синтаксисом и назначением
     */
    @Override
    public String getDescription() {
        return "execute_script file_path : считать и исполнить скрипт из указанного файла. Поддерживает команды с объектами.";
    }
}