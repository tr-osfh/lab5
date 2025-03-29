package commands;


import seClasses.Dragon;

/**
 * Команда обновления элемента коллекции по ID.
 * Заменяет данные существующего элемента новыми значениями, полученными через интерактивный ввод.
 */
public class UpdateIdCommand implements Command {
    private Long id;
    private Dragon dragon;

    public UpdateIdCommand(Long id, Dragon dragon) {
        this.id = id;
        this.dragon = dragon;
    }

    @Override
    public void execute(String[] args) {
    }

    @Override
    public String getDescription(){
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}