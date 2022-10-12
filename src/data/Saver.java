package data;

import model.Shop;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class Saver implements Savable {
    @Override
    public void save(Shop model) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.bin"))) {
            System.out.println("...saving!...");
            oos.writeObject(model);
        }
    }
}
