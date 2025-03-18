package root.files.file;

import root.files.commands.CommandManager;
import root.files.seClasses.BrightColor;
import root.files.seClasses.DragonType;
import root.files.seClasses.NaturalColor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Класс ScriptReaderManager отвечает за чтение данных из скриптового файла.
 * Используется для выполнения команд и обработки данных, содержащихся в скрипте.
 */
public class ScriptReaderManager {
    private final Scanner scanner;

    /**
     * Конструктор класса ScriptReaderManager.
     * @param filename Имя файла, из которого будут читаться данные.
     * @throws FileNotFoundException Если файл не найден.
     */
    public ScriptReaderManager(String filename) throws FileNotFoundException {
        this.scanner = new Scanner(new File(filename));
    }

    /**
     * Возвращает сканер для чтения данных из файла.
     * @return Сканер для чтения данных.
     */
    public Scanner getScanner() {
        return this.scanner;
    }

    /**
     * Читает команду и её аргументы из файла.
     * @return Массив строк, содержащий команду и её аргументы.
     * @throws IllegalArgumentException Если команда или её аргументы неверны.
     */
    public String[] readCmdArg() throws IllegalArgumentException {
        String[] cmdArg = scanner.nextLine().trim().split(" ");
        String cmd = cmdArg[0].trim();
        if (CommandManager.getCommands().containsKey(cmd)) {
            if (
                    cmd.equals("update") ||
                            cmd.equals("remove_by_id") ||
                            cmd.equals("execute_script") ||
                            cmd.equals("filter_contains_name") ||
                            cmd.equals("filter_starts_with_name")
            ) {
                if (cmdArg.length == 2) {
                    return new String[]{cmd, cmdArg[1].trim()};
                } else {
                    throw new IllegalArgumentException("Неверное количество аргументов для команды.");
                }
            } else {
                // Команды без аргументов
                if (cmdArg.length == 1) {
                    return new String[]{cmd};
                } else {
                    throw new IllegalArgumentException("Команда не требует аргументов.");
                }
            }
        } else {
            throw new IllegalArgumentException("Неизвестная команда.");
        }
    }

