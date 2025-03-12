package root.files.console;

import root.files.collection.IdGenerator;
import root.files.seClasses.Dragon;
import root.files.seClasses.DragonType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Validator {

    private ArrayList<Long> ids = new ArrayList<>();
    private IdGenerator idGen = new IdGenerator();
    private ArrayList<DragonType> possibleDragonTypes = new ArrayList<>(Arrays.asList(
            DragonType.AIR,
            DragonType.UNDERGROUND,
            DragonType.WATER
    ));


    public Dragon getValidatedId(Dragon dragon){
        Long id = dragon.getId();
        if (ids.contains(id)){
            Long tmpId = idGen.generateId();
            System.out.println("ID дракона " + dragon.getId() + " заменен на " + tmpId + " из-за коллизии.");
            dragon.setId(tmpId);
            ids.add(tmpId);
            return dragon;
        } else {
            ids.add(dragon.getId());
            return dragon;
        }
    }



    public Dragon getValid(Dragon dragon) {
        if (
                dragon.getId() <= 0 ||
                dragon.getName().isBlank() ||
                dragon.getName().isEmpty() ||
                dragon.getName() == null ||
                dragon.getCoordinates() == null ||
                dragon.getCreationDate() == null ||
                dragon.getAge() <= 0 ||
                dragon.getWeight() <= 0 ||
                dragon.getType() == null ||
                !possibleDragonTypes.contains(dragon.getType()) ||
                dragon.getCoordinates().getX() == null ||
                dragon.getCoordinates().getY() == null
        ) {
            return null;
        } else {
            if (dragon.getKiller() != null) {
                if (
                        dragon.getKiller().getName().isEmpty() ||
                                dragon.getKiller().getName().isBlank() ||
                                dragon.getKiller().getName() == null ||
                                dragon.getKiller().getPassportID().isBlank() ||
                                dragon.getKiller().getPassportID().isEmpty() ||
                                dragon.getKiller().getPassportID() == null ||
                                dragon.getKiller().getEyeColor() == null ||
                                dragon.getKiller().getHairColor() == null ||
                                dragon.getKiller().getLocation() == null ||
                                dragon.getKiller().getLocation().getY() == null
                ) {
                    return null;
                } else {
                    return dragon;
                }
            } else {
                return dragon;
            }
        }
    }
}


