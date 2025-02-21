package root.files.commands;

import root.files.collection.CollectionManagerRecever;
import root.files.seClasses.Dragon;

public class AddIfMinCommand implements Command{
    private CollectionManagerRecever manager;

    public AddIfMinCommand(CollectionManagerRecever manager) {
        this.manager = manager;
    }



    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            try {
                Dragon dragon = manager.getDragonManager().setDragon();

                boolean inCollection = false;
                for (Dragon dragonTmp : manager.getDragons()) {
                    if (dragonTmp.equals(dragon)) {
                        inCollection = true;
                    }
                }
                if (inCollection) {
                    System.out.println("Этот дракон уже есть в коллекции.");
                } else {
                    if (dragon.getCoordinates().getX() < manager.getDragons().peek().getCoordinates().getX()){
                        manager.addIfMin(dragon);
                        System.out.println("Дракон успешно добавлен.");
                    } else {
                        System.out.println("Данный дракон не имеет минимального значения.");
                    }

                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getDescription() {
        return "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}