    /**
     * Читает имя из файла.
     * @return Прочитанное имя.
     * @throws IllegalArgumentException Если имя пустое или состоит из пробелов.
     */
    public String readName() throws IllegalArgumentException {
        String name = scanner.nextLine().replace(',', '.');
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым.");
        } else {
            return name;
        }
    }

    /**
     * Читает координату X из файла.
     * @return Прочитанная координата X.
     * @throws IllegalArgumentException Если координата пустая или состоит из пробелов.
     */
    public Float readCoordinateX() throws IllegalArgumentException {
        String x = scanner.nextLine();
        if (x.isEmpty() || x.isBlank()) {
            throw new IllegalArgumentException("Координата X не может быть пустой.");
        } else {
            return Float.valueOf(x.trim());
        }
    }

    /**
     * Читает координату Y из файла.
     * @return Прочитанная координата Y.
     * @throws IllegalArgumentException Если координата пустая или состоит из пробелов.
     */
    public Integer readCoordinateY() throws IllegalArgumentException {
        String y = scanner.nextLine();
        if (y.isEmpty() || y.isBlank()) {
            throw new IllegalArgumentException("Координата Y не может быть пустой.");
        } else {
            return Integer.valueOf(y.trim());
        }
    }

    /**
     * Читает возраст из файла.
     * @return Прочитанный возраст или null, если возраст не указан.
     * @throws IllegalArgumentException Если возраст меньше или равен 0.
     */
    public Long readAge() throws IllegalArgumentException {
        String age = scanner.nextLine();
        if (age.isBlank() || age.isEmpty()) {
            return null;
        } else if (Long.valueOf(age) <= 0) {
            throw new IllegalArgumentException("Возраст должен быть больше 0.");
        } else {
            return Long.valueOf(age);
        }
    }

    /**
     * Читает описание из файла.
     * @return Прочитанное описание.
     */
    public String readDescription() {
        return scanner.nextLine().replace(',', '.');
    }

    /**
     * Читает вес из файла.
     * @return Прочитанный вес или null, если вес не указан.
     * @throws IllegalArgumentException Если вес меньше или равен 0.
     */
    public Long readWeight() throws IllegalArgumentException {
        String weight = scanner.nextLine();
        if (weight.isBlank() || weight.isEmpty()) {
            return null;
        } else if (Long.valueOf(weight) <= 0) {
            throw new IllegalArgumentException("Вес должен быть больше 0.");
        } else {
            return Long.valueOf(weight);
        }
    }

    /**
     * Читает идентификатор паспорта из файла.
     * @return Прочитанный идентификатор паспорта.
     * @throws IllegalArgumentException Если идентификатор пустой или состоит из пробелов.
     */
    public String readPassportID() throws IllegalArgumentException {
        String id = scanner.nextLine().trim().replace(',', '.');
        if (id.isEmpty() || id.isBlank()) {
            throw new IllegalArgumentException("Идентификатор паспорта не может быть пустым.");
        } else {
            return id;
        }
    }

    /**
     * Читает тип дракона из файла.
     * @return Прочитанный тип дракона.
     * @throws IllegalArgumentException Если тип дракона неверен.
     */
    public DragonType readType() throws IllegalArgumentException {
        String type = scanner.nextLine();
        return switch (type) {
            case "WATER" -> DragonType.WATER;
            case "UNDERGROUND" -> DragonType.UNDERGROUND;
            case "AIR" -> DragonType.AIR;
            default -> throw new IllegalArgumentException("Неверный тип дракона.");
        };
    }

    /**
     * Читает цвет глаз из файла.
     * @return Прочитанный цвет глаз.
     * @throws IllegalArgumentException Если цвет глаз неверен.
     */
    public BrightColor readBrightColor() throws IllegalArgumentException {
        String color = scanner.nextLine();
        return switch (color) {
            case "GREEN" -> BrightColor.GREEN;
            case "BLACK" -> BrightColor.BLACK;
            case "BLUE" -> BrightColor.BLUE;
            case "YELLOW" -> BrightColor.YELLOW;
            case "ORANGE" -> BrightColor.ORANGE;
            default -> throw new IllegalArgumentException("Неверный цвет глаз.");
        };
    }

    /**
     * Читает цвет волос из файла.
     * @return Прочитанный цвет волос.
     * @throws IllegalArgumentException Если цвет волос неверен.
     */
    public NaturalColor readNaturalColor() throws IllegalArgumentException {
        String color = scanner.nextLine();
        return switch (color) {
            case "BLACK" -> NaturalColor.BLACK;
            case "RED" -> NaturalColor.RED;
            case "YELLOW" -> NaturalColor.YELLOW;
            case "WHITE" -> NaturalColor.WHITE;
            case "BROWN" -> NaturalColor.BROWN;
            default -> throw new IllegalArgumentException("Неверный цвет волос.");
        };
    }

    /**
     * Читает координату X локации из файла.
     * @return Прочитанная координата X.
     * @throws IllegalArgumentException Если координата пустая или состоит из пробелов.
     */
    public int readLocationX() throws IllegalArgumentException {
        String x = scanner.nextLine();
        if (x.isEmpty() || x.isBlank()) {
            throw new IllegalArgumentException("Координата X локации не может быть пустой.");
        } else {
            return Integer.parseInt(x.trim());
        }
    }

    /**
     * Читает координату Y локации из файла.
     * @return Прочитанная координата Y.
     * @throws IllegalArgumentException Если координата пустая или состоит из пробелов.
     */
    public Integer readLocationY() throws IllegalArgumentException {
        String y = scanner.nextLine();
        if (y.isEmpty() || y.isBlank()) {
            throw new IllegalArgumentException("Координата Y локации не может быть пустой.");
        } else {
            return Integer.valueOf(y);
        }
    }

    /**
     * Читает координату Z локации из файла.
     * @return Прочитанная координата Z.
     * @throws IllegalArgumentException Если координата пустая или состоит из пробелов.
     */
    public double readLocationZ() throws IllegalArgumentException {
        String z = scanner.nextLine();
        if (z.isBlank() || z.isEmpty()) {
            throw new IllegalArgumentException("Координата Z локации не может быть пустой.");
        } else {
            return Double.parseDouble(z.trim());
        }
    }

    /**
     * Читает выбор пользователя (y/n) из файла.
     * @return true, если выбор "y", иначе false.
     * @throws IllegalArgumentException Если выбор неверен.
     */
    public boolean readChoice() throws IllegalArgumentException {
        String yn = scanner.nextLine();
        return switch (yn) {
            case "y" -> true;
            case "n" -> false;
            default -> throw new IllegalArgumentException("Неверный выбор. Ожидается 'y' или 'n'.");
        };
    }

    /**
     * Читает название локации из файла.
     * @return Прочитанное название локации или null, если название пустое.
     */
    public String readLocationName() {
        String name = scanner.nextLine().replace(',', '.').trim();
        if (name.isEmpty() || name.isBlank()) {
            return null;
        } else {
            return name;
        }
    }
}