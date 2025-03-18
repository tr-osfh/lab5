package root.files.commands;

import root.files.collection.CollectionManager;

/**
 * Команда вывода справочной информации о доступных командах.
 * Отображает список всех зарегистрированных команд с их описаниями.
 */
public class HelpCommand implements Command {

    private CollectionManager manager;

    /**
     * Конструктор команды помощи
     * @param manager Менеджер коллекции для доступа к реестру команд
     */
    public HelpCommand(CollectionManager manager) {
        this.manager = manager;
    }

    /**
     * @param args Аргументы команды (должны отсутствовать)
     * @throws IllegalArgumentException При наличии аргументов
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            CommandManager cm = manager.getCommandManager();
            manager.help(cm.getCommands());
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Возвращает описание команды для системы помощи
     * @return Строка с синтаксисом и назначением команды
     */
    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
}