package console;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CommandSerializer {
    public static <T extends Serializable> byte[] serialize(T object){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(baos);
        } catch (IOException e) {
            System.out.println("Ошибка сериализации");
            return new byte[0];
        }
        try {
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            System.out.println("Ошибка сериализации");
            return new byte[0];
        }
        try {
            objectOutputStream.close();
        } catch (IOException e){
            System.out.println("Невозможно закрыть канал");
        }
        return baos.toByteArray();
    }
}
