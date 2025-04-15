package collection;

import commands.CommandManager;
import file.FileManager;
import seClasses.Dragon;

import java.util.*;
import java.util.stream.Collectors;

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
        return dragons.isEmpty()
                ? "Коллекция пуста."
                : dragons.stream()
                .map(Dragon::toString)
                .collect(Collectors.joining("\n")) + "\n";
    }


    public static String add(Dragon dragon) {
        boolean alreadyExists = dragons.stream()
                .anyMatch(d -> d.equals(dragon));

        if (alreadyExists) {
            return "Этот дракон уже есть в коллекции.\n";
        }

        dragon.setId(IdGenerator.generateId());
        if (validator.getValid(dragon) == null) {
            return "Параметры дракона не верны.\n";
        }

        dragons.add(dragon);
        return "Дракон успешно добавлен.\n";
    }

    public static String info() {
        return "Тип хранимых данных в коллекции: Dragon\n" +
        "Дата и время инициализации: " + creationDate + "\n" +
        "Колличество элементов в коллеции: " + dragons.size() + "\n";
    }


    public static String updateById(Long dragonId, Dragon updatedDragon) {
        if (validator.getValid(updatedDragon) == null) {
            return "Параметры дракона не верны.\n";
        }

        Optional<Dragon> existingDragon = dragons.stream()
                .filter(d -> d.getId() == (dragonId))
                .findFirst();

        if (existingDragon.isPresent()) {
            dragons.remove(existingDragon.get());
            updatedDragon.setId(dragonId);
            dragons.add(updatedDragon);
            return "Данные дракона успешно обновлены.\n";
        } else {
            return "Дракона с ID " + dragonId + " нет в коллекции.\n";
        }
    }

    public static String removeById(Long dragonId) {
        Optional<Dragon> dragonToRemove = dragons.stream()
                .filter(d -> d.getId() == (dragonId))
                .findFirst();

        if (dragonToRemove.isPresent()) {
            dragons.remove(dragonToRemove.get());
            return "Дракон с ID " + dragonId + " успешно удален.\n";
        } else {
            return "Дракона с ID " + dragonId + " нет в коллекции.\n";
        }
    }


    public static void exit() {
        System.exit(0);
    }

    public static String clear() {
        long count = dragons.size();

        dragons.clear();
        return String.format("Коллекция очищена. Удалено %d элементов.\n", count);
}

    public static String head() {
        return dragons.stream()
                .findFirst()
                .map(Dragon::toString)
                .orElse("Коллекция пуста.\n");
    }

    public static String addIfMin(Dragon dragon) {

        dragon.setId(IdGenerator.generateId());

        boolean alreadyExists = dragons.stream()
                .anyMatch(d -> d.equals(dragon));

        if (alreadyExists) {
            return "Этот дракон уже есть в коллекции.\n";
        }

        if (validator.getValid(dragon) == null) {
            return "Параметры дракона не верны.\n";
        }

        boolean isMin = dragons.isEmpty() ||
                dragons.stream()
                        .allMatch(d -> dragon.getCoordinates().getX() < d.getCoordinates().getX());

        if (isMin) {
            dragons.add(dragon);
            return "Дракон успешно добавлен.\n";
        } else {
            return "Данный дракон не имеет минимального значения.\n";
        }
    }


    public static String removeLower(Dragon dragon) {
        if (dragons.isEmpty()) {
            return "Коллекция драконов пуста.\n";
        }

        List<Dragon> toRemove = dragons.stream()
                .filter(d -> d.getCoordinates().getX() < dragon.getCoordinates().getX())
                .collect(Collectors.toList());

        if (!toRemove.isEmpty()) {
            dragons.removeAll(toRemove);
            return String.format("Удалено %d драконов, меньших чем заданный.\n", toRemove.size());
        } else {
            return "Драконов меньше, чем заданный, нет в коллекции.\n";
        }
    }


    public static String sumOfAge() {
        if (dragons.isEmpty()) {
            return "В коллекции нет драконов.\n";
        }

        long sum = dragons.stream()
                .map(Dragon::getAge)
                .filter(Objects::nonNull)
                .mapToLong(Long::longValue)
                .sum();

        return sum == 0L
                ? "Нет данных о возрасте драконов.\n"
                : "Суммарный возраст всех драконов: " + sum + "\n";
    }


    public static String filterContainsName(String name) {
        if (dragons.isEmpty()) {
            return "Поиск не дал результатов.\n";
        }

        String result = dragons.stream()
                .filter(dragon -> dragon.getName().contains(name))
                .map(Dragon::toString)
                .collect(Collectors.joining("\n"));

        return result.isEmpty()
                ? "Поиск не дал результатов.\n"
                : result + "\n";
    }

    public static String filterStartsWithName(String name) {
        int len = name.length();

        if (dragons.isEmpty()) {
            return "Поиск не дал результатов.\n";
        }

        String result = dragons.stream()
                .filter(dragon -> dragon.getName().length() >= len)
                .filter(dragon -> dragon.getName().substring(0, len).equals(name))
                .map(Dragon::toString)
                .collect(Collectors.joining("\n"));

        return result.isEmpty()
                ? "Поиск не дал результатов.\n"
                : result + "\n";
    }
}