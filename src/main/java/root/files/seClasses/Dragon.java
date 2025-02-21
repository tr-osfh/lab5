package root.files.seClasses;

import root.files.collection.IdGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Dragon implements Comparable<Dragon> {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate = java.time.LocalDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long age; //Значение поля должно быть больше 0, Поле может быть null
    private String description; //Поле может быть null
    private Long weight; //Значение поля должно быть больше 0, Поле может быть null
    private DragonType type; //Поле не может быть null
    private Person killer; //Поле может быть null

    public Dragon(
            String name,
            Coordinates coordinates,
            Long age,
            String description,
            Long weight,
            DragonType type,
            Person killer
    ) {
        if (name == null || name.isEmpty() || coordinates == null || (age != null && age <= 0) || weight <= 0 || type == null){
            System.out.println("Введенная информация содержит недопустимые значения.");//добавить вывод информации об ошибке
        } else {
            this.id = IdGenerator.generateId();
            this.name = name;
            this.coordinates = coordinates;
            this.age = age;
            this.description = description;
            this.weight = weight;
            this.type = type;
            this.killer = killer;
        }
    }

    public Dragon(
            String name,
            Coordinates coordinates,
            Long age,
            String description,
            Long weight,
            DragonType type
    ) {
            if (name == null || name.isEmpty() || coordinates == null || (age != null && age <= 0) || weight <= 0 || type == null){
                System.out.println("В исходном файле ошибка."); //добавить вывод информации об ошибке
                throw new RuntimeException();
            } else {
                this.id = IdGenerator.generateId();
                this.name = name;
                this.coordinates = coordinates;
                this.age = age;
                this.description = description;
                this.weight = weight;
                this.type = type;
            }
    }

    public long getId() {
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }


    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public DragonType getType() {
        return type;
    }

    public void setType(DragonType type) {
        this.type = type;
    }

    public Person getKiller() {
        return killer;
    }

    public void setKiller(Person killer) {
        this.killer = killer;
    }

    public String[] getDBDescription(){
        String[] res = new String[9];
        res[0] = Long.toString(this.getId());
        res[1] = this.getName();
        res[2] = this.getCoordinates().toString();
        res[3] = this.getCreationDate().toString();
        res[4] = this.getAge().toString();
        res[5] = this.getDescription();
        res[6] = this.getWeight().toString();
        res[7] = this.getType().toString();
        res[8] = this.getKiller().toString();
        return res;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Dragon dragon = (Dragon) object;
        return
                id == dragon.id &&
                Objects.equals(name, dragon.name) &&
                Objects.equals(coordinates, dragon.coordinates) &&
                Objects.equals(creationDate, dragon.creationDate) &&
                Objects.equals(age, dragon.age) &&
                Objects.equals(description, dragon.description) &&
                Objects.equals(weight, dragon.weight) &&
                type == dragon.type &&
                Objects.equals(killer, dragon.killer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                coordinates,
                creationDate,
                age,
                description,
                weight,
                type,
                killer
        );
    }

    @Override
    public String toString() {
        return "Dragon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", age=" + age +
                ", getDescription='" + description + '\'' +
                ", weight=" + weight +
                ", type=" + type +
                ", killer=" + killer +
                '}';
    }

    @Override
    public int compareTo(Dragon o) {
        if (this.getCoordinates().getX() > o.getCoordinates().getX()){
            return 1;
        } else if (this.getCoordinates().getX() < o.getCoordinates().getX()){
            return -1;
        } else {
            return 0;
        }
    }
}