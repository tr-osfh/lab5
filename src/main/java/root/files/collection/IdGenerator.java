package root.files.collection;

import java.util.Random;
import java.util.HashSet;

public class IdGenerator {

    private static HashSet<Integer> occupiedIds = new HashSet<>();

    public static int generateId(){
        int id;
        do {
            int time = (int) System.currentTimeMillis();
            int rnd = new Random().nextInt(1000);
            id = rnd + time;
        } while (occupiedIds.contains(id));
        occupiedIds.add(id);
        return id;
    }


    private static HashSet<Integer> occupiedPassportIds = new HashSet<>();
    public static String generatePassportId(){
        Integer id;
        do {
            int time = (int) System.currentTimeMillis();
            int rnd = new Random().nextInt(1000);
            id = rnd + time;
        } while (occupiedIds.contains(id));
        occupiedPassportIds.add(id);
        return id.toString();
    }



}
