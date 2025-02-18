package root.files.seClasses;


import root.files.collection.IdGenerator;
import root.files.seClasses.BrightColor;
import root.files.seClasses.NaturalColor;
import root.files.seClasses.Location;

import java.util.Objects;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private final String passportID = IdGenerator.generatePassportId(); //Строка не может быть пустой, Значение этого поля должно быть уникальным, Поле не может быть null
    private BrightColor eyeColor; //Поле не может быть null
    private NaturalColor hairColor; //Поле может быть null
    private Location location; //Поле не может быть null

    public Person(String name, BrightColor eyeColor, NaturalColor hairColor, Location location){
        if (name == null || name.isEmpty() || eyeColor == null || hairColor == null || location == null){
            System.out.println("Ошибка вкралась");
        } else {
            this.name = name;
            this.eyeColor = eyeColor;
            this.hairColor = hairColor;
            this.location = location;
        }
    }

    public String getPassportID() {
        return passportID;
    }

    public BrightColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(BrightColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public NaturalColor getHairColor() {
        return hairColor;
    }

    public void setHairColor(NaturalColor hairColor) {
        this.hairColor = hairColor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setLocation(int x, Integer y, double z, String name){
        Location location = new Location(x, y, z, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Person person = (Person) object;
        return
                Objects.equals(name, person.name) &&
                Objects.equals(passportID, person.passportID) &&
                Objects.equals(location, person.location) &&
                eyeColor == person.eyeColor &&
                hairColor == person.hairColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                passportID,
                eyeColor,
                hairColor,
                location
        );
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", passportID='" + passportID + '\'' +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", location=" + location +
                '}';
    }
}
