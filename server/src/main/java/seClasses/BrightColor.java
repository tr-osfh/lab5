package seClasses;

/**
 * Перечисление BrightColor представляет яркие цвета, которые могут быть использованы в программе.
 * Поддерживаемые цвета: GREEN, BLACK, BLUE, YELLOW, ORANGE.
 */
public enum BrightColor {
    GREEN,
    BLACK,
    BLUE,
    YELLOW,
    ORANGE;

    /**
     * Преобразует строку в значение перечисления BrightColor.
     * @param name Строка, представляющая цвет.
     * @return Значение перечисления BrightColor, соответствующее строке.
     *         Если строка не соответствует ни одному значению, возвращает null.
     */
    public static BrightColor ValueOf(String name) {
        try {
            return BrightColor.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Неверный цвет: " + name);
            return null;
        }
    }
}