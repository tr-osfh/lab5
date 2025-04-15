package collection;

import seClasses.Dragon;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

/**
 * Генератор уникальных идентификаторов для объектов коллекции.
 * Использует комбинацию временной метки и случайных чисел для генерации ID,
 * гарантируя отсутствие дубликатов в рамках текущей сессии.
 */

public class IdGenerator {
    public static long generateId() {
        PriorityQueue<Dragon> dragons = CollectionManager.getDragons();
        return dragons.stream()
                .mapToLong(Dragon::getId)
                .max()
                .orElse(0) + 1;
    }
}