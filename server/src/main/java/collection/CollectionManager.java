package collection;

import commands.CommandManager;
import file.FileManager;
import seClasses.Dragon;

import java.util.*;

/**
 * Менеджер коллекции драконов.
 */
public class CollectionManager {

    PriorityQueue<Dragon> dragons = new PriorityQueue<>();
    FileManager fm;
    ConsoleManager cm = new ConsoleManager();
    private CommandManager commandManager;
    private final Validator validator = new Validator();
    private final DragonManager dragonManager = new DragonManager(cm);
    private final java.time.LocalDateTime creationDate = java.time.LocalDateTime.now();

    /**
     * Устанавливает файловый менеджер для работы с сохранением/загрузкой данных
     * @param fm Экземпляр {@link FileManager}
     */
    public void setFileManager(FileManager fm) {
        this.fm = fm;
    }

    /**
     * Возвращает текущую коллекцию драконов
     * @return PriorityQueue<Dragon> - коллекция объектов Dragon
     */
    public PriorityQueue<Dragon> getDragons() {
        return dragons;
    }

    /**
     * Заменяет текущую коллекцию драконов
     * @param dragons Новая коллекция объектов Dragon
     */
    public void setDragons(PriorityQueue<Dragon> dragons) {
        this.dragons = dragons;
    }

    /**
     * Устанавливает менеджер команд для взаимодействия
     * @param commandManager Экземпляр {@link CommandManager}
     */
    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /**
     * Возвращает текущий менеджер команд
     * @return {@link CommandManager}
     */
    public CommandManager getCommandManager() {
        return commandManager;
    }

    /**
     * Возвращает менеджер для работы с данными драконов
     * @return {@link DragonManager}
     */
    public DragonManager getDragonManager() {
        return dragonManager;
    }

    /**
     * Выводит все элементы коллекции в консоль.
     * Если коллекция пуста, отображает соответствующее сообщение.
     */
    public void show() {
        if (!dragons.isEmpty()) {
            for (Dragon dragon : dragons) {
                cm.printLine(dragon + "\n");
            }
        } else {
            cm.printLine("Коллекция пуста.\n");
        }
    }

    /**
     * Сохраняет коллекцию в CSV-файл через {@link FileManager}.
     * Выводит сообщение об успехе или ошибке записи.
     */
    public void save() {
        try {
            fm.saveCSV(dragons);
            cm.printLine("Коллекция сохранена в файл.\n");
        } catch (Exception e) {
            cm.printLine("Запись в файл не возможна\n");
        }
    }

    /**
     * Добавляет дракона в коллекцию после проверки:
     * - Уникальности объекта
     * - Валидности данных через {@link Validator}
     * @param dragon Объект Dragon для добавления
     */
    public void add(Dragon dragon) {
        boolean inCollection = false;
        for (Dragon dragonTmp : dragons) {
            if (dragonTmp.equals(dragon)) {
                inCollection = true;
                break;
            }
        }
        if (inCollection) {
            cm.printLine("Этот дракон уже есть в коллекции.\n");
        } else {
            if (validator.getValid(dragon) != null){
                dragons.add(dragon);
            } else {
                cm.printLine("Параметры дракона не верны.\n");
            }
            cm.printLine("Дракон успешно добавлен.\n");
        }
    }

    /**
     * Выводит справку по доступным командам
     * @param commands Карта команд с их описаниями
     */
    public void help(HashMap<String, Command> commands) {
        for (Command command : commands.values()) {
            cm.printLine(command.getDescription() + "\n");
        }
    }

    /**
     * Выводит метаинформацию о коллекции:
     * - Тип хранимых данных
     * - Время инициализации
     * - Количество элементов
     */
    public void info() {
        cm.printLine("Тип хранимых данных в коллекции: Dragon\n");
        cm.printLine("Дата и время инициализации: " + creationDate + "\n");
        cm.printLine("Колличество элементов в коллеции: " + dragons.size() + "\n");
    }

    /**
     * Обновляет дракона по ID. Заменяет старые данные новыми после валидации.
     * @param dragonId ID дракона для обновления
     * @param dragon Новые данные дракона
     */
    public void updateById(Long dragonId, Dragon dragon) {
        boolean inCollection = false;
        Iterator<Dragon> iterator = dragons.iterator();
        while (iterator.hasNext()) {
            Dragon dragonToRemove = iterator.next();
            if (dragonToRemove.getId() == dragonId) {
                iterator.remove();
                dragon.setId(dragonId);
                if (validator.getValid(dragon) != null) {
                    dragons.add(dragon);
                } else {
                    cm.printLine("Параметры дракона не верны.\n");
                }
                inCollection = true;
                break;
            }
        }
        if (inCollection) {
            cm.printLine("Данные дракона успешно обновлены.\n");
        } else {
            cm.printLine("Дракона с ID " + dragonId + " нет в коллекции.\n");
        }
    }

