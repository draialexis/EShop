package data;

import model.*;
import util.Size;

import java.io.IOException;

public class Stub implements Loadable {

    @Override
    public Shop load() throws IOException, ClassNotFoundException {
        Shop shop = new Shop();

        Garment p1 = new Garment("shirt", 15.9);
        p1.addAllSizes();

        Garment p2 = new Garment("sweater", 34.9);
        p1.addSize(Size.valueOf("XXL"));
        p1.addSize(Size.valueOf("XXS"));
        p1.addSize(Size.valueOf("XS"));

        Perfume p3 = new Perfume("spiderman", 89.9);
        p3.addSmell("woody");
        p3.addSmell("musky");

        Perfume p4 = new Perfume("boring", 143.9);
        p3.addSmell("flowery");
        p3.addSmell("acidic");

        shop.addProduct(p1);
        shop.addProduct(p2);
        shop.addProduct(p3);
        shop.addProduct(p4);

        return shop;
    }
}
