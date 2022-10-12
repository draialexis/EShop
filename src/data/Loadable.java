package data;

import model.Shop;

import java.io.IOException;

public interface Loadable {
    Shop load() throws IOException, ClassNotFoundException;
}
