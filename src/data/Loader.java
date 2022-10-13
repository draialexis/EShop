package data;

import model.Shop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Loader implements Loadable {
    @Override
    public Shop load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.bin"))) {
            return (Shop) ois.readObject();
        }
    }
}
