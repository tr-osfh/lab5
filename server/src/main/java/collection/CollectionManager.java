package collection;

import commands.Command;
import commands.CommandManager;
import file.FileManager;
import seClasses.Dragon;

import java.util.*;

/**
 * Менеджер коллекции драконов.
 */
public class CollectionManager {

    private static PriorityQueue<Dragon> dragons = new PriorityQueue<>();
    private static FileManager fm;
    private static CommandManager commandManager;
    private static final Validator validator = new Validator();
    private static final java.time.LocalDateTime creationDate = java.time.LocalDateTime.now();

    public void setFileManager(FileManager fm) {
        this.fm = fm;
    }

    public static PriorityQueue<Dragon> getDragons() {
        return dragons;
    }


    public void setDragons(PriorityQueue<Dragon> dragons) {
        this.dragons = dragons;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }


    public CommandManager getCommandManager() {
        return commandManager;
    }

    public static String show() {
        StringBuilder res = new StringBuilder();
        if (!dragons.isEmpty()) {
            dragons.forEach(dragon -> res.append(dragon.toString() + "\n"));
            return res.toString();
        } else {
            return "Коллекция пуста.";
        }
    }


    public static String add(Dragon dragon) {
        boolean inCollection = false;
        for (Dragon dragonTmp : dragons) {
            if (dragonTmp.equals(dragon)) {
                inCollection = true;
                break;
            }
        }
        if (inCollection) {
            return ("Этот дракон уже есть в коллекции.\n");
        } else {
            dragon.setId(IdGenerator.generateId());
            if (validator.getValid(dragon) != null){
                dragons.add(dragon);
            } else {
                return ("Параметры дракона не верны.\n");
            }
            return ("Дракон успешно добавлен.\n");
        }
    }

    public static String info() {
        return "Тип хранимых данных в коллекции: Dragon\n" +
        "Дата и время инициализации: " + creationDate + "\n" +
        "Колличество элементов в коллеции: " + dragons.size() + "\n";
    }


    public static String updateById(Long dragonId, Dragon dragon) {
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
                    return ("Параметры дракона не верны.\n");
                }
                inCollection = true;
                break;
            }
        }
        if (inCollection) {
            return ("Данные дракона успешно обновлены.\n");
        } else {
            return ("Дракона с ID " + dragonId + " нет в коллекции.\n");
        }
    }


    public static String removeById(Long dragonId) {
        Iterator<Dragon> iterator = dragons.iterator();
        while (iterator.hasNext()) {
            Dragon dragon = iterator.next();
            if (dragon.getId() == (dragonId)) {
                iterator.remove();
                return "Дракон с ID " + dragonId + " успешно удален.\n";
            }
        }
        return "Дракона с ID " + dragonId + " нет в коллекции.\n";
    }


    public static void exit() {
        System.exit(0);
    }

    public static void clear() {
        dragons.clear();
    }


    public static String head() {
        if (!dragons.isEmpty()) {
            return (dragons.peek().toString());
        } else {
            return ("Коллекция пуста.\n");
        }
    }

    public static String addIfMin(Dragon dragon) {
        dragon.setId(IdGenerator.generateId());
        boolean inCollection = false;
        for (Dragon dragonTmp : dragons) {
            if (dragonTmp.equals(dragon)) {
                inCollection = true;
            }
        }
        if (inCollection) {
            return ("Этот дракон уже есть в коллекции.\n");
        } else {
            if (dragons.isEmpty()){
                if (validator.getValid(dragon) != null){
                    dragons.add(dragon);
                } else {
                    return ("Параметры дракона не верны.\n");
                }
                return ("Дракон успешно добавлен.\n");
            }
            if (dragon.getCoordinates().getX() < dragons.peek().getCoordinates().getX()) {
                if (validator.getValid(dragon) != null){
                    dragons.add(dragon);
                } else {
                    return ("Параметры дракона не верны.\n");
                }
                return ("Дракон успешно добавлен.\n");
            } else {
                return ("Данный дракон не имеет минимального значения.\n");
            }
        }
    }


    public static String removeLower(Dragon dragon) {
        List<Dragon> toRemove = new ArrayList<>();

        for (Dragon dragonToCheck : dragons) {
            if (dragonToCheck.getCoordinates().getX() < dragon.getCoordinates().getX()) {
                toRemove.add(dragonToCheck);
            }
        }

        if (!toRemove.isEmpty()) {
            dragons.removeAll(toRemove);
            return ("Драконы, меньшие чем заданный, удалены.\n");
        } else {
            return ("Драконов меньше, чем заданный, нет в коллекции\n");
        }
    }


    public static String sumOfAge(){
        Long sAge = 0L;
        if (!dragons.isEmpty()){
            for (Dragon dragon : dragons){
                if (dragon.getAge() != null){
                    sAge += dragon.getAge();
                }
            }
            if (sAge == 0L){
                return ("Нет данных о возрасте драконов.\n");
            } else {
                return ("Суммарный возраст всех драконов: " + sAge);
            }
        } else {
            return ("В коллекции нет драконов.\n");
        }
    }


    public static String filterContainsName(String name){
        boolean flag = false;
        StringBuilder res = new StringBuilder();
        if (!dragons.isEmpty()) {
            for (Dragon dragon : dragons){
                if (dragon.getName().contains(name)){
                    res.append(dragon + "\n");
                    flag = true;
                }
            }
            if (flag){
                return res.toString();
            } else {
                return ("Поиск не дал результатов.\n");
            }
        } else {
            return ("Поиск не дал результатов.\n");
        }
    }


    public static String filterStartsWithName(String name){
        int len = name.length();

        boolean flag = false;
        StringBuilder res = new StringBuilder();
        if (!dragons.isEmpty()) {
            for (Dragon dragon : dragons){
                if (dragon.getName().substring(0, len).equals(name)){
                    res.append(dragon + "\n");
                    flag = true;
                }
            }
            if (flag){
                return res.toString();
            } else {
                return ("Поиск не дал результатов.\n");
            }
        } else {
            return ("Поиск не дал результатов.\n");
        }
    }
}