package root.files.seClasses;

import java.util.Objects;

public class Location {
    private int x;
    private Integer y; //Поле не может быть null
    private double z;
    private String name; //Поле может быть null

    public Location(int x, Integer y, double z, String name){
        if (y == null){
            System.out.println("Ошибочка вкралась");
        } else {
            this.x = x;
            this.y = y;
            this.z = z;
            this.name = name;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
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
        Location location = (Location) object;
        return
                x == location.x &&
                Double.compare(z, location.z) == 0 &&
                Objects.equals(y, location.y) &&
                Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                x,
                y,
                z,
                name);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }
}
