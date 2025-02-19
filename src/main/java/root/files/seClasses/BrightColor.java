package root.files.seClasses;

public enum BrightColor {
    GREEN,
    BLACK,
    BLUE,
    YELLOW,
    ORANGE;

    public static BrightColor ValueOf(String name) {
        try {
            return BrightColor.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Неверный цвет: " + name);
            return null;
        }
    }
}
