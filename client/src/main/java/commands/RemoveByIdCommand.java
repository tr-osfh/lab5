package commands;


import java.io.Serializable;

/**
 * Команда удаления элемента коллекции по уникальному идентификатору (ID).
 * Осуществляет поиск элемента с указанным ID и его удаление при наличии.
 */
public class RemoveByIdCommand implements Command, Serializable {

    private Long id;

    public RemoveByIdCommand(Long id) {
        this.id = id;
    }

    @Override
    public void execute(String[] args) {
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}