package root.files.collection;

import java.util.Random;
import java.util.HashSet;

public class IdGenerator {

    private static HashSet<Long> occupiedIds = new HashSet<>();
    private static Random random = new Random();

    public long generateId() {
        long id;
        do {
            long timestamp = System.currentTimeMillis();
            long randomPart = random.nextInt(1000000);
            long randomDiv = random.nextInt(10000);

            id = (timestamp * 1000000 + randomPart) / randomDiv;
        } while (occupiedIds.contains(id));

        occupiedIds.add(id);
        return id;

    }
}