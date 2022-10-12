package data;

import model.Shop;

import java.io.IOException;

public interface Savable {
    void save(Shop model) throws IOException;
}
