package root.files.seClasses;

public enum NaturalColor {
    RED,
    BLACK,
    YELLOW,
    WHITE,
    BROWN;

    public static NaturalColor ValueOf(String name) {
        try {
            return NaturalColor.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Неверный цвет: " + name);
            return null;
        }
    }
}
