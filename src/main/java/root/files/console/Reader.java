package root.files.console;

import root.files.seClasses.BrightColor;
import root.files.seClasses.DragonType;
import root.files.seClasses.NaturalColor;

public interface Reader {

    public String readName();

    public Float readCoordinateX();

    public Integer readCoordinateY();

    public Long readAge();

    public String readDescription();

    public Long readWeight();

    public String readPassportID();

    public DragonType readType();

    public BrightColor readBrightColor();

    public NaturalColor readNaturalColor();

    public int readLocationX();

    public Integer readLocationY();

    public double readLocationZ();

    public boolean readChoice();

    public String readLocationName();
}
