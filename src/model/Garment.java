package model;

import util.Size;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Garment extends Product {

//    private List<Color> colors = new ArrayList<>();

    /**
     * if there are 3 shirts in M, the list will contain M, M, M
     */
    private final List<Size> sizes = new ArrayList<>();

    public Garment(String name, double price) {
        super(name, price);
    }

    public void addSize(Size size) {
        sizes.add(size);
    }

    public void addAllSizes() {
        sizes.addAll(Arrays.asList(Size.values()));
    }


    public List<Size> getSizes() {
        return Collections.unmodifiableList(sizes);
    }

    public void removeSize(Size size) {
        sizes.remove(size);
    }
}

