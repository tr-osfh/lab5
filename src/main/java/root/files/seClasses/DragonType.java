package root.files.seClasses;

public enum DragonType {
    WATER,
    UNDERGROUND,
    AIR;

    public static DragonType ValueOf(String name) {
        try {
            return DragonType.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Неверный тип дракона: " + name);
            return null;
        }
    }
}