    /**
     * Удаляет дракона из коллекции по ID
     * @param dragonId ID дракона для удаления
     */
    public void removeById(Long dragonId) {
        try {
            boolean inCollection = false;
            Iterator<Dragon> iterator = dragons.iterator();

            while (iterator.hasNext()) {
                Dragon dragonToRemove = iterator.next();
                if (dragonToRemove.getId() == dragonId) {
                    iterator.remove();
                    cm.printLine("Дракон с ID " + dragonId + " успешно удален.\n");
                    dragons.remove(dragonToRemove);
                    inCollection = true;
                }
            }

            if (!inCollection) {
                cm.printLine("Дракона с ID " + dragonId + " нет в коллекции.\n");
            }
        } catch (NumberFormatException e) {
            cm.printLine("Ошибка: ID должен быть числом.\n");
        }
    }

    /**
     * Завершает работу приложения
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Полностью очищает коллекцию
     */
    public void clear() {
        dragons.clear();
        cm.printLine("Коллекция успешно удалена.\n");
    }


    /**
     * Выводит первый элемент коллекции
     */
    public void head() {
        if (!dragons.isEmpty()) {
            cm.printLine(dragons.peek());
        } else {
            cm.printLine("Коллекция пуста.\n");
        }
    }

    /**
     * Добавляет дракона, если его значение меньше минимального в коллекции (по координате X)
     * @param dragon Объект Dragon для проверки и добавления
     */
    public void addIfMin(Dragon dragon) {
        boolean inCollection = false;
        for (Dragon dragonTmp : dragons) {
            if (dragonTmp.equals(dragon)) {
                inCollection = true;
            }
        }
        if (inCollection) {
            cm.printLine("Этот дракон уже есть в коллекции.\n");
        } else {
            if (dragon.getCoordinates().getX() < dragons.peek().getCoordinates().getX()) {
                if (validator.getValid(dragon) != null){
                    dragons.add(dragon);
                } else {
                    cm.printLine("Параметры дракона не верны.\n");
                }
                cm.printLine("Дракон успешно добавлен.\n");
            } else {
                cm.printLine("Данный дракон не имеет минимального значения.\n");
            }
        }
    }

    /**
     * Удаляет всех драконов, меньших чем заданный (по координате X)
     * @param dragon Объект Dragon для сравнения
     */
    public void removeLower(Dragon dragon) {
        List<Dragon> toRemove = new ArrayList<>();

        for (Dragon dragonToCheck : dragons) {
            if (dragonToCheck.getCoordinates().getX() < dragon.getCoordinates().getX()) {
                toRemove.add(dragonToCheck);
            }
        }

        if (!toRemove.isEmpty()) {
            dragons.removeAll(toRemove);
            cm.printLine("Драконы, меньшие чем заданный, удалены.\n");
        } else {
            cm.printLine("Драконов меньше, чем заданный, нет в коллекции\n");
        }
    }

    /**
     * Выводит суммарный возраст всех драконов коллекции.
     */
    public void sumOfAge(){
        Long sAge = 0L;
        if (!dragons.isEmpty()){
            for (Dragon dragon : dragons){
                if (dragon.getAge() != null){
                    sAge += dragon.getAge();
                }
            }
            if (sAge == 0L){
                cm.printLine("Нет данных о возрасте драконов.\n");
            } else {
                cm.printLine("Суммарный возраст всех драконов: " + sAge);
            }
        } else {
            cm.printLine("В коллекции нет драконов.\n");
        }
    }

    /**
     * Ищет драконов, чьи имена содержат заданную подстроку
     * @param name Подстрока для поиска
     */
    public void filterContainsName(String name){
        boolean flag = false;
        for (Dragon dragon : dragons){
            if (dragon.getName().contains(name)){
                cm.printLine(dragon + "\n");
                flag = true;
            }
        }
        if (!flag){
            cm.printLine("Поиск не дал результатов.\n");
        }
    }

    /**
     * Ищет драконов, чьи имена начинаются с заданной подстроки
     * @param name Подстрока для поиска в начале имени
     */
    public void filterStartsWithName(String name){
        boolean flag = false;
        int len = name.length();
        for (Dragon dragon : dragons){
            if (dragon.getName().length() >= len){
                if (dragon.getName().substring(0, len).equals(name)){
                    cm.printLine(dragon + "\n");
                    flag = true;
                }
            }
        }
        if (!flag){
            cm.printLine("Поиск не дал результатов.\n");
        }
    }
}