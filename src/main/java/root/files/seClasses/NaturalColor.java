package root.files.seClasses;

/**
 * Перечисление NaturalColor представляет естественные цвета, которые могут быть использованы в программе.
 * Поддерживаемые цвета: RED, BLACK, YELLOW, WHITE, BROWN.
 */
public enum NaturalColor {
    RED,
    BLACK,
    YELLOW,
    WHITE,
    BROWN;

    /**
     * Преобразует строку в значение перечисления NaturalColor.
     * @param name Строка, представляющая цвет.
     * @return Значение перечисления NaturalColor, соответствующее строке.
     *         Если строка не соответствует ни одному значению, возвращает null.
     */
    public static NaturalColor ValueOf(String name) {
        try {
            return NaturalColor.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Неверный цвет: " + name);
            return null;
        }
    }
}